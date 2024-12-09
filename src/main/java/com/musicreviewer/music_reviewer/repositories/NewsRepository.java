package com.musicreviewer.music_reviewer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicreviewer.music_reviewer.entities.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
    
}
