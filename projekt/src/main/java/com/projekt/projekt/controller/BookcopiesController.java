package com.projekt.projekt.controller;

import com.projekt.projekt.*;
import com.projekt.projekt.exception.ResourceNotFoundException;
import com.projekt.projekt.model.Bookcopies;
import com.projekt.projekt.repository.BookcopiesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookcopiesController {

	@Autowired
	BookcopiesRepository bookcopiesRepository;

	// Get All bookcopies
	@GetMapping("/bookcopies")
	public List<Bookcopies> getAllBookcopies() {
		return bookcopiesRepository.findAll();
	}

	// Create a new bookcopies
	// http://localhost:8080/api/bookcopies
	@PostMapping("/bookcopies")
	public Bookcopies createBookcopies(@Valid @RequestBody Bookcopies bookcopies) {
		return bookcopiesRepository.save(bookcopies);
	}

	// Get a Single bookcopies
	@GetMapping("/bookcopies/{id}")
	public Bookcopies getBookcopiesById(@PathVariable(value = "id") Long idbookcopies) {
		return bookcopiesRepository.findById(idbookcopies)
				.orElseThrow(() -> new ResourceNotFoundException("Bookcopies", "id", idbookcopies));
	}

	// Update a bookcopies
	@PutMapping("/bookcopies/{id}")
	public Bookcopies updateBookcopies(@PathVariable(value = "id") Long idbookcopies,
			@Valid @RequestBody Bookcopies bookcopiesDetails) {

		Bookcopies bookcopies = bookcopiesRepository.findById(idbookcopies)
				.orElseThrow(() -> new ResourceNotFoundException("bookcopies", "id", idbookcopies));

		bookcopies.setCopiesnumber(bookcopiesDetails.getCopiesnumber());
		bookcopies.setIdbook(bookcopiesDetails.getIdbook());

		Bookcopies updatedbookcopies = bookcopiesRepository.save(bookcopies);
		return updatedbookcopies;
	}

	// Delete a bookcopies
	@DeleteMapping("/bookcopies/{id}")
	public ResponseEntity<?> deletebookcopies(@PathVariable(value = "id") Long idbookcopies) {
		Bookcopies bookcopies = bookcopiesRepository.findById(idbookcopies)
				.orElseThrow(() -> new ResourceNotFoundException("bookcopies", "id", idbookcopies));

		bookcopiesRepository.delete(bookcopies);

		return ResponseEntity.ok().build();
	}

	// Update a bookcopies, send querty
	@PutMapping("/bookcopies/{idBook}/{copiesnumberDiffrence}")
	public Bookcopies updateBookcopiesSendQuerty(@PathVariable("idBook") Integer idBook,
			@PathVariable("copiesnumberDiffrence") Integer copiesnumberDiffrence,
			@Valid @RequestBody Bookcopies bookcopiesDetails) {

		
		Bookcopies bookcopies = bookcopiesRepository.findByidbook(idBook);
		bookcopies.setCopiesnumber(bookcopies.updateBookCopiesDifference(copiesnumberDiffrence));

		Bookcopies updatedbookcopies = bookcopiesRepository.save(bookcopies);
		return updatedbookcopies;

	}

}
