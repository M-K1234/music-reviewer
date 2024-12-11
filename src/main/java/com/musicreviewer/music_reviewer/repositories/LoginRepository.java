package com.musicreviewer.music_reviewer.repositories;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicreviewer.music_reviewer.entities.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByUsername(String username); // Find login via brugernavn
}
