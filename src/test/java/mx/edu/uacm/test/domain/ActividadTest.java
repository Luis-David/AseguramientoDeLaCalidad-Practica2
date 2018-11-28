package mx.edu.uacm.test.domain;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.Application;
import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.repository.ActividadRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ActividadTest {
	private Logger log= LogManager.getLogger(ActividadTest.class);
	@Autowired
	private ActividadRepository actividadRepository;
	@Test
	public void testGuardarActividad() {
		log.info("entrando al testGuardarActividad");
		Estudiante estudiante;
		Actividad actividad = new Actividad("Futbol","Deporte");
		Actividad actividadActual= null;
		estudiante=new Estudiante();
		estudiante.setMatricula("12-003-5623");
		estudiante.setNombre("David");
		estudiante.setApellidos("Ramirez");
		actividad.setEstudiante(estudiante);
		actividadActual=actividadRepository.save(actividad);
		Assert.assertNotNull(actividadActual.getId());
	}
	
	@Test
	public void testActualizarActividad() {
		log.info("entrando al testActualizarActividad");
		Actividad actividad = new Actividad("Correr","Deporte");
		Actividad actividadEsperada= null;
		Actividad actividadActual=null;
		actividadEsperada=actividadRepository.save(actividad);
		actividadEsperada.setNombre("correr");
		actividadActual=actividadRepository.save(actividadEsperada);
		Assert.assertEquals(actividadEsperada.getNombre(), actividadActual.getNombre());
	}
	@Test
	public void testObtenerActividad() {
		log.info("entrando al testGuardarActividad");
		Estudiante estudiante;
		Estudiante estudianteActual;
		Actividad actividad = new Actividad("Futbol","Deporte");
		Actividad actividadEsperada= null;
		Actividad actividadActual=null;
		Optional<Actividad> op;
		estudiante=new Estudiante();
		estudiante.setMatricula("12-003-1234");
		estudiante.setNombre("David");
		estudiante.setApellidos("Ramirez");
		actividad.setEstudiante(estudiante);
		actividadEsperada=actividadRepository.save(actividad);
		op= actividadRepository.findById(actividadEsperada.getId());
		Assert.assertTrue(op.isPresent());
		actividadActual=op.get();
		Assert.assertNotNull(actividadActual.getEstudiante());
		estudianteActual=actividadActual.getEstudiante();
		log.info("nombre: "+estudianteActual.getNombre());
		Assert.assertEquals(estudiante.getNombre(), estudianteActual.getNombre());		
	}
	
	@Test 
	public void testEliminarActividad() {
		log.info("entrando al testEliminarActividad");
		Actividad actividad = new Actividad("Caminar","Deporte");
		actividad=actividadRepository.save(actividad);
		actividadRepository.delete(actividad);
		Optional<Actividad> op=actividadRepository.findById(actividad.getId());
		
		Assert.assertFalse(op.isPresent());
	}
}
