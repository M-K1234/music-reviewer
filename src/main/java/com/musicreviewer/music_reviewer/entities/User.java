package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;


@Data
@Entity
@Table(
    name = "user",
    uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
public class User {

    public User() {}

    public User(int id, String fullName,String username)
    {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    
    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    // @OneToOne
    // private Account account;
}