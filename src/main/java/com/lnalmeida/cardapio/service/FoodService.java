package com.lnalmeida.cardapio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnalmeida.cardapio.dto.FoodDTO;
import com.lnalmeida.cardapio.entities.Food;
import com.lnalmeida.cardapio.repository.FoodRepository;



@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;
	
	@Transactional(readOnly=true)
	public List<FoodDTO> findAllFoods() {
		List<Food> foodList = foodRepository.findAll();
		return foodList.stream().map(food -> new FoodDTO(food)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Optional<FoodDTO> findFoodById(Long foodId) {
		Optional<Food> food = foodRepository.findById(foodId);
			return food.map(x -> new FoodDTO(x));		 
	}
	
	@Transactional(readOnly = true)
	public List<FoodDTO> findFoodByCategoryId(Long categoryId) {
		List<Food> foods = foodRepository.findByCategoryId(categoryId);
			return foods.stream().map(x -> new FoodDTO(x)).collect(Collectors.toList());		 
	}
	
	
	@Transactional
	public void createNewFood(Food food) {
			foodRepository.save(food);
	}
	
	@Transactional
	public Optional<FoodDTO> editFood(Long id, Food foodData) {
		Optional<Food> actualFood = foodRepository.findById(id);
		if(actualFood.isPresent()) {
			Food updatedFood = actualFood.get();
			
			updatedFood.setTitle(foodData.getTitle());
			updatedFood.setImgUrl(foodData.getImgUrl());
			updatedFood.setPrice(foodData.getPrice());
			updatedFood.setDescription(foodData.getDescription());
			
			Food savedFood = foodRepository.save(updatedFood);
			return Optional.of(new FoodDTO(savedFood));
		}
		return Optional.empty();		
	}
	
	@Transactional
	public void deleteFood(Long foodId) {
			foodRepository.deleteById(foodId);	
	}
}
