package com.musicreviewer.music_reviewer.controllers;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.musicreviewer.music_reviewer.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            Map<String, Object> tokenResponse = authService.authenticateAndGenerateTokenResponse(email, password);
            return ResponseEntity.ok(tokenResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }



    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password) {
        try {
            authService.register(fullName, email, username, password);
            return ResponseEntity.ok("Registration successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

