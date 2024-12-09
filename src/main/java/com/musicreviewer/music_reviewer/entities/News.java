package com.musicreviewer.music_reviewer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class News {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_news;
    private String title;
    private String author;
    private String creation_date;
    private String imgurl;
    @Lob
    private String text;
    
    
}
