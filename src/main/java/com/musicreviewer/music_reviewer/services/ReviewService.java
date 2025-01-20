package com.musicreviewer.music_reviewer.services;

import com.musicreviewer.music_reviewer.config.ModelMapperConfig;
import com.musicreviewer.music_reviewer.dtos.ReviewDTO;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.repositories.ReviewRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapperConfig modelMapperConfig;


    public ReviewDTO mapToDTO(Review review) {
        return modelMapperConfig.modelMapper().map(review, ReviewDTO.class);
    }

    public List<ReviewDTO> mapToDTOList(List<Review> reviews) {
        return reviews.stream()
                      .map(this::mapToDTO)
                      .collect(Collectors.toList());
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return mapToDTOList(reviews);
    }

    public ReviewDTO getReviewById(int reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return mapToDTO(review);
    }

    public ReviewDTO addReview(Review newReview) {
        reviewRepository.save(newReview);
        return mapToDTO(newReview);
    }
}
