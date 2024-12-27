package com.musicreviewer.music_reviewer.dtos;

import com.musicreviewer.music_reviewer.entities.Account;

import lombok.Data;

@Data
public class AccountDTO {
    private int id;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private int reviewsCreated;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.fullName = account.getUser().getFullName();
        this.username = account.getUser().getUsername();
        this.email = account.getLogin().getEmail();
        this.password = account.getLogin().getPassword(); // Use encrypted password in real systems
        this.reviewsCreated = account.getReviewsCreated();
    }
}
