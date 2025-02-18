package com.musicreviewer.music_reviewer.controllers;

import com.musicreviewer.music_reviewer.dtos.UserDTO;
import com.musicreviewer.music_reviewer.entities.User;
import com.musicreviewer.music_reviewer.repositories.UserRepository;
import com.musicreviewer.music_reviewer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

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
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new UserDTO(savedUser));
    }

    // PUT: Opdater en eksisterende bruger
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setFullName(user.getFullName());
            userToUpdate.setUsername(user.getUsername());
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
