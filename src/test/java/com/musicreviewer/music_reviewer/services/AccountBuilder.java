package com.musicreviewer.music_reviewer.services;

import com.github.javafaker.Faker;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.entities.Login;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountBuilder {
    private int id = 1;
    private LocalDateTime creationDate = LocalDateTime.now();
    private int reviewsCreated = 0;
    private User user = UserBuilder.create().build();
    private Login login = LoginBuilder.create().build();
    private List<com.musicreviewer.music_reviewer.entities.Review> reviews = new ArrayList<>();
    private final Faker faker = new Faker();

    private AccountBuilder() {}

    public static AccountBuilder create() {
        return new AccountBuilder();
    }

    public AccountBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public AccountBuilder withReviewsCreated(int reviewsCreated) {
        this.reviewsCreated = reviewsCreated;
        return this;
    }

    public AccountBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public AccountBuilder withLogin(Login login) {
        this.login = login;
        return this;
    }

    public AccountBuilder withEmail(String email) {
        this.login.setEmail(email);
        return this;
    }

    public AccountBuilder withEmail() {
        return withEmail(faker.internet().emailAddress());
    }

    public AccountBuilder withUsername(String username) {
        this.user.setUsername(username);
        return this;
    }

    public AccountBuilder withUsername() {
        return withUsername(faker.name().username());
    }

    public AccountBuilder withFullName(String fullName) {
        this.user.setFullName(fullName);
        return this;
    }

    public AccountBuilder withFullName() {
        return withFullName(faker.name().fullName());
    }

    public AccountBuilder withPassword(String password) {
        this.login.setPassword(password);
        return this;
    }

    public AccountBuilder withReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Account build() {
        Account account = new Account(id, creationDate, reviewsCreated, user, login);
        account.setReviews(reviews);
        return account;
    }
}
