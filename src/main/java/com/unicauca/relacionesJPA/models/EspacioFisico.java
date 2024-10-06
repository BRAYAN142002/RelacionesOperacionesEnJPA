package com.unicauca.relacionesJPA.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="espacio_fisico")
@Getter
@Setter
@AllArgsConstructor
public class EspacioFisico {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre",unique=true,length=50, nullable=false)
    private String nombre;
    @Column(name="capacidad",nullable=false)
    private int capacidad;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objEspacioFisico")
    private List<FranjaHoraria>franjasHorarias;

    public EspacioFisico(String nombre,int capacidad){
        this.nombre=nombre;
        this.capacidad=capacidad;
    }
    
}
