package mx.edu.uacm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.domain.Estudiante;
@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante,Long>{
	@Query(value="select * from estudiante where matricula=:idEstudiante",nativeQuery=true)
	public Estudiante obtenerEstudiante(@Param("idEstudiante")String idEstudiante);
}
