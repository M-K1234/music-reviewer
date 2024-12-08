package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private String firstname;
    private String lastname;
    private String username;
    
}
