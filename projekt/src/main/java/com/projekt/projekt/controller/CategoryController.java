package com.projekt.projekt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekt.projekt.exception.ResourceNotFoundException;
import com.projekt.projekt.model.Book;
import com.projekt.projekt.model.Category;
import com.projekt.projekt.repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	
    // Get All books
	@GetMapping("/categories")
	public List<Category> getAllCategories() {
	    return categoryRepository.findAll();
	}
	
	@GetMapping("/categories/{id}")
	public Category getCategoryById(@PathVariable(value = "id") Long idcategory) {
	    return categoryRepository.findById(idcategory)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "id", idcategory));
	}
}
