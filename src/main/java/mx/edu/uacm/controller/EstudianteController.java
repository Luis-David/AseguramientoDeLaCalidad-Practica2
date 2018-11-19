package mx.edu.uacm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.services.EstudianteService;
@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
	private static final Logger log= LogManager.getLogger(EstudianteController.class);
	
	@Autowired
	private EstudianteService estudianteService;
	
	@GetMapping("/")
	public String principal() {
		return "index";
	}
	@PostMapping("/agregar")
	public String agregarEstudiante(Estudiante estudiante) {//Estudiante estudiante) {
		log.debug("estudiante: "+estudiante.getNombre());
		if(estudianteService.guardarEstudiante(estudiante)!=null) {
			log.debug("Regreso la pagina principal");
			return "redirect:index";
		}
		return "redirect:error";
	}
	@PostMapping("/actualizar")
	public String actualizarEstudiante(Estudiante estudiante) {
		log.debug("actualizando estudiante");
		if(estudianteService.actualizarEstudiante(estudiante) != null) {
			return "redirect:index";
		}
		return "redirect:error";
	}
	@PostMapping("/eliminar")
	public String eliminarEstudiante(Estudiante estudiante) {
		boolean exito;
		exito=estudianteService.eliminarEstudiante(estudiante);
		if(exito)
			return "redirect:index";
		return "redirect:error";
	}
	@GetMapping("/obtener")
	public String  obtenerEstudiante(Model model,String idMatricula) {
		Estudiante estudiante;
		
		estudiante=estudianteService.obtenerEstudiante(idMatricula);
		if(estudiante!=null) {
			model.addAttribute("estudiante",estudiante);
			return "infoEstudiante";
		}
		return "error";
	}
	
}
