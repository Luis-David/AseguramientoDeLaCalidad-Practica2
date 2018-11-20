package mx.edu.uacm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.services.ActividadService;

@Controller
@RequestMapping("/actividad")
public class ActividadController {
private static final Logger log= LogManager.getLogger(ActividadController.class);
	
	@Autowired
	private ActividadService actividadteService;
	
	@GetMapping("/")
	public String principal() {
		return "registroActividad";
	}
	@PostMapping("/agregar")
	public String agregarActividad(Actividad actividad) {//Estudiante estudiante) {
		log.debug("estudiante: "+actividad.getNombre());
		if(actividadteService.guardarActividad(actividad)!=null) {
			log.debug("Regreso la pagina de registroActividad");
			return "redirect:registroActividad";
		}
		return "redirect:error";
	}
	@PostMapping("/actualizar")
	public String actualizarActividad(Actividad actividad) {
		log.debug("actualizando estudiante");
		if(actividadteService.actualizarActividad(actividad) != null) {
			return "redirect:registroActividad";
		}
		return "redirect:error";
	}
	@PostMapping("/eliminar")
	public String eliminarActividad(Actividad actividad) {
		boolean exito;
		exito=actividadteService.eliminarActividad(actividad);
		if(exito)
			return "redirect:registroActividad";
		return "redirect:error";
	}
	@GetMapping("/obtener")
	public String  obtenerActividad(Model model,Long idActividad) {
		Actividad actividad;
		
		actividad=actividadteService.obtenerActividad(idActividad);
		if(actividad!=null) {
			model.addAttribute("actividad",actividad);
			return "infoActividad";
		}
		return "error";
	}
}
