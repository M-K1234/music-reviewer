package com.musicreviewer.music_reviewer.controllers;

import com.musicreviewer.music_reviewer.dtos.ReviewDTO;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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