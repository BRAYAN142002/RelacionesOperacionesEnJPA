package com.unicauca.relacionesJPA.models;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="franja_horarias")
@Getter
@Setter
@AllArgsConstructor
public class FranjaHoraria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="dia",nullable=false, length=50)
    private String dia;
    @Column(name="hora_inicio",nullable=false)
    private Time hora_inicio;
    @Column(name="hora_fin", nullable=false)
    private Time hora_fin;

    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso objCurso;

    @ManyToOne
    @JoinColumn(name="espacio_fisico_id" , nullable=false)
    private EspacioFisico objEspacioFisico;

    public FranjaHoraria(String dia,Time horaInicio,Time horaFin){
        this.dia=dia;
        this.hora_inicio=horaInicio;
        this.hora_fin=horaFin;
    }
}
