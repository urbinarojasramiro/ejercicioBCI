package com.bci.ejercicio.repository;

import com.bci.ejercicio.entity.RolEntity;
import com.bci.ejercicio.util.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("RolRepository")
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    Optional<RolEntity> findByRolName(RolName rolName);
}
