package com.musicreviewer.music_reviewer.repositories;

import com.musicreviewer.music_reviewer.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByEmail(String email); // Find login via brugernavn
}
