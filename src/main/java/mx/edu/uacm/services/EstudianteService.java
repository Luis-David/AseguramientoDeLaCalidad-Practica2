package mx.edu.uacm.services;

import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Estudiante;
@Service
public interface EstudianteService {
	public Estudiante guardarEstudiante(Estudiante estudiante);
	public Estudiante actualizarEstudiante(Estudiante estudiante);
	public boolean eliminarEstudiante(Estudiante estudiante);
	public Estudiante obtenerEstudiante(String idEstudiante);
}
