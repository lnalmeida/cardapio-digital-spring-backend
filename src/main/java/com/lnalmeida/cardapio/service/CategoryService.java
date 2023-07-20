package com.lnalmeida.cardapio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnalmeida.cardapio.dto.CategoryDTO;
import com.lnalmeida.cardapio.entities.Category;
import com.lnalmeida.cardapio.repository.CategoryRepository;



@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Transactional(readOnly=true)
	public List<CategoryDTO> findAllCategories() {
		List<Category> categoryList = categoryRepository.findAll();
		return categoryList.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Optional<CategoryDTO> findCategoryById(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
			return category.map(x -> new CategoryDTO(x));		 
	}
	
	@Transactional
	public void createNewCategory(Category category) {
			categoryRepository.save(category);
	}
	
	@Transactional
	public Optional<CategoryDTO> editCategory(Long id, Category categoryData) {
		Optional<Category> actualCategory = categoryRepository.findById(id);
		if(actualCategory.isPresent()) {
			Category updatedCategory = actualCategory.get();
			
			updatedCategory.setTitle(categoryData.getTitle());

			
			Category savedCategory = categoryRepository.save(updatedCategory);
			return Optional.of(new CategoryDTO(savedCategory));
		}
		return Optional.empty();		
	}
	
	@Transactional
	public void deleteCategory(Long categoryId) {
			categoryRepository.deleteById(categoryId);	
	}
}
