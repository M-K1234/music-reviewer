package com.musicreviewer.music_reviewer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicreviewer.music_reviewer.entities.Review;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
    // Find all reviews by a specific user
    List<Review> findByUserId(int userId);
    
    // Find reviews by title (partial match)
    List<Review> findByTitleContaining(String title);

    // Find reviews by score
    List<Review> findByScore(int score);

    // Find a review by its ID
    Optional<Review> findById(int id);

    // Find reviews created after a specific date
    List<Review> findByCreationDateAfter(Date creationDate);
}

