package mx.edu.uacm.test.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import mx.edu.uacm.Application;
import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.services.EstudianteService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class EstudianteControllerTest {
	private Logger log = LogManager.getLogger(EstudianteControllerTest.class);
	@Autowired
	private EstudianteService estudianteService;
	@Autowired
	private MockMvc mvc;
	@Test
	public void testAgregarEstudiante() {
		log.debug("Entrando al metodo testAgregarEstudiante" );
		try {
			mvc.perform(post("/estudiante/agregar").param("matricula", "13-003-1235")
					.param("nombre", "Juan")
					.param("apellidos", "Perez Romiro")).andExpect(redirectedUrl("index"));
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	@Test
	public void testActualizarEstudiante() {
		Estudiante estudiante;
		log.debug("Entrando al metodo testActualizarEstudiante" );
		try {
			mvc.perform(post("/estudiante/agregar").param("matricula", "13-003-1240")
					.param("nombre", "Pablo")
					.param("apellidos", "Jerez Oro")).andExpect(redirectedUrl("index"));
			
			mvc.perform(post("/estudiante/actualizar").param("matricula", "13-003-1240")
					.param("nombre", "Pablo Tomas")
					.param("apellidos", "Jerez Oro")).andExpect(redirectedUrl("index"));
			estudiante=estudianteService.obtenerEstudiante("13-003-1240");
			
			Assert.assertThat(estudiante.getNombre(), is(equalTo("Pablo Tomas")));
			log.debug("nom: "+estudiante.getNombre());
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	@Test
	public void testEliminarEstudiante() {
		Estudiante estudiante;
		log.debug("Entrando al metodo testActualizarEstudiante" );
		try {
			mvc.perform(post("/estudiante/agregar").param("matricula", "13-003-1240")
					.param("nombre", "Pablo")
					.param("apellidos", "Jerez Oro")).andExpect(redirectedUrl("index"));
			
			mvc.perform(post("/estudiante/eliminar").param("matricula", "13-003-1240"))
					.andExpect(redirectedUrl("index"));
			estudiante=estudianteService.obtenerEstudiante("13-003-1240");
			
			Assert.assertThat(estudiante, is(nullValue()));
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	@Test
	public void testObtenerEstudiante() {
		try {
			mvc.perform(post("/estudiante/agregar").param("matricula", "13-003-1234")
					.param("nombre", "Jose")
					.param("apellidos", "Buen Dia")).andExpect(redirectedUrl("index"));
			mvc.perform(get("/estudiante/obtener").param("idMatricula","13-003-1234"))
					.andExpect(model().attribute("estudiante",is(notNullValue())));
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
}
