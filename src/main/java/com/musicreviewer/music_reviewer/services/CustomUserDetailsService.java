package com.musicreviewer.music_reviewer.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.entities.Login;
import com.musicreviewer.music_reviewer.repositories.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;

    public CustomUserDetailsService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = loginRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));
        return org.springframework.security.core.userdetails.User
                .withUsername(login.getEmail())
                .password(login.getPassword())
                .authorities("USER") // Add roles/authorities as needed
                .build();
    }
}

