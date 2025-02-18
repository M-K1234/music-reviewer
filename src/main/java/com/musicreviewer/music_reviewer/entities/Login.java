package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "login",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Login {

    public Login() {
    }

    public Login(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;
}