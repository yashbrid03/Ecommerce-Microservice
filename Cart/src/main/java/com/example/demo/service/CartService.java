package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.controller.CartController;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	public ResponseEntity<String> addToCart(Integer userId, CartItem cartItem) {
		try {
			int pCheck = productService.checkProduct(cartItem.getProductId());
			if (pCheck == 0) {
				return new ResponseEntity<>("no such product", HttpStatus.BAD_REQUEST);
			}
			Cart cart = cartRepository.findByUserId(userId);
			if (cart == null) {
				cart = new Cart();
				cart.setUserId(userId);
				cart.setCreatedAt(LocalDateTime.now());
				cart.setUpdatedAt(LocalDateTime.now());
				cartRepository.save(cart);
			}
			// code to check if productId exist or not
			cartItem.setShoppingCart(cart);
			cartItemRepository.save(cartItem);
			return new ResponseEntity<>("Item added successfully", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<CartItem>> getCartItem(Integer userId) {
		try {
			Cart cart = cartRepository.findByUserId(userId);
			if (cart != null) {
				return new ResponseEntity<>(cart.getItems(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> updateCartItem(Integer userId, CartItem cartItem) {
		try {
			Cart cart = cartRepository.findByUserId(userId);
			if (cart != null) {
				CartItem existingItem = cartItemRepository.findById(cartItem.getCartItemId()).orElse(null);
				if (existingItem != null) {
					existingItem.setQuantity(cartItem.getQuantity());
					cartItemRepository.save(existingItem);
					return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("no such existing item in cart", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>("cart doesnt exist", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<String> removeItemFromCart(Integer userId, Integer cartItemId) {
		try {
			Cart cart = cartRepository.findByUserId(userId);
			if (cart != null) {
				CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
				if (cartItem != null) {
					cartItemRepository.delete(cartItem);
					return new ResponseEntity<>("Item deleted from cart", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Item not found in cart", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>("cart doesnt exist", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
