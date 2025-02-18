package com.musicreviewer.music_reviewer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private int id_review;
    private String title;
    private String author;
    private Date creation_date;
    private String imgurl;
    private String text;
    private int score;

}
