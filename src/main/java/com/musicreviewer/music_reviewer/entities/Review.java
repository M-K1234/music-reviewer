package com.musicreviewer.music_reviewer.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    public Review() {}

     public Review(int id_review, String title, String author, Date creation_date, String imgurl, String text, int score ) {
        this.id_review = id_review;
         this.title = title;
         this.author = author;
         this.creation_date = creation_date;
         this.imgurl = imgurl;
         this.text = text;
         this.score = score;
     }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_review;

    private String title;
    private String author;
    
    @Temporal(TemporalType.DATE)
    private Date creation_date;
    
    private String imgurl;
    
    @Lob
    private String text;
    
    private int score;
    
    @ManyToOne
    // @JoinColumn(name = "account_id_account")
    private Account account;
    
    // @ManyToOne
    // @JoinColumn(name = "account_user_id_user")
    // private User user;

    // @ManyToOne
    // @JoinColumn(name = "account_login_id_login")
    // private Login login;
}

