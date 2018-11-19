package mx.edu.uacm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.domain.Actividad;
@Repository
public interface ActividadRepository extends CrudRepository<Actividad,Long>{

}
