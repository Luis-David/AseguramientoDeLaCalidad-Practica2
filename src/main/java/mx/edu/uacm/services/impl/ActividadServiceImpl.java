package mx.edu.uacm.services.impl;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import mx.edu.uacm.domain.Actividad;
import mx.edu.uacm.repository.ActividadRepository;
import mx.edu.uacm.services.ActividadService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 
 * @author Luis David
 *
 */
@Service
public class ActividadServiceImpl implements ActividadService {
  public static final Logger log = LogManager.getLogger(ActividadServiceImpl.class);
  @Autowired
  private ActividadRepository actividadRepository;

  @Override
  public Actividad guardarActividad(Actividad vehiculo) {

    return actividadRepository.save(vehiculo);
  }

  @Override
  public Actividad actualizarActividad(Actividad actividad) {
    return actividadRepository.save(actividad);
  }

  @Override
  public boolean eliminarActividad(Actividad actividad) {
    Actividad a;
    if (actividad != null) {
      actividadRepository.delete(actividad);
      a = obtenerActividad(actividad.getId());
      if (a == null) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Actividad obtenerActividad(Long idActividad) {
    Optional<Actividad> op = actividadRepository.findById(idActividad);
    Actividad actividad;
    try {
      actividad = op.get();
    } catch (NoSuchElementException e) {
      return null;
    }
    return actividad;
  }

  @Override
  public ArrayList<Actividad> obtenerActividades() {
    Iterable<Actividad> it = actividadRepository.findAll();
    ArrayList<Actividad> actividades = new ArrayList<Actividad>();
    it.iterator().forEachRemaining(actividades::add);
    return actividades;
  }

}
