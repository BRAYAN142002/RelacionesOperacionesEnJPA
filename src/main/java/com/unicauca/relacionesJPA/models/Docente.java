package com.unicauca.relacionesJPA.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="docentes")
@PrimaryKeyJoinColumn(name="idDocente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Docente extends  Persona {
    public Docente(String nombre,String apellido,String correo){
        super(nombre,apellido,correo);
    }

    @OneToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="oficina_id")
    private Oficina objOficina;

    @ManyToMany(cascade={CascadeType.PERSIST},mappedBy="listaDocentes")
    private List<Curso>listaCursos;
    
}
