package mx.edu.uacm.controller;

import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.services.EstudianteService;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
  private static final Logger log = LogManager.getLogger(EstudianteController.class);

  @Autowired
  private EstudianteService estudianteService;

  @GetMapping("/")
  public String principal() {
    return "index";
  }
  
  /**
   * Método para guardar un estudiante.
   * @param estudiante objeto estudiante
   * @return una vista
   */
  
  @PostMapping("/agregar")
  public String agregarEstudiante(Estudiante estudiante) {
    log.debug("estudiante: " + estudiante.getNombre());
    if (estudianteService.guardarEstudiante(estudiante) != null) {
      log.debug("Regreso la pagina principal");
      return "redirect:index";
    }
    return "redirect:error";
  }
  
  /**
   * Método para actualizar un estudiante.
   * @param estudiante objeto estudiante
   * @return una vista
   */

  @PostMapping("/actualizar")
  public String actualizarEstudiante(Estudiante estudiante) {
    log.debug("actualizando estudiante");
    if (estudianteService.actualizarEstudiante(estudiante) != null) {
      return "redirect:index";
    }
    return "redirect:error";
  }
  
  /**
   * Método para eliminar un estudiante.
   * @param estudiante objeto estudiante
   * @return una vista
   */
  
  @PostMapping("/eliminar")
  public String eliminarEstudiante(Estudiante estudiante) {
    boolean exito;
    exito = estudianteService.eliminarEstudiante(estudiante);
    if (exito) {
      return "redirect:index";
    }
    return "redirect:error";
  }

  /**
   * Metodo para obtener un estudiante junto con sus actividades.
   * @param model modelo de la vista
   * @param idMatricula matricula del estudiante
   * @return una vista
   */
  
  @GetMapping("/obtener")
  public String obtenerEstudiante(Model model, String idMatricula) {
    Estudiante estudiante;

    estudiante = estudianteService.obtenerEstudiante(idMatricula);
    if (estudiante != null) {
      model.addAttribute("estudiante", estudiante);
      return "infoEstudiante";
    }
    return "error";
  }

}
