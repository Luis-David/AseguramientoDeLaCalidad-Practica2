package mx.edu.uacm.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Estudiante {
	@Id
	private String matricula;
	private String nombre;
	private String apellidos;
	@OneToMany(mappedBy="estudiante",cascade=CascadeType.ALL,orphanRemoval=true,
			fetch=FetchType.LAZY)
	private List<Actividad> actividades;
	public Estudiante(String m,String n,String a) {
		matricula=m;
		nombre=n;
		apellidos=a;
		actividades=new ArrayList<Actividad>();
	}
	public Estudiante() {
		actividades=new ArrayList<Actividad>();
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public List<Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	public void setActividad(Actividad actividad) {
		actividad.setEstudiante(this);
		actividades.add(actividad);
		
	}
	@Override
	public boolean equals(Object o) {
		Estudiante e=(Estudiante)o;
		if(this.nombre.equals(e.getNombre())
				&& this.matricula.equals(e.getMatricula())
				&& this.apellidos.equals(e.getApellidos())) {
			return true;
		}
		return false;
	}
}
