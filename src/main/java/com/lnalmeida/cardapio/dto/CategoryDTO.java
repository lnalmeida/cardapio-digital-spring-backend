package com.lnalmeida.cardapio.dto;

import com.lnalmeida.cardapio.entities.Category;

public class CategoryDTO {
	
	private Long id;
	private String title;
	
	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	};
	
	

}
