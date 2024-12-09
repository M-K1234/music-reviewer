package com.musicreviewer.music_reviewer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

    private int id_news;
    private String title;
    private String author;
    private String creation_date;
    private String imgurl;
    private String text;
    
}
