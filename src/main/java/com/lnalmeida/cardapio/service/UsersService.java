package com.lnalmeida.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnalmeida.cardapio.entities.Users;
import com.lnalmeida.cardapio.repository.UserRepository;

@Service
public class UsersService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional(readOnly=true)
	public UserDetails findUserByUsername(String username) {
		UserDetails user = userRepository.findByUsername(username);
		return (user);
	}
	
	@Transactional
	public void registerUser(Users user) {
		userRepository.save(user);
	}

}
