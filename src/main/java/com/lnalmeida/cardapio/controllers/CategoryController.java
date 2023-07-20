package com.lnalmeida.cardapio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnalmeida.cardapio.dto.CategoryDTO;
import com.lnalmeida.cardapio.entities.Category;
import com.lnalmeida.cardapio.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public List<CategoryDTO> getAllCategories() {
		List<CategoryDTO> categories = categoryService.findAllCategories();
		return categories;
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
		Optional<CategoryDTO> category = categoryService.findCategoryById(id);
		if(category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<String> postNewCategory(@RequestBody Category body) {
		try {
			categoryService.createNewCategory(body);
			return ResponseEntity.ok("Register created successfully");
		} catch (Exception e) {
			return (ResponseEntity<String>) ResponseEntity
					.internalServerError()
					.body("Error on create register.");
		}		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> putCategory(@PathVariable Long id, @RequestBody Category body) {
		Optional<CategoryDTO> category = categoryService.editCategory(id, body);
		if(category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
	
}
