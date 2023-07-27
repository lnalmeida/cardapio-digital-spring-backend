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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lnalmeida.cardapio.dto.CategoryDTO;
import com.lnalmeida.cardapio.dto.FoodDTO;
import com.lnalmeida.cardapio.entities.Category;
import com.lnalmeida.cardapio.entities.Food;
import com.lnalmeida.cardapio.service.CategoryService;
import com.lnalmeida.cardapio.service.FoodService;


@RestController
@RequestMapping(value="api/cardapio")
public class FoodController {
	
	@Autowired
	FoodService foodService;
	@Autowired
	CategoryService categoryService;
		
	@GetMapping(value="/food")
	public List<FoodDTO> getAllFoods() {
		List<FoodDTO> foodList = foodService.findAllFoods();
		return foodList;
	}
	
	@GetMapping(value= "/food/{id}")
	public ResponseEntity<FoodDTO> getFoodById(@PathVariable Long id) {
		Optional<FoodDTO> food = foodService.findFoodById(id);
		if(food.isPresent()) {
			return ResponseEntity.ok(food.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value="/food/bycategory")
	public List<FoodDTO> getFoodsByCategoryId(@RequestParam Long categoryId) {
		List<FoodDTO> foodList = foodService.findFoodByCategoryId(categoryId);
		return foodList;
	}
	

	@PostMapping(value="/food")
	public ResponseEntity<String> postNewFood(@RequestBody Food body) {
		Long categoryId = body.getCategory().getId();
		
		Optional<CategoryDTO> category = categoryService.findCategoryById(categoryId);
		
		if(!category.isPresent()) return ResponseEntity.badRequest().body("Category not found");

		Category newCategory = new Category(categoryId);

		body.setCategory(newCategory);
			
		try {
			
			foodService.createNewFood(body);
			return ResponseEntity.ok("Register created successfully - "+body.getCategory().getId());
		} catch (Exception e) {
			return (ResponseEntity<String>) ResponseEntity
					.internalServerError()
					.body("Error on create register.");
		}		
	}
	
	@PutMapping(value="/food/{id}")
	public ResponseEntity<FoodDTO> putFood(@PathVariable Long id, @RequestBody Food body) {
		
		Optional<FoodDTO> food = foodService.editFood(id, body);
		if(food.isPresent()) {
			
			return ResponseEntity.ok(food.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value="/food/{id}")
	public void deleteFood(@PathVariable Long id) {
		foodService.deleteFood(id);
	}
}
