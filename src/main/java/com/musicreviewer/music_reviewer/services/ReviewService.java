package com.musicreviewer.music_reviewer.services;

import com.musicreviewer.music_reviewer.config.ModelMapperConfig;
import com.musicreviewer.music_reviewer.dtos.ReviewDTO;
import com.musicreviewer.music_reviewer.entities.Review;
import com.musicreviewer.music_reviewer.repositories.ReviewRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    // Create a new review
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(int reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public Review updateReview(int reviewId, Review updatedReview) {
        

        if (reviewRepository.existsById(reviewId)) {
           Review review = reviewRepository.findById(reviewId).get();
            review.setText(updatedReview.getText());  
        review.setAuthor(updatedReview.getAuthor());
        review.setScore(updatedReview.getScore()); 
        review.setTitle(updatedReview.getTitle()); 
        review.setImgURL(updatedReview.getImgURL());      
        return reviewRepository.save(review);
        } else
        {
            throw new RuntimeException("Review not found with id: " + reviewId);
        }
        
                
    }

    public void deleteReview(int reviewId) {
        if (reviewRepository.existsById(reviewId)) 
        {
            reviewRepository.deleteById(reviewId);
        } else 
        {
            throw new RuntimeException("Review not found with id: " + reviewId);
        }
    }
}
