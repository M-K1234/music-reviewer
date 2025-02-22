package com.musicreviewer.music_reviewer.controllers;

import com.musicreviewer.music_reviewer.dtos.NewsDTO;
import com.musicreviewer.music_reviewer.entities.News;
import com.musicreviewer.music_reviewer.services.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    NewsService newsService;

    // Hent alle nyheder
    @GetMapping("/all")
    public List<NewsDTO> getAll() {
        List<NewsDTO> newsDTOs = newsService.getAllNews();
        return newsDTOs;
    }

    // Hent en nyhed via ID
    @GetMapping("/{id}")
    public NewsDTO get(@PathVariable int id) {
        NewsDTO newsDTO = newsService.getNewsById(id);
        return newsDTO;
    }

    // Opret en ny nyhed
    @PostMapping("/create")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.createNews(news);
        return ResponseEntity.ok(createdNews);
    }

    // Opdater en eksisterende nyhed
    @PutMapping("/update/{id}")
    public ResponseEntity<News> updateReview(@PathVariable int id, @RequestBody News news) {
        News updatedNews = newsService.updateReview(id, news);
        return ResponseEntity.ok(updatedNews);
    }

    // Slet en nyhed
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        newsService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}
