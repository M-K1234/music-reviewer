package com.musicreviewer.music_reviewer.repositories;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicreviewer.music_reviewer.entities.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {
}
