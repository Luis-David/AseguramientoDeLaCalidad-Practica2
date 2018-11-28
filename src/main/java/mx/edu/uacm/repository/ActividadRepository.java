package mx.edu.uacm.repository;

import mx.edu.uacm.domain.Actividad;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActividadRepository extends CrudRepository<Actividad,Long> {
  
}
