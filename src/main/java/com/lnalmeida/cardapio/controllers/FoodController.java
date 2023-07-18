package com.lnalmeida.cardapio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnalmeida.cardapio.dto.FoodDTO;
import com.lnalmeida.cardapio.service.FoodService;


@RestController
@RequestMapping(value="/food")
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@GetMapping
	public List<FoodDTO> getAllFoods() {
		List<FoodDTO> foodList = foodService.getAllFoods();
		return foodList;
	}

}
