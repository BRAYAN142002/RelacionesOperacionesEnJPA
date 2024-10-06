package com.unicauca.relacionesJPA.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cursos")
@Getter
@Setter
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre",nullable=false,length=70)
    private String nombre;

    @OneToMany(fetch=FetchType.LAZY,mappedBy="objCurso")
    private List<FranjaHoraria> franjasHorarias;

    @ManyToOne
    @JoinColumn(name="asignatura_id")
    private Asignatura objAsignatura;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="curso_docente",
                joinColumns = @JoinColumn(name= "idCurso"),
                inverseJoinColumns = @JoinColumn(name= "idDocente"))
    private List<Docente>listaDocentes;

    public Curso(String nombre){
        this.nombre=nombre;

    }


}
