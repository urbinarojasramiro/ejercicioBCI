package com.bci.ejercicio.repository;

import com.bci.ejercicio.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<UserEntity, UUID>{

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByName(String userName);

	boolean existsByName(String userName);
	
	boolean existsByEmail(String email);
}
