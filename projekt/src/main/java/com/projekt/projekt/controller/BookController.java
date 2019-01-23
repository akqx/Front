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

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	// Get All books
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	// Create a new book
	// http://localhost:8080/api/books
	// example: {"name":"name", "year":"2019","author":"author", "note":"note",
	// "language":"language"}
	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}

	// Get a Single book
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable(value = "id") Long idbook) {
		return bookRepository.findById(idbook).orElseThrow(() -> new ResourceNotFoundException("Book", "id", idbook));
	}

	// Update a book
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable(value = "id") Long idbook, @Valid @RequestBody Book bookDetails) {

		Book book = bookRepository.findById(idbook)
				.orElseThrow(() -> new ResourceNotFoundException("book", "id", idbook));

		book.setAuthor(bookDetails.getAuthor());
		book.setName(bookDetails.getName());
		book.setNote(bookDetails.getNote());
		book.setYear(bookDetails.getYear());
		book.setLanguage(bookDetails.getLanguage());

		Book updatedbook = bookRepository.save(book);
		return updatedbook;
	}

	// Delete a book
	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deletebook(@PathVariable(value = "id") Long idbook) {
		Book book = bookRepository.findById(idbook)
				.orElseThrow(() -> new ResourceNotFoundException("book", "id", idbook));

		bookRepository.delete(book);

		return ResponseEntity.ok().build();
	}
}
