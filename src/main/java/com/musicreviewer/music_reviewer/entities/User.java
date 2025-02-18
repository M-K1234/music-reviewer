package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
public class User {

    public User() {
    }

    public User(int id, String fullName, String username) {
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
}