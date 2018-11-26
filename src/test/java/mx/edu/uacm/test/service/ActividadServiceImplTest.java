package mx.edu.uacm.test.service;

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
import mx.edu.uacm.services.ActividadService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ActividadServiceImplTest {
	public static final Logger log= LogManager.getLogger(ActividadServiceImplTest.class);
	@Autowired
	private ActividadService actividadService;
	
	@Test
	public void testGuardarActividad() {
		Actividad a= new Actividad();
		a.setCategoria("Escolar");
		a.setNombre("Leer");
		
		actividadService.guardarActividad(a);
	}
	
	@Test
	public void testActualizarActividad() {
		Actividad actividadEsperada;
		Actividad actividadActual;
		Actividad a= new Actividad();
		a.setCategoria("Escolar");
		a.setNombre("Reponder cuestionario");
		actividadEsperada=actividadService.guardarActividad(a);
		
		actividadEsperada.setNombre("Exponer");
		
		actividadService.actualizarActividad(actividadEsperada);
		actividadActual=actividadService.obtenerActividad(actividadEsperada.getId());
		Assert.assertEquals(actividadEsperada, actividadActual);
	}
	
	@Test
	public void testObtenerActividad() {
		Actividad actividadEsperada;
		Actividad actividadActual;
		Estudiante estudiante=new Estudiante("13-003-2222","Luis","David");
		Actividad a= new Actividad();
		estudiante.setActividad(a);
		a.setCategoria("Ocio");
		a.setNombre("Ver pelicula");
		actividadEsperada=actividadService.guardarActividad(a);
		actividadActual=actividadService.obtenerActividad(actividadEsperada.getId());
		Assert.assertEquals(actividadEsperada,actividadActual);
	}
	
	@Test
	public void testEliminarActividad() {
		Actividad a= new Actividad();
		a.setCategoria("Escolar");
		a.setNombre("Reponder cuestionario");
		actividadService.guardarActividad(a);
		
		Assert.assertTrue(actividadService.eliminarActividad(a));
		Assert.assertFalse(actividadService.eliminarActividad(null));
	}
}
