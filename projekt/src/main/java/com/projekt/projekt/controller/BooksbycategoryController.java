package com.projekt.projekt.controller;

import com.projekt.projekt.*;
import com.projekt.projekt.exception.ResourceNotFoundException;
import com.projekt.projekt.model.Booksbycategory;
import com.projekt.projekt.repository.BooksbycategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksbycategoryController {
	
	@Autowired
	BooksbycategoryRepository booksbycategoryRepository;
	
    // Get All booksbycategory
	@GetMapping("/booksbycategory")
	public List<Booksbycategory> getAllBooksbycategorys() {
	    return booksbycategoryRepository.findAll();
	}

	
    // Create a new booksbycategory
	//http://localhost:8080/api/booksbycategorys
	//example: {"name":"name", "year":"2019","author":"author", "note":"note", "language":"language"} 
	@PostMapping("/booksbycategorys")
	public Booksbycategory createBooksbycategory(@Valid @RequestBody Booksbycategory booksbycategory) {
	    return booksbycategoryRepository.save(booksbycategory);
	}

    // Get a Single booksbycategory
	@GetMapping("/booksbycategorys/{id}")
	public Booksbycategory getBooksbycategoryById(@PathVariable(value = "id") Long idbooksbycategory) {
	    return booksbycategoryRepository.findById(idbooksbycategory)
	            .orElseThrow(() -> new ResourceNotFoundException("Booksbycategory", "id", idbooksbycategory));
	}

    // Update a booksbycategory
	@PutMapping("/booksbycategorys/{id}")
	public Booksbycategory updateBooksbycategory(@PathVariable(value = "id") Long idbooksbycategory,
	                                        @Valid @RequestBody Booksbycategory booksbycategoryDetails) {

	    Booksbycategory booksbycategory = booksbycategoryRepository.findById(idbooksbycategory)
	            .orElseThrow(() -> new ResourceNotFoundException("booksbycategory", "id", idbooksbycategory));

	    booksbycategory.setIdcategory(booksbycategoryDetails.getIdcategory());
	    booksbycategory.setIdbook(booksbycategoryDetails.getIdbook());

	    
	    Booksbycategory updatedbooksbycategory = booksbycategoryRepository.save(booksbycategory);
	    return updatedbooksbycategory;
	}

    // Delete a booksbycategory
	@DeleteMapping("/booksbycategorys/{id}")
	public ResponseEntity<?> deletebooksbycategory(@PathVariable(value = "id") Long idbooksbycategory) {
	    Booksbycategory booksbycategory = booksbycategoryRepository.findById(idbooksbycategory)
	            .orElseThrow(() -> new ResourceNotFoundException("booksbycategory", "id", idbooksbycategory));

	    booksbycategoryRepository.delete(booksbycategory);

	    return ResponseEntity.ok().build();
	}
}

