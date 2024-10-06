package com.unicauca.relacionesJPA.respository;

import org.springframework.data.repository.CrudRepository;

import com.unicauca.relacionesJPA.models.Curso;

public interface CursoRepository extends CrudRepository<Curso, Integer> {
    
}
