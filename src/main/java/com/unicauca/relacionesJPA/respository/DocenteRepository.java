package com.unicauca.relacionesJPA.respository;

import org.springframework.data.repository.CrudRepository;

import com.unicauca.relacionesJPA.models.Docente;

public interface DocenteRepository extends CrudRepository<Docente, Integer> {
    
}
