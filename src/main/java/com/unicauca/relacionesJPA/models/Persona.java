package com.unicauca.relacionesJPA.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="personas")
@Inheritance(strategy=InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre", nullable=false, length=50)
    private String nombre;
    @Column(name="apellido", nullable=false, length=50)
    private String apellido;
    @Column(unique = true, name = "correo", nullable = false)
    private String correo;

    public Persona(String nombre,String apellido,String correo){
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
    }

}
