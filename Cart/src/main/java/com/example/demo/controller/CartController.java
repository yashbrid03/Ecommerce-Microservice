package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartService cartService;

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@PostMapping("/{userId}")
	public ResponseEntity<String> postCall(@PathVariable Integer userId, @RequestBody CartItem cartItem) {
		return cartService.addToCart(userId, cartItem);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<CartItem>> getCall(@PathVariable Integer userId) {
		return cartService.getCartItem(userId);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<String> putCall(@PathVariable Integer userId, @RequestBody CartItem cartItem) {
		return cartService.updateCartItem(userId, cartItem);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteCall(@PathVariable Integer userId, @RequestBody Integer cartItemId) {
		return cartService.removeItemFromCart(userId, cartItemId);
	}

}
