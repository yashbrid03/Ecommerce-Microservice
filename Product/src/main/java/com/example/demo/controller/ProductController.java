package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@RequestMapping("/product")
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public ResponseEntity<List<Object>> getAllProducts() {
        try {
            List<Product> product = productRepository.findAll();
            if (product.isEmpty()) {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdatedAt(LocalDateTime.now());
            logger.info("inside : " + product);
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("error: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        try {
            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.info("error: " + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Integer categoryId) {
        try {
            Category category = categoryRepository.findById(categoryId);
            if (category != null) {
                List<Product> products = productRepository.findByCategory(category.getCategoryId());
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategories() {
        try {
            List<Category> categories = categoryRepository.getAllCategories();
            if (categories.isEmpty()) {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        try {
            Product curr_product = productRepository.findById(productId);
            if (curr_product != null) {
                return new ResponseEntity<>(curr_product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductById(@RequestBody Product product, @PathVariable Integer productId) {
        try {
            Product curr_product = productRepository.findById(productId);
            if (curr_product != null) {
                if (product.getName() != null) {
                    curr_product.setName(product.getName());
                }
                if (product.getDescription() != null) {
                    curr_product.setDescription(product.getDescription());
                }
                if (product.getPrice() != null) {
                    curr_product.setPrice(product.getPrice());
                }
                if (product.getQuantity() != null) {
                    curr_product.setQuantity(product.getQuantity());
                }
                if (product.getImageUrl() != null) {
                    curr_product.setImageUrl(product.getImageUrl());
                }
                if (product.getCategoryId() != null) {
                    curr_product.setCategoryId(product.getCategoryId());
                }

                curr_product.setUpdatedAt(LocalDateTime.now());
                logger.info("price: " + curr_product.getPrice());
                productRepository.update(curr_product);
                return new ResponseEntity<>(curr_product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
