package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.ProductServiceClient;

@Service
public class ProductService {

	@Autowired
	private ProductServiceClient productServiceClient;
	
	public int checkProduct(Integer id) {
		return productServiceClient.fetchProduct(id);
	}
	
}