package mx.edu.uacm.services;

import java.util.ArrayList;

import mx.edu.uacm.domain.Actividad;

import org.springframework.stereotype.Service;



@Service
public interface ActividadService {
  public Actividad guardarActividad(Actividad actividad);

  public Actividad actualizarActividad(Actividad actividad);

  public boolean eliminarActividad(Actividad actividad);

  public Actividad obtenerActividad(Long idActividad);

  public ArrayList<Actividad> obtenerActividades();
}
