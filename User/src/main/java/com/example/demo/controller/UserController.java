package com.example.demo.controller;

import java.time.LocalDateTime;

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
import com.example.demo.model.Login;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/profile/{userId}")
	public ResponseEntity<Object> getProfile(@PathVariable("userId") Integer userId) {
		try {
			User user = userRepository.getUser(userId);
			if (user == null) {
				return new ResponseEntity<>("No such user", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Inside", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/profile")
	public ResponseEntity<Object> createProfile(@RequestBody User user) {
		try {
			user.setCreatedAt(LocalDateTime.now());
			user.setUpdatedAt(LocalDateTime.now());
			userRepository.save(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Inside", e);
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/profile/{userId}")
	public ResponseEntity<Object> updateProfile(@RequestBody User user, @PathVariable("userId") Integer userId) {
		try {

			User curr_user = userRepository.getUser(userId);
			if (user == null) {
				return new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
			}
			if (user.getUsername() != null) {
				curr_user.setUsername(user.getUsername());
			}
			if (user.getEmail() != null) {
				curr_user.setEmail(user.getEmail());
			}
			if (user.getPasswordHash() != null) {
				curr_user.setPasswordHash(user.getPasswordHash());
			}
			if (user.getFirstName() != null) {
				curr_user.setFirstName(user.getFirstName());
			}
			if (user.getLastName() != null) {
				curr_user.setLastName(user.getLastName());
			}
			curr_user.setUpdatedAt(LocalDateTime.now());
			userRepository.update(curr_user);
			return new ResponseEntity<>(curr_user, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Inside", e);
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @PostMapping("/login")
	// public ResponseEntity<Object> login(@RequestBody Login login) {
	// try {
	// User user = userRepository.getUserByUsername(login.getUsername());
	// logger.info("uname : "+login.getUsername()+ " pass: "+login.getPassword());
	// if (user != null && login.getPassword().equals(user.getPasswordHash()) ) {
	// return new ResponseEntity<>("Login successful", HttpStatus.OK);
	// } else {
	// return new ResponseEntity<>("Invalid username or password",
	// HttpStatus.UNAUTHORIZED);
	// }
	// } catch (Exception e) {
	// logger.error("Error during login", e);
	// return new ResponseEntity<>(e.getMessage(),
	// HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	// }

}
