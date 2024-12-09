package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_account;
    private String creation_date;
    private int reviews_created;
    private int user_id_user;
    private int login_id_login;
}
