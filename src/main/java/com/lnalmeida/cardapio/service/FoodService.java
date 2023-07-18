package com.lnalmeida.cardapio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnalmeida.cardapio.dto.FoodDTO;
import com.lnalmeida.cardapio.entities.Food;
import com.lnalmeida.cardapio.repository.FoodRepository;

import jakarta.transaction.Transactional;

@Service
public class FoodService {
	
	@Autowired
	FoodRepository foodRepository;
	
	@Transactional
	public List<FoodDTO> getAllFoods() {
		List<Food> foodList = foodRepository.findAll();
		return foodList.stream().map(food -> new FoodDTO(food)).collect(Collectors.toList());
	}

}
