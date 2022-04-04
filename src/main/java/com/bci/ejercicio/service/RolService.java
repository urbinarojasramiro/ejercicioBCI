package com.bci.ejercicio.service;

import com.bci.ejercicio.entity.RolEntity;
import com.bci.ejercicio.repository.RolRepository;
import com.bci.ejercicio.util.RolName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface RolService {

    Optional<RolEntity> getByRolName(RolName rolName);

    void save(RolEntity rol);
}