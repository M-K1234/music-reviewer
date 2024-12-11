package com.musicreviewer.music_reviewer.dtos;

import com.musicreviewer.music_reviewer.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String fullName;
    private String email;

    // Constructor to map from User entity
    public UserDTO(User user) {
        this.id = user.getId(); // Correct method name
        this.fullName = user.getFullName();
        this.email = user.getEmail();
    }

    // Method to map back to User entity
    public User toEntity() {
        User user = new User();
        user.setId(this.id); // Correct method name
        user.setFullName(this.fullName); // Correct method name
        user.setEmail(this.email);
        return user;
    }
}
