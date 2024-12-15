package com.musicreviewer.music_reviewer.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Account {

    // public Account() {}

    // public Account(int id, LocalDateTime creationDate)
    // {
    //     this.id = id;
    //     this.creationDate = creationDate;
    // }

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