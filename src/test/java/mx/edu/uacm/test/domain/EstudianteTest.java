package mx.edu.uacm.test.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.id.IdentifierGenerationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.Application;
import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.repository.EstudianteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class EstudianteTest {
	private Logger log= LogManager.getLogger(ActividadTest.class);
	private Estudiante estudiante;
	private Estudiante estudianteEsperado;
	private Estudiante estudianteActual;
	private Actividad actividad;
	@Autowired
	private EstudianteRepository estudianteRepository;
	@Test
	public void testGuardarEstudiante() {
		log.info("Entrando a testGuardarEstudiante");
		estudiante= new Estudiante();
		estudiante.setMatricula("11-123-1234");
		estudiante.setNombre("Yaritzi");
		estudiante.setApellidos("Vaca");
		actividad= new Actividad("Caminar","deporte");
		estudiante.setActividad(actividad);
		actividad= new Actividad("Correr","deporte");
		estudiante.setActividad(actividad);
		actividad= new Actividad("Leer","Academica");
		estudiante.setActividad(actividad);
		try {
			estudianteRepository.save(estudiante);
			
		}catch(IdentifierGenerationException e) {
			log.warn(e.getMessage());
			Assert.fail();
		}
	}
	@Test
	public void testActualizarEstudiante() {
		log.info("Entrando a testActualizarEstudiante");
		//List<Actividad> actividades;
		estudiante= new Estudiante();
		estudiante.setMatricula("11-123-0000");
		estudiante.setNombre("Manuel");
		estudiante.setApellidos("Vaca");
		actividad= new Actividad("Caminar","deporte");
		estudiante.setActividad(actividad);
		actividad= new Actividad("Correr","deporte");
		estudiante.setActividad(actividad);
		actividad= new Actividad("Leer","Academica");
		estudiante.setActividad(actividad);
		
		estudiante=estudianteRepository.save(estudiante);
		
		estudianteEsperado=estudiante;
		estudianteEsperado.setApellidos("Vaca Romero");
		/* Nota: No se pudo eliminar la actividad con las siguientes intrucciones:
		//elimino la actividad Caminar
		actividades=estudianteEsperado.getActividades();
		actividad=actividades.remove(0);
		*/
		estudianteRepository.save(estudianteEsperado);
		
		
		estudianteActual=estudianteRepository.obtenerEstudiante(estudiante.getMatricula());
		
		Assert.assertEquals(estudianteEsperado, estudianteActual);
	}
	@Test
	public void testObtenerEstudiante() {
		log.info("Entrando a testObtenerEstudiante");
		estudiante= new Estudiante();
		estudiante.setMatricula("11-001-0000");
		estudiante.setNombre("Lorena");
		estudiante.setApellidos("Martinez");
		actividad= new Actividad("Dibujar","arte");
		estudiante.setActividad(actividad);
		actividad= new Actividad("Colorear","arte");
		estudiante.setActividad(actividad);
		actividad= new Actividad("Leer","Academica");
		estudiante.setActividad(actividad);
		estudiante=estudianteRepository.save(estudiante);
		estudianteActual=estudianteRepository.obtenerEstudiante(estudiante.getMatricula());
		Assert.assertEquals(estudiante, estudianteActual);
	}
	@Test
	public void testEliminarEstudiante() {
		log.info("Entrando a testObtenerEstudiante");
		estudiante= new Estudiante();
		estudiante.setMatricula("16-003-0000");
		estudiante.setNombre("Daniela");
		estudiante.setApellidos("Martinez");
		actividad= new Actividad("Dibujar","arte");
		estudiante=estudianteRepository.save(estudiante);
		estudianteRepository.delete(estudiante);
		estudianteActual=estudianteRepository.obtenerEstudiante(estudiante.getMatricula());
		Assert.assertNull(estudianteActual);
	}
}
