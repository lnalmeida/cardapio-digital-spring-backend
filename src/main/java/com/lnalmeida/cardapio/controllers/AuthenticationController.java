package com.lnalmeida.cardapio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lnalmeida.cardapio.dto.UsersDTO;
import com.lnalmeida.cardapio.entities.Users;
import com.lnalmeida.cardapio.service.TokenService;
import com.lnalmeida.cardapio.service.UsersService;

@RestController
@RequestMapping(value="auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsersService usersService;
	@Autowired
	TokenService tokenService;
	
	@PostMapping(value="/login")
	public ResponseEntity<String> login(@RequestBody @Validated UsersDTO user){
		var usernamePassword = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		var auth = authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Users)auth.getPrincipal());
		return ResponseEntity.ok("User authenticated.\nToken: "+token);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<String> register(@RequestBody @Validated UsersDTO user) {
		if(usersService.findUserByUsername(user.getUsername()) != null) {
			return ResponseEntity.badRequest().build();
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		Users newUser = new Users(user.getUsername(), encryptedPassword, user.getRole());
		
		usersService.registerUser(newUser);
		
		return ResponseEntity.ok("User successfully registered!");
	}
	
}
