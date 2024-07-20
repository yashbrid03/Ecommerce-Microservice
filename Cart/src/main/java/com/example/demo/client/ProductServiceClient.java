package com.example.demo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ProductResponse;

@Component
public class ProductServiceClient {
	@Autowired
	private RestTemplate template;
	
	public int fetchProduct(Integer id) {
		 try {
		        ProductResponse response = template.getForObject("http://localhost:8765/product/" + id, ProductResponse.class);
		        if (response != null) {
		            return 1;
		        }
		    } catch (Exception e) {
		    }
		    return 0;
	}
}