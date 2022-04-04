package com.bci.ejercicio.service.impl;

import com.bci.ejercicio.entity.RolEntity;
import com.bci.ejercicio.repository.RolRepository;
import com.bci.ejercicio.service.RolService;
import com.bci.ejercicio.util.RolName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<RolEntity> getByRolName(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }

    public void save(RolEntity rol){
        rolRepository.save(rol);
    }
}
