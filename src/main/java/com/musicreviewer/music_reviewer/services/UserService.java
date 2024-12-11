package com.musicreviewer.music_reviewer.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.dtos.UserDTO;
import com.musicreviewer.music_reviewer.entities.User;
import com.musicreviewer.music_reviewer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // private final BCryptPasswordEncoder passwordEncoder;


    // Hent alle brugere
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    
    public UserDTO getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDTO::new).orElse(null); // Returner null, hvis brugeren ikke findes
    }

    // Opret en ny bruger
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTO.toEntity(); // Konverter DTO til entitet
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser); // Konverter den gemte entitet tilbage til DTO
    }

    // Opdater en eksisterende bruger
    // public UserDTO updateUser(int id, User user) {
    //     Optional<User> existingUser = userRepository.findById(id);

    //     if (existingUser.isPresent()) {
    //         User user = existingUser.get();
    //         user.setFullName(userDTO.getFirstname());
    //         user.setUsername(userDTO.getUsername());
    //         User updatedUser = userRepository.save(user);
    //         return new UserDTO(updatedUser);
    //     } else {
    //         return null; // Eller smid en exception
    //     }
    // }

    // Slet en bruger
    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}