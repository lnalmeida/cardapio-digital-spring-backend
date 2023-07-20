package com.lnalmeida.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lnalmeida.cardapio.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
