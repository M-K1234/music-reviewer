package com.musicreviewer.music_reviewer.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Getter
@Setter
@Table(name = "review")
@Entity
public class Review {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
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

