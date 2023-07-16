package com.lnalmeida.cardapio.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/food")
public class FoodController {
	
	@GetMapping
	public void getAllFoods() {
		
	}

}
