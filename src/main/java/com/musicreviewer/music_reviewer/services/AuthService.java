package com.musicreviewer.music_reviewer.services;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.entities.Login;
import com.musicreviewer.music_reviewer.entities.User;
import com.musicreviewer.music_reviewer.repositories.AccountRepository;
import com.musicreviewer.music_reviewer.repositories.LoginRepository;
import com.musicreviewer.music_reviewer.repositories.UserRepository;
import com.musicreviewer.music_reviewer.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public Map<String, Object> authenticateAndGenerateTokenResponse(String username, String password) {
        Login login = loginRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
    
        if (!passwordEncoder.matches(password, login.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
    
        return generateTokenResponse(username);
    }

    public void register(String fullName, String email, String username, String password) {
        // Check if username is already taken
        if (loginRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        // Create and save Login
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(passwordEncoder.encode(password));
        loginRepository.save(login);

        // Create and save User
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setLogin(login);
        userRepository.save(user);

        // Create and save Account
        Account account = new Account();
        account.setUser(user);
        account.setCreationDate(LocalDateTime.now());
        account.setReviewsCreated(0);
        accountRepository.save(account);
    }

    public Map<String, Object> generateTokenResponse(String username) {
        String token = jwtUtil.generateToken(username);
        Date expiration = jwtUtil.getTokenExpiration(token);
        return Map.of("token", token, "expiresAt", expiration.getTime());
    }
    
}

