package com.musicreviewer.music_reviewer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeezerAlbumDTO {
    private String albumTitle;
    private String albumLink;
    private String cover_small;
    private String artist_name;
    private String artist_link;
}
