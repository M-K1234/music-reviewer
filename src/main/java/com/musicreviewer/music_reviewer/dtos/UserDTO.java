package com.musicreviewer.music_reviewer.dtos;

import java.util.Date;

import com.musicreviewer.music_reviewer.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int idUsers;
    private String firstname;
    private String lastname;
    private String username;
    private Date creationDate; // Hvis creationDate er relevant
    private String imgURL; // Hvis imgURL er relevant

    // Constructor med alle felter
    public UserDTO(int idUsers, String firstname, String lastname, String username, Date creationDate, String imgURL) {
        this.idUsers = idUsers;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.creationDate = creationDate;
        this.imgURL = imgURL;
    }

    // Constructor til at oprette fra User entitet
    public UserDTO(User user) {
        if (user != null) {
            this.idUsers = user.getIdUsers();
            this.firstname = user.getFirstname();
            this.lastname = user.getLastname();
            this.username = user.getUsername();
            this.creationDate = user.getCreationDate(); // Hvis creationDate er inkluderet
            this.imgURL = user.getImgURL(); // Hvis imgURL er inkluderet
        }
    }

    // Konverter til User-entitet
    public User toEntity() {
        User user = new User();
        user.setIdUsers(this.idUsers);
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setUsername(this.username);
        user.setCreationDate(this.creationDate); // Hvis creationDate er relevant
        user.setImgURL(this.imgURL); // Hvis imgURL er relevant
        return user;
    }
}