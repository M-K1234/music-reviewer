package com.musicreviewer.music_reviewer.dtos;

import lombok.Data;

@Data
public class DuplicateCheckRequest {
    private String email;
    private String username;
}