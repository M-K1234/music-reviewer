package com.musicreviewer.music_reviewer.dtos;

import com.musicreviewer.music_reviewer.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String fullName;
    private String username;

    // Constructor to map from User entity
    public UserDTO(User user) {
        this.id = user.getId(); // Correct method name
        this.fullName = user.getFullName();
        this.username = user.getUsername();
    }

    // Method to map back to User entity
    public User toEntity() {
        User user = new User();
        user.setId(this.id); // Correct method name
        user.setFullName(this.fullName); // Correct method name
        user.setUsername(this.username); 
        return user;
    }
}
