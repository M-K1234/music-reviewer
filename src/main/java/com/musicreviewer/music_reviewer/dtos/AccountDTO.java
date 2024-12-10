package com.musicreviewer.music_reviewer.dtos;

import com.musicreviewer.music_reviewer.entities.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private int id_account;
    private String creation_date;
    private int reviews_created;
    private int user_id_user;
    private int login_id_login;
    private int reviewCount; // Antal anmeldelser tilknyttet kontoen

    // Constructor for Account + reviewCount
    public AccountDTO(Account account, int reviewCount) {
        this.id_account = account.getId_account();
        this.creation_date = account.getCreation_date();
        this.reviews_created = account.getReviews_created();
        this.user_id_user = account.getUser_id_user();
        this.login_id_login = account.getLogin_id_login();
        this.reviewCount = reviewCount;
    }

    // Valgfri metode til at konvertere tilbage til en Account entity
    public Account toEntity() {
        Account account = new Account();
        account.setId_account(this.id_account);
        account.setCreation_date(this.creation_date);
        account.setReviews_created(this.reviews_created);
        account.setUser_id_user(this.user_id_user);
        account.setLogin_id_login(this.login_id_login);
        return account;
    }
}
