package com.musicreviewer.music_reviewer.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicreviewer.music_reviewer.dtos.NewsDTO;
import com.musicreviewer.music_reviewer.services.NewsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    NewsService newsService;

    @GetMapping("/all")
    public List<NewsDTO> getAll() {
        List<NewsDTO> newsDTOs = newsService.getAllNews();
        return newsDTOs;
    }

    @GetMapping("/{id}")
    public NewsDTO get(@PathVariable int id) {
        NewsDTO newsDTO = newsService.getNewsById(id);
        return newsDTO;
    }
    
}
