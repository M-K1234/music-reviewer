package com.musicreviewer.music_reviewer.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsers")
    private int idUsers;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Column(name = "imgURL", length = 255)
    private String imgURL;

    // Forbindelse til reviews
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

}
