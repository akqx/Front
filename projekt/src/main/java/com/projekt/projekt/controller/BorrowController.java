package com.projekt.projekt.controller;

import com.projekt.projekt.*;
import com.projekt.projekt.exception.ResourceNotFoundException;
import com.projekt.projekt.model.Book;
import com.projekt.projekt.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import com.projekt.projekt.model.Borrow;
import com.projekt.projekt.repository.BorrowRepository;

@RestController
@RequestMapping("/api")
public class BorrowController {

	@Autowired
	BorrowRepository borrowRepository;

	// Get All borrows
	@GetMapping("/borrow")
	public List<Borrow> getAllBorrow() {
		return borrowRepository.findAll();
	}

	// Create a new borrow
	@PostMapping("/borrow")
	public Borrow createBook(@Valid @RequestBody Borrow borrow) {
		return borrowRepository.save(borrow);
	}

	// Get a Single borrow
	@GetMapping("/borrow/{id}")
	public Borrow getBorrowById(@PathVariable(value = "id") Long idborrow) {
		return borrowRepository.findById(idborrow)
				.orElseThrow(() -> new ResourceNotFoundException("borrow", "id", idborrow));
	}

	// Update a borrow
	@PutMapping("/borrow/{id}")
	public Borrow updateBorrow(@PathVariable(value = "id") Long idborrow, @Valid @RequestBody Borrow borrowDetails) {

		Borrow borrow = borrowRepository.findById(idborrow)
				.orElseThrow(() -> new ResourceNotFoundException("borrow", "id", idborrow));

		borrow.setDateborrowed(borrowDetails.getDateborrowed());
		borrow.setDatereturned(borrowDetails.getDatereturned());
		borrow.setIdbook(borrowDetails.getIdbook());
		borrow.setIduser(borrowDetails.getIduser());

		Borrow updatedborrow = borrowRepository.save(borrow);
		return updatedborrow;
	}

	// Delete a borrow
	@DeleteMapping("/borrow/{id}")
	public ResponseEntity<?> deleteborrow(@PathVariable(value = "id") Long idborrow) {
		Borrow borrow = borrowRepository.findById(idborrow)
				.orElseThrow(() -> new ResourceNotFoundException("borrow", "id", idborrow));

		borrowRepository.delete(borrow);

		return ResponseEntity.ok().build();
	}
}
