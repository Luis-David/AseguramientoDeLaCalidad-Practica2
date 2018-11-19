package mx.edu.uacm.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Estudiante;
import mx.edu.uacm.repository.EstudianteRepository;
import mx.edu.uacm.services.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService{
	private static final Logger log= LogManager.getLogger(EstudianteServiceImpl.class);
	@Autowired
	private EstudianteRepository estudianteBD;
	@Override
	public Estudiante guardarEstudiante(Estudiante estudiante) {
		return estudianteBD.save(estudiante);
	}
	@Override
	public Estudiante actualizarEstudiante(Estudiante estudiante) {
		return estudianteBD.save(estudiante);
	}
	@Override
	public boolean eliminarEstudiante(Estudiante estudiante) {
		Estudiante eliminado;
		try {
			estudianteBD.delete(estudiante);
			eliminado=estudianteBD.obtenerEstudiante(estudiante.getMatricula());
			if(eliminado==null)
				return true;
		}catch(IllegalArgumentException e) {
			log.warn(""+e.getMessage());
		}
		return false;
	}
	@Override
	public Estudiante obtenerEstudiante(String idEstudiante) {
		return estudianteBD.obtenerEstudiante(idEstudiante);
	}
	

}
