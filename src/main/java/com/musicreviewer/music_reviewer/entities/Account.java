package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Account {

    public Account() {
    }

    public Account(int id, LocalDateTime creationDate, int reviewsCreated, User user, Login login) {
        this.id = id;
        this.creationDate = creationDate;
        this.reviewsCreated = reviewsCreated;
        this.user = user;
        this.login = login;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    private int reviewsCreated;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "login_id", referencedColumnName = "id", nullable = false)
    private Login login;


    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Review> reviews;
}