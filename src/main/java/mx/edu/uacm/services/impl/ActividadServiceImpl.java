package mx.edu.uacm.services.impl;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.repository.ActividadRepository;
import mx.edu.uacm.services.ActividadService;

/**
 * 
 * @author Luis David
 *
 */

@Service
public class ActividadServiceImpl implements ActividadService{
	public static final Logger log = LogManager.getLogger(ActividadServiceImpl.class);
	@Autowired
	private ActividadRepository actividadRepository;
	@Override
	public Actividad guardaActividad(Actividad vehiculo) {
		
		return actividadRepository.save(vehiculo);
	}
	@Override
	public Actividad actualizarActividad(Actividad actividad) {
		return actividadRepository.save(actividad);
	}
	@Override
	public boolean eliminarActividad(Actividad actividad) {
		try {
			actividadRepository.delete(actividad);
			return true;
		}catch(IllegalArgumentException e) {
			log.warn(""+e.getMessage());
		}
		return false;
	}

}
