package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.ProductServiceClient;
import com.example.demo.controller.RatingController;
import com.example.demo.model.ProductResponse;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductServiceClient productServiceClient;
	
	public int checkProduct(Integer id) {
		logger.info("service"+id);
		return productServiceClient.fetchProduct(id);
	}
	
}
