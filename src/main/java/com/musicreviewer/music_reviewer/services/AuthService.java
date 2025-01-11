package com.musicreviewer.music_reviewer.services;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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

    public Map<String, Object> authenticateAndGenerateTokenResponse(String email, String password) {
        Login login = loginRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
    
        if (!passwordEncoder.matches(password, login.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
    
        return generateTokenResponse(email);
    }

    public boolean isCurrentUserEmail(String email, String currentEmail) {
        return currentEmail.equals(email);
    }
    
    public boolean isCurrentUserUsername(String username, String currentEmail) {
        return accountRepository.findByLoginEmail(currentEmail)
                .map(account -> account.getUser().getUsername().equals(username))
                .orElse(false);
    }

    public String getUsernameByEmail(String email) {
        return accountRepository.findByLoginEmail(email)
                .map(account -> account.getUser().getUsername())
                .orElse(null);
    }
    
    
    @Transactional
    public void register(String fullName, String email, String username, String password) {
        // Check if username is already taken
        if (loginRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        // Create and save Login
        Login login = new Login();
        login.setEmail(email);
        login.setPassword(passwordEncoder.encode(password));
        loginRepository.save(login);

        // Create and save User
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        userRepository.save(user);

        // Create and save Account
        Account account = new Account();
        account.setUser(user);
        account.setLogin(login);
        account.setCreationDate(LocalDateTime.now());
        account.setReviewsCreated(0);
        accountRepository.save(account);
    }

    public boolean checkEmailExists(String email) {
        return loginRepository.findByEmail(email).isPresent();
    }

    public boolean checkUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Map<String, Object> generateTokenResponse(String username) {
        String token = jwtUtil.generateToken(username);
        Date expiration = jwtUtil.getTokenExpiration(token);
        return Map.of("token", token, "expiresAt", expiration.getTime());
    }
}

