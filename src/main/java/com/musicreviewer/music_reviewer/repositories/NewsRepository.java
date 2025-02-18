package com.musicreviewer.music_reviewer.repositories;

import com.musicreviewer.music_reviewer.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {

}
