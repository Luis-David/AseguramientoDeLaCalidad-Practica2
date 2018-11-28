package mx.edu.uacm.services;

import mx.edu.uacm.domain.Estudiante;

import org.springframework.stereotype.Service;



@Service
public interface EstudianteService {
  public Estudiante guardarEstudiante(Estudiante estudiante);

  public Estudiante actualizarEstudiante(Estudiante estudiante);

  public boolean eliminarEstudiante(Estudiante estudiante);

  public Estudiante obtenerEstudiante(String idEstudiante);
}
