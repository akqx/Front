package com.qrelix.springbootmysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/users")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping(value="/all")
	public List<Book> getAll() {
		return bookRepository.findAll();
		
	}
	
	@PostMapping(value="/load")
	public List<Book> persist(@RequestBody final Book book){
		bookRepository.save(book);
		return bookRepository.findAll();
	}

}
