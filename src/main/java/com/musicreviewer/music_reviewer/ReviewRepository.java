package com.musicreviewer.music_reviewer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicreviewer.music_reviewer.entities.Review;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
}

