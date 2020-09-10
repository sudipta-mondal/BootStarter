package com.sud.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.sud.restservices.entities.User;
import com.sud.restservices.exceptions.UserExistsException;
import com.sud.restservices.exceptions.UserNotFoundException;
import com.sud.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException userExistsException) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, userExistsException.getMessage());
		}
	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserbyId(@PathVariable("id") Long id) {
		try {
			return userService.getUserbyId(id);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, userNotFoundException.getMessage());
		}
	}

	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserbyId(id, user);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage());
		}

	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		try {
			userService.deleteUserbyId(id);
		} catch (UserNotFoundException userNotFoundException) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage());
		}
	}

	@GetMapping("/users/getUserByUserName/{userName}")
	public User getUserByUserName(@PathVariable("userName") String userName) {
		return userService.getUserbyUserName(userName);
	}
}
