package com.unicauca.relacionesJPA.respository;

import org.springframework.data.repository.CrudRepository;

import com.unicauca.relacionesJPA.models.Administrativo;

public interface AdministrativoRepository extends CrudRepository<Administrativo, Integer> {
    
}
