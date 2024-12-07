package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.musicreviewer.music_reviewer.entities.*;;

@Getter
@Setter
@Table(name = "reviews")
@Entity
public class Review {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReviews")
    private int idReviews;

    private String title;
    private String author;
    
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    
    private String imgURL;
    
    @Lob
    private String text;
    
    private int score;
    
    @ManyToOne
    @JoinColumn(name = "Account_idAccount")
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "Account_User_idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Account_login_idlogin")
    private Login login;

}

