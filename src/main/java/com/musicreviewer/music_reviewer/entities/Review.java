package com.musicreviewer.music_reviewer.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "Account_Login_idLogin")
    private Login login;

}

