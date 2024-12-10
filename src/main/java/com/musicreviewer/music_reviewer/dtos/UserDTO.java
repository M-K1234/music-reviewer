package com.musicreviewer.music_reviewer.dtos;

import com.musicreviewer.music_reviewer.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private int idUser;
    private String firstname;
    private String lastname;
    private String username;

    // Constructor to map from User entity
    public UserDTO(User user) {
        this.idUser = user.getIdUser(); // Correct method name
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
    }

    // Method to map back to User entity
    public User toEntity() {
        User user = new User();
        user.setIdUser(this.idUser); // Correct method name
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setUsername(this.username);
        return user;
    }
}
