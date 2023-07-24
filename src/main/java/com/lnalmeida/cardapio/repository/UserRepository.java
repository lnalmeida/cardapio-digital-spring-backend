package com.lnalmeida.cardapio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.lnalmeida.cardapio.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	UserDetails findByUsername(String username);

}
