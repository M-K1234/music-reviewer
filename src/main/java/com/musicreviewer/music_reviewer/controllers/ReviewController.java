package com.musicreviewer.music_reviewer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicreviewer.music_reviewer.dtos.ReviewDTO;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.services.ReviewService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    @GetMapping("/{id}")
    public ReviewDTO get(@PathVariable int id) {
        ReviewDTO reviewDto = reviewService.getReviewById(id);
        return reviewDto;
    }

    @PostMapping("/create")
    public ReviewDTO add(@RequestBody Review review) {
        ReviewDTO reviewDto = reviewService.addReview(review);
        return reviewDto;
    }
}