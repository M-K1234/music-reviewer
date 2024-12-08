package com.musicreviewer.music_reviewer.dtos;

import java.util.Date;
import com.musicreviewer.music_reviewer.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private int id_user;
    private String firstname;
    private String lastname;
    private String username;


    // Constructor til at oprette fra User entitet
    public UserDTO(User user) {
        if (user != null) {
            this.id_user = user.getId_user();
            this.firstname = user.getFirstname();
            this.lastname = user.getLastname();
            this.username = user.getUsername();

        }
    }

    // Konverter til User-entitet
    public User toEntity() {
        User user = new User();
        user.setId_user(this.id_user);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setUsername(this.username);
        return user;
    }
}