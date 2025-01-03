package com.musicreviewer.music_reviewer.dtos;

import jakarta.validation.constraints.Email;
// import org.hibernate.annotations.processing.Pattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


import lombok.Data;

@Data
public class RegistrationDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ'’.\\- ]+$")
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String username;

    @NotBlank
    @Size(min = 6, max = 64)
    private String password;
}
