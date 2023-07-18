package com.lnalmeida.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lnalmeida.cardapio.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
