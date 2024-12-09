package com.musicreviewer.music_reviewer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicreviewer.music_reviewer.dtos.UserDTO;
import com.musicreviewer.music_reviewer.entities.User;
import com.musicreviewer.music_reviewer.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET: Hent alle brugere
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    // GET: Hent en specifik bruger efter ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok(new UserDTO(value)))
                   .orElse(ResponseEntity.notFound().build());
    }

    // POST: Opret en ny bruger
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userDTO.toEntity();
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new UserDTO(savedUser));
    }

    // PUT: Opdater en eksisterende bruger
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setFirstname(userDTO.getFirstname());
            userToUpdate.setLastname(userDTO.getLastname());
            userToUpdate.setUsername(userDTO.getUsername());
            User updatedUser = userRepository.save(userToUpdate);
            return ResponseEntity.ok(new UserDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Slet en bruger efter ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
