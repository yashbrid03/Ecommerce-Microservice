package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Rating;
import com.example.demo.repository.RatingRepository;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	
	@PostMapping
    public ResponseEntity<Rating> addReview(@RequestBody Rating rating) {
        rating.setCreatedAt(LocalDateTime.now());
        rating.setUpdatedAt(LocalDateTime.now());
        Rating savedReview = ratingRepository.save(rating);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }
	
	@GetMapping("/{reviewId}")
    public ResponseEntity<Rating> getReviewById(@PathVariable Integer ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if (rating.isPresent()) {
            return new ResponseEntity<>(rating.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	 @GetMapping("/product/{productId}")
	 public ResponseEntity<List<Rating>> getReviewsByProductId(@PathVariable Integer productId) {
	        List<Rating> reviews = ratingRepository.findByProductId(productId);
	        return new ResponseEntity<>(reviews, HttpStatus.OK);
	 }
	 
	 @GetMapping("/user/{userId}")
	    public ResponseEntity<List<Rating>> getReviewsByUserId(@PathVariable Integer userId) {
	        List<Rating> reviews = ratingRepository.findByUserId(userId);
	        return new ResponseEntity<>(reviews, HttpStatus.OK);
	    }
	 
	 @PutMapping("/{reviewId}")
	    public ResponseEntity<Rating> updateReview(@PathVariable Integer reviewId, @RequestBody Rating reviewDetails) {
	        Optional<Rating> optionalRating = ratingRepository.findById(reviewId);
	        if (optionalRating.isPresent()) {
	        	Rating review = optionalRating.get();
	            review.setRating(reviewDetails.getRating());
	            review.setComment(reviewDetails.getComment());
	            review.setUpdatedAt(LocalDateTime.now());
	            Rating updatedReview = ratingRepository.save(review);
	            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @DeleteMapping("/{reviewId}")
	    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
	        Optional<Rating> optionalReview = ratingRepository.findById(reviewId);
	        if (optionalReview.isPresent()) {
	            ratingRepository.delete(optionalReview.get());
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
