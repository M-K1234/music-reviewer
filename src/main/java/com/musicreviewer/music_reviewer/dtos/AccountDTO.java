package com.musicreviewer.music_reviewer.dtos;

import com.musicreviewer.music_reviewer.entities.Account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountDTO {
    private int id;
    
    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-zÀ-ÿ'’.\\- ]+$")
    private String fullName;

    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 64)
    private String password;
    private int reviewsCreated;
    public AccountDTO() {}
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.fullName = account.getUser().getFullName();
        this.username = account.getUser().getUsername();
        this.email = account.getLogin().getEmail();
        this.password = account.getLogin().getPassword(); // Use encrypted password in real systems
        this.reviewsCreated = account.getReviewsCreated();
    }
}
