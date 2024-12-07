package com.musicreviewer.music_reviewer.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    
    private int idReviews;
    private String title;
    private String author;
    private String creationDate;
    private String imgURL;
    private String text;
    private int score;
    

   

}
