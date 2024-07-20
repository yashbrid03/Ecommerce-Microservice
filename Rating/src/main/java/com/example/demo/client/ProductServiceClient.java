package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ProductResponse;
import com.example.demo.service.ProductService;

@Component
public class ProductServiceClient {
	@Autowired
	private RestTemplate template;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public int fetchProduct(Integer id) {
		 try {
		        ProductResponse response = template.getForObject("http://PRODUCT-SERVICE/product/" + id, ProductResponse.class);
		        logger.info("client"+response);
		        if (response != null) {
		            return 1;
		        }
		    } catch (Exception e) {
		    	logger.info(""+e);
		    }
		    return 0;
	}
}
