package mx.edu.uacm.test.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;

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
import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.services.ActividadService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class ActividadControllerTest {

	private Logger log = LogManager.getLogger(EstudianteControllerTest.class);
	@Autowired
	private ActividadService actividadService;
	@Autowired
	private MockMvc mvc;
	@Test
	public void testAgregarActividad() {
		log.debug("Entrando al metodo testAgregarEstudiante" );
		try {
			mvc.perform(post("/actividad/agregar").param("nombre", "Run")
					.param("Categoria", "Deporte")).andExpect(redirectedUrl("registroActividad"));
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	@Test
	public void testActualizarEstudiante() {
		Actividad actividadActual;
		log.debug("Entrando al metodo testActualizarEstudiante" );
		try {
			mvc.perform(post("/actividad/agregar").param("nombre", "Run")
					.param("categoria", "Deporte")).andExpect(redirectedUrl("registroActividad"));
			
			ArrayList<Actividad> actividades=actividadService.obtenerActividades();
			Actividad actividadEsperada=actividades.get(0);
			actividadEsperada.setNombre("corre");
			mvc.perform(post("/actividad/actualizar")
					.param("id", actividadEsperada.getId()+"")
					.param("nombre", actividadEsperada.getNombre())
					.param("categoria", actividadEsperada.getCategoria())).andExpect(redirectedUrl("registroActividad"));
			actividadActual=actividadService.obtenerActividad(actividadEsperada.getId());
			
			Assert.assertThat(actividadActual, is(equalTo(actividadEsperada)));
		} catch (Exception e) {
			log.warn(e.getMessage());
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test
	public void testEliminarActividad() {
		Actividad actividadActual;
		log.debug("Entrando al metodo testActualizarEstudiante" );
		try {
			mvc.perform(post("/actividad/agregar").param("nombre", "Run")
					.param("categoria", "Deporte")).andExpect(redirectedUrl("registroActividad"));
			
			ArrayList<Actividad> actividades=actividadService.obtenerActividades();
			Actividad actividadEsperada=actividades.get(0);
			mvc.perform(post("/actividad/eliminar").param("id", actividadEsperada.getId()+""))
					.andExpect(redirectedUrl("registroActividad"));
			actividadActual=actividadService.obtenerActividad(actividadEsperada.getId());
			
			Assert.assertThat(actividadActual, is(nullValue()));
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	@Test
	public void testObtenerEstudiante() {
		try {
			mvc.perform(post("/actividad/agregar").param("nombre", "Nadar")
					.param("categoria", "Deporte")).andExpect(redirectedUrl("registroActividad"));
			
			ArrayList<Actividad> actividades=actividadService.obtenerActividades();
			Actividad actividadEsperada=actividades.get(0);
			
			mvc.perform(get("/actividad/obtener").param("idActividad",""+actividadEsperada.getId()))
					.andExpect(model().attribute("actividad"
							,allOf(
									hasProperty("id",is(actividadEsperada.getId()))
									,hasProperty("nombre",is(actividadEsperada.getNombre()))
									,hasProperty("categoria",is(actividadEsperada.getCategoria())))));
			
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
}
