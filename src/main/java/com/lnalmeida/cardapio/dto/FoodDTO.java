package com.lnalmeida.cardapio.dto;

import com.lnalmeida.cardapio.entities.Category;
import com.lnalmeida.cardapio.entities.Food;

import jakarta.persistence.Column;

public class FoodDTO {
	
	private Long id;
	private String title;
	private String imgUrl;
	private double price;
	@Column(columnDefinition = "TEXT")
	private String description;
	public Category category;
	
	
	public FoodDTO(Food entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.imgUrl = entity.getImgUrl();
		this.price = entity.getPrice();
		this.description = entity.getDescription();
		this.category = entity.getCategory();
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
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category Category() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
