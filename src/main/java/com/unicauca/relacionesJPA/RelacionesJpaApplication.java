package com.unicauca.relacionesJPA;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.unicauca.relacionesJPA.models.Asignatura;
import com.unicauca.relacionesJPA.models.Curso;
import com.unicauca.relacionesJPA.models.Docente;
import com.unicauca.relacionesJPA.models.EspacioFisico;
import com.unicauca.relacionesJPA.models.FranjaHoraria;
import com.unicauca.relacionesJPA.models.Oficina;
import com.unicauca.relacionesJPA.respository.AsignaturaRepository;
import com.unicauca.relacionesJPA.respository.CursoRepository;
import com.unicauca.relacionesJPA.respository.DocenteRepository;
import com.unicauca.relacionesJPA.respository.EspacioFisicoRepository;
import com.unicauca.relacionesJPA.respository.FranjaHorariaRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
@Transactional
public class RelacionesJpaApplication implements CommandLineRunner{
	@Autowired
	private DocenteRepository servicioBDDocente;

	@Autowired
	private AsignaturaRepository servicioBDAsignatura;
	@Autowired
	private CursoRepository servicioBDCurso;

	@Autowired
	private EspacioFisicoRepository servicioBDEspacioFisico;
	@Autowired
	private FranjaHorariaRepository servicioBDFranjaHoraria;

	public static void main(String[] args) {
		SpringApplication.run(RelacionesJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'run'");
	}
	public void crearDocente(){
		Docente objDocente=new Docente("Juan camilo","Alegria","juanca@unicauca.edu");
		Oficina objOficina=new Oficina("Oficina 423","Fiet");

		objDocente.setObjOficina(objOficina);
		objOficina.setObjDocente(objDocente);

		this.servicioBDDocente.save(objDocente);
	}
	public void almacenarAsignatura(){
			Asignatura objAsignatura=new Asignatura("Calculo 2", "45378");
			this.servicioBDAsignatura.save(objAsignatura);

	}
	public void crearCurso(){
		
		Curso objCurso=new Curso("Curso Ingenieria grup 1");
		List<Docente>listaDocentes=new  ArrayList<>();

		Optional<Docente> objDocente=this.servicioBDDocente.findById(1);

		if(objDocente!=null){
			listaDocentes.add(objDocente.get());
		}else{
			System.out.println("El docente no esta creado");
		}

		

		

	

			objCurso.setListaDocentes(listaDocetes);
			objCurso.setObjAsignatura(objAsignatura);
			this.servicioBDCurso.save(objCurso);

	}
	public void crearFranjaHoraria(){
			Curso objCurso=new Curso("Curso Ingenieria grupo 2");
			this.servicioBDCurso.save(objCurso);

			EspacioFisico objEspacioFisico=new EspacioFisico("Sala 2", 23);

			this.servicioBDEspacioFisico.save(objEspacioFisico);


		Time horaInicio = Time.valueOf("07:00:00"); 
        Time horaFin = Time.valueOf("9:00:00"); 
			FranjaHoraria objFranjaHoraria=new FranjaHoraria("martes",horaInicio,horaFin);

		objFranjaHoraria.setObjCurso(objCurso);
		objFranjaHoraria.setObjEspacioFisico(objEspacioFisico);

		this.servicioBDFranjaHoraria.save(objFranjaHoraria);
	}
	public void listarFranjasHorarias(){
		Iterable<FranjaHoraria> listaFranjasHorarias=this.servicioBDFranjaHoraria.findAll();
		for (FranjaHoraria objFranja : listaFranjasHorarias) {
			System.out.println("Dia"+objFranja.getDia());
			System.out.println("Hora Inicio"+objFranja.getHora_inicio());
			System.out.println("Hora Fin"+objFranja.getHora_fin());
			System.out.println("Nombre del curso"+objFranja.getObjCurso().getNombre());
			System.out.println("Nombre espacio Fisico: "+objFranja.getObjEspacioFisico().getNombre());
			System.out.println("Capacidad espacio Fisico: "+objFranja.getObjEspacioFisico().getCapacidad());
		}

	}
	public void consultarFranjaHorariaporDcente(){
		Iterable<Docente> objDocente=this.servicioBDDocente.findAll();
		


	}
	public void eliminarCurso(){
		
	}
}
