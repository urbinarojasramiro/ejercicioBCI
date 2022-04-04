package com.bci.ejercicio.repository;



import com.bci.ejercicio.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("PhoneRepository")
public interface PhoneRepository extends CrudRepository<PhoneEntity, UUID> {

}
