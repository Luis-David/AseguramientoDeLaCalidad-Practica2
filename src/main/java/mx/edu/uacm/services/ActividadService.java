package mx.edu.uacm.services;

import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Actividad;
@Service
public interface ActividadService {
	public Actividad guardaActividad(Actividad actividad);
	public Actividad actualizarActividad(Actividad actividad);
	public boolean eliminarActividad(Actividad actividad);
}
