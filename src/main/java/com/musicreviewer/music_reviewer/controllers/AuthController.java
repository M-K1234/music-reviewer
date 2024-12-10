package com.musicreviewer.music_reviewer.controllers;
import org.springframework.web.bind.annotation.*;
import com.musicreviewer.music_reviewer.services.AuthenticationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authenticationService.authenticate(username, password);
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        authenticationService.register(username, password);
        return "Registration successful";
    }
}

