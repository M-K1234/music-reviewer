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

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return mapToDTOList(reviews);
    }

    public ReviewDTO getReviewById(int reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return mapToDTO(review);
    }

    public Review updateReview(int reviewId, Review updatedReview) {
        

        if (reviewRepository.existsById(reviewId)) {
           Review review = reviewRepository.findById(reviewId).get();
            review.setText(updatedReview.getText());  
        review.setAuthor(updatedReview.getAuthor());
        review.setScore(updatedReview.getScore()); 
        review.setTitle(updatedReview.getTitle()); 
        review.setImgurl(updatedReview.getImgurl());   
        review.setCreation_date(updatedReview.getCreation_date());   
        return reviewRepository.save(review);
        } else
        {
            throw new RuntimeException("Review not found with id: " + reviewId);
        }
        
                
    }

    public ReviewDTO addReview(Review newReview) {

        reviewRepository.save(newReview);
        ReviewDTO reviewDTO = mapToDTO(newReview);
        return reviewDTO;
       
        
        
                
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
