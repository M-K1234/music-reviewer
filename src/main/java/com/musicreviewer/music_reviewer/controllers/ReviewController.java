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
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO get(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping("/create")
    public ReviewDTO add(@RequestBody Review review) {
        return reviewService.addReview(review);
    }
}