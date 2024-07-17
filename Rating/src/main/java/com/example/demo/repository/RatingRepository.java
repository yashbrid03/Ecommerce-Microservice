package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Rating;


public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findByProductId(Integer productId);
    List<Rating> findByUserId(Integer userId);
}
