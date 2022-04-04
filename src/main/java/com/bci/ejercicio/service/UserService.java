package com.bci.ejercicio.service;

import com.bci.ejercicio.dto.UserRequestDTO;
import com.bci.ejercicio.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
	
	List<UserEntity> getUser();
	
	ResponseEntity<Object> saveUser(UserRequestDTO user);
	
	Optional<UserEntity> findByEmail(String email);

	ResponseEntity<Object> getTokenByUser(String user);

}
