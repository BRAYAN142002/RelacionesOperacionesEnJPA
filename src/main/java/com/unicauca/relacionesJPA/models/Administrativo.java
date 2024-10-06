package com.unicauca.relacionesJPA.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="administrativos")
@PrimaryKeyJoinColumn(name="idAdministrativo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrativo extends Persona {
   /*  @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    */
    @Column(name="rol",nullable=true,length=60)
    private String rol;

}
