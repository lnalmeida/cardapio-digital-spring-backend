package com.lnalmeida.cardapio.dto;

import org.springframework.security.core.userdetails.UserDetails;

import com.lnalmeida.cardapio.entities.Users;
import com.lnalmeida.cardapio.users.UserRole;

public class UsersDTO {
	
	private Long id;
	private String username;
	private String password;
	private UserRole role;
	
	public UsersDTO(Users entity) {
		this.id = entity.getId();
		this.username = entity.getUsername();
		this.password = entity.getPassword();
		this.role = entity.getRole();
	}
	
	public UsersDTO(String username, String encryptedPassword, UserRole role) {
		this.username = username;
		this.password = encryptedPassword;
		this.role = role;
	}
	
	public UsersDTO(UserDetails user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	

}
