package com.musicreviewer.music_reviewer.repositories;

import com.musicreviewer.music_reviewer.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}