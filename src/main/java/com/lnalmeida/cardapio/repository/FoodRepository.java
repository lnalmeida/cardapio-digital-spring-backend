package com.lnalmeida.cardapio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lnalmeida.cardapio.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
	 List<Food> findByCategoryId(Long categoryId);
}
