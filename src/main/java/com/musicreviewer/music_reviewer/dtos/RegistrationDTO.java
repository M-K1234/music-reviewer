package com.musicreviewer.music_reviewer.dtos;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String fullName;
    private String email;
    private String username;
    private String password;
}
