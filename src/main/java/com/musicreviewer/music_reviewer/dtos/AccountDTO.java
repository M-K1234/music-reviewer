package com.musicreviewer.music_reviewer.dtos;

import java.time.LocalDateTime;

// import com.musicreviewer.music_reviewer.entities.Account;

// import lombok.Getter;
// import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private int id; // Matcher "id" i Account
    private String creationDate; // Bruger String, men husk at mappe fra LocalDateTime
    private int reviewsCreated; // Matcher "reviewsCreated"
    private int userId; // Refererer til "user" i Account
    private int loginId; // Refererer til "login" i Account
    private int reviewCount; // Antal anmeldelser tilknyttet kontoen

    // Constructor for Account + reviewCount
    public AccountDTO(Account account, int reviewCount) {
        this.id = account.getId();
        this.creationDate = account.getCreationDate().toString(); // Konverter LocalDateTime til String
        this.reviewsCreated = account.getReviewsCreated();
        this.userId = account.getUser().getId(); // Hent user-id
        this.loginId = account.getLogin().getId(); // Hent login-id
        this.reviewCount = reviewCount;
    }

    // Valgfri metode til at konvertere tilbage til en Account entity
    public Account toEntity() {
        Account account = new Account();
        account.setId(this.id);
        account.setCreationDate(LocalDateTime.parse(this.creationDate)); // Konverter String til LocalDateTime
        account.setReviewsCreated(this.reviewsCreated);
        // Bemærk: User og Login skal sættes med faktiske objekter
        return account;
    }
}
