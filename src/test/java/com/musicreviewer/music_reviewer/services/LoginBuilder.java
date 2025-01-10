package com.musicreviewer.music_reviewer.services;

import com.musicreviewer.music_reviewer.entities.Login;

public class LoginBuilder {
    private int id = 1;
    private String email = "default@example.com";
    private String password = "defaultPassword";

    private LoginBuilder() {
    }

    public static LoginBuilder create() {
        return new LoginBuilder();
    }

    public LoginBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public LoginBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Login build() {
        return new Login(id, email, password);
    }
}
