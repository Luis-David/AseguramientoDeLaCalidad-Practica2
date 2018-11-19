package mx.edu.uacm.test.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.Application;
import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.services.EstudianteService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class EstudianteServiceImplTest {
	public static final Logger log= LogManager.getLogger(ActividadServiceImplTest.class);
	@Autowired
	private EstudianteService estudianteService;
	@Test
	public void guardarEstudiante() {
		Estudiante estudiante=new Estudiante();
		estudiante.setMatricula("112220987");
		estudiante.setApellidos("PErez");
		estudiante.setNombre("Juan");
		Actividad a= new Actividad();
		a.setCategoria("Escolar");
		a.setNombre("Mecanica");
		estudiante.setActividad(a);
		estudianteService.guardarEstudiante(estudiante);
	}
}
