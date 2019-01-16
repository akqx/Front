package com.projekt.projekt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekt.projekt.exception.ResourceNotFoundException;
import com.projekt.projekt.model.Book;
import com.projekt.projekt.model.User;
import com.projekt.projekt.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	// Get All users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Create a new user
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
		@GetMapping("/users/{id}")
		public User getUserById(@PathVariable(value = "id") Long iduser) {
		    return userRepository.findById(iduser)
		            .orElseThrow(() -> new ResourceNotFoundException("User", "id", iduser));
		}

	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long iduser,
	                                        @Valid @RequestBody User userDetails) {

	    User user = userRepository.findById(iduser)
	            .orElseThrow(() -> new ResourceNotFoundException("user", "id", iduser));

	    user.setFirstname(userDetails.getFirstname());
	    user.setSurname(userDetails.getSurname());
	    user.setEmail(userDetails.getEmail());
	    user.setPassword(userDetails.getPassword());

	    
	    User updateduser = userRepository.save(user);
	    return updateduser;
	}
}
