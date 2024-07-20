package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

	@PostMapping("/")
	public ResponseEntity<Object> addReview(@RequestBody Rating rating) {
		try {
			logger.info("id = " + rating.getProductId());
			int pCheck = productService.checkProduct(rating.getProductId());
			logger.info("" + pCheck);
			if (pCheck == 0) {
				return new ResponseEntity<>("no such product", HttpStatus.BAD_REQUEST);
			}
			rating.setCreatedAt(LocalDateTime.now());
			rating.setUpdatedAt(LocalDateTime.now());
			Rating savedReview = ratingRepository.save(rating);
			return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Rating> getReviewById(@PathVariable Integer ratingId) {
		try {
			Optional<Rating> rating = ratingRepository.findById(ratingId);
			if (rating.isPresent()) {
				return new ResponseEntity<>(rating.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getReviewsByProductId(@PathVariable Integer productId) {
		try {
			List<Rating> reviews = ratingRepository.findByProductId(productId);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getReviewsByUserId(@PathVariable Integer userId) {
		try {
			List<Rating> reviews = ratingRepository.findByUserId(userId);
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<Rating> updateReview(@PathVariable Integer reviewId, @RequestBody Rating reviewDetails) {
		try {
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
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
		try {
			Optional<Rating> optionalReview = ratingRepository.findById(reviewId);
			if (optionalReview.isPresent()) {
				ratingRepository.delete(optionalReview.get());
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
