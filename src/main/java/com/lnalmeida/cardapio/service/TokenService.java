package com.lnalmeida.cardapio.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lnalmeida.cardapio.entities.Users;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String jwt_secret;

	public String generateToken(Users user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
			String token = JWT
							.create()
							.withIssuer("auth-api")
							.withSubject(user.getUsername())
							.withExpiresAt(generateExpirationDate())
							.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error generating token", e);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(jwt_secret);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			throw new RuntimeException("Error verifying token", e);
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
