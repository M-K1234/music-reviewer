package com.musicreviewer.music_reviewer.services;

import com.musicreviewer.music_reviewer.entities.User;

public class UserBuilder {
    private int id = 1;
    private String fullName = "Default FullName";
    private String username = "defaultUsername";

    private UserBuilder() {
    }

    public static UserBuilder create() {
        return new UserBuilder();
    }

    public UserBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public User build() {
        return new User(id, fullName, username);
    }
}
