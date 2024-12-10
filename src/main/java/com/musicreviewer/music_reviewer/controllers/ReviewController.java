package com.musicreviewer.music_reviewer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicreviewer.music_reviewer.ReviewRepository;
import com.musicreviewer.music_reviewer.dtos.ReviewDTO;
import com.musicreviewer.music_reviewer.services.ReviewService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    ReviewService reviewService;

    @GetMapping("/all")
    public List<ReviewDTO> getAll() {
        List<ReviewDTO> reviewDto = reviewService.getAllReviews();
        return reviewDto;
    }
    

}