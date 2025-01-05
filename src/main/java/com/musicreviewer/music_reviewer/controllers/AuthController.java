package com.musicreviewer.music_reviewer.controllers;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.musicreviewer.music_reviewer.dtos.DuplicateCheckRequest;
import com.musicreviewer.music_reviewer.dtos.LoginDTO;
import com.musicreviewer.music_reviewer.dtos.RegistrationDTO;
import com.musicreviewer.music_reviewer.services.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        String email = login.getEmail();
        String password = login.getPassword();
        try {
            Map<String, Object> tokenResponse = authService.authenticateAndGenerateTokenResponse(email, password);
            return ResponseEntity.ok(tokenResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDTO registration, BindingResult result) {
        if (result.hasErrors()) {
            // Collect validation errors
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        String fullName = registration.getFullName();
        String email = registration.getEmail();
        String username = registration.getUsername();
        String password = registration.getPassword();
        authService.register(fullName, email, username, password);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken() {
        // If the JwtFilter runs and doesn't throw an exception, the token is valid
        return ResponseEntity.ok(true);
    }
    
    @PostMapping("/check-duplicates")
public ResponseEntity<?> checkDuplicates(@RequestBody DuplicateCheckRequest request, @AuthenticationPrincipal UserDetails userDetails) {
    String currentEmail = userDetails.getUsername(); // Assuming email is the username

    // Check if email or username is already taken
    boolean emailExists = !currentEmail.equals(request.getEmail()) && authService.checkEmailExists(request.getEmail());
    boolean usernameExists = !authService.isCurrentUserUsername(request.getUsername(), currentEmail) && authService.checkUsernameExists(request.getUsername());

    return ResponseEntity.ok(Map.of(
        "emailExists", emailExists,
        "usernameExists", usernameExists,
        "currentEmail", currentEmail, // Add current email
        "currentUsername", authService.getUsernameByEmail(currentEmail) // Add current username
    ));
}



    // @PostMapping("/register")
    // public ResponseEntity<String> register(
    //         @RequestParam String fullName,
    //         @RequestParam String email,
    //         @RequestParam String username,
    //         @RequestParam String password) {
    //     try {
    //         authService.register(fullName, email, username, password);
    //         return ResponseEntity.ok("Registration successful");
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }
}

