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
		List<Docente> listaDocentes=new  ArrayList<>();
		Asignatura asignatura=null;

		Optional<Docente> objDocente=this.servicioBDDocente.findById(1);

		if(objDocente!=null){
			listaDocentes.add(objDocente.get());
		}else{
			System.out.println("El docente no esta creado");
		}

		Optional<Asignatura> objAsignatura=this.servicioBDAsignatura.findById(1);

		if(objAsignatura!=null){
			asignatura=objAsignatura.get();
		}else{
			System.out.println("La asignatura no esta creada");
		}

			objCurso.setListaDocentes(listaDocentes);
			objCurso.setObjAsignatura(asignatura);
			this.servicioBDCurso.save(objCurso);
	}

	public void almacenarEspacioFisico(){
		EspacioFisico objEspacioFisico=new EspacioFisico("Sala 2", 23);
		this.servicioBDEspacioFisico.save(objEspacioFisico);
	}

	public void crearFranjaHoraria(){
		Curso curso=null;
		EspacioFisico espacioFisico=null;

		Optional<Curso> objCurso=this.servicioBDCurso.findById(1);
		if(objCurso!=null){
			curso=objCurso.get();
		}else{
			System.out.println("El curso no ha sido creado ");
		}

		Optional<EspacioFisico> objEspacioFisico=this.servicioBDEspacioFisico.findById(1);
		if(objEspacioFisico!=null){
			espacioFisico=objEspacioFisico.get();
		}else{
			System.out.println("El espacio fisico no ha sido creado");
		}

		Time horaInicio = Time.valueOf("07:00:00"); 
        Time horaFin = Time.valueOf("9:00:00"); 
		FranjaHoraria objFranjaHoraria=new FranjaHoraria("martes",horaInicio,horaFin);
		objFranjaHoraria.setObjCurso(curso);
		objFranjaHoraria.setObjEspacioFisico(espacioFisico);

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
		Optional<Docente> objDocente=this.servicioBDDocente.findById(1);
		

		if(objDocente!=null){
			System.out.println("Id:"+objDocente.get().getId());
			System.out.println("Nombre: "+objDocente.get().getNombre());
			System.out.println("Apelldio: "+objDocente.get().getApellido());
			System.out.println("Correo: "+objDocente.get().getCorreo());
			for(Curso objCurso: objDocente.get().getListaCursos()){
				System.out.println("id del curso: "+objCurso.getId());
				System.out.println("Nombre del curso: "+objCurso.getNombre());
				for(FranjaHoraria objFranja: objCurso.getFranjasHorarias()){
					System.out.println("id Franja: "+objFranja.getId());
					System.out.println("Dia: "+objFranja.getDia());
					System.out.println("Hora Inicio: "+objFranja.getHora_inicio());
					System.out.println("Hora Fin: "+objFranja.getHora_fin());
					System.out.println("Id espacio Fisico: "+objFranja.getObjEspacioFisico().getId());
					System.out.println("Nombre de espacio fisico: "+objFranja.getObjEspacioFisico().getNombre());
					System.out.println("Capacidad de espacio fisico: "+objFranja.getObjEspacioFisico().getCapacidad());

				}
			}

		}else{
			System.out.println("El docente no esta creado");
		}



	}
	public void eliminarCurso(){
		this.servicioBDCurso.deleteById(1);

	}
}
