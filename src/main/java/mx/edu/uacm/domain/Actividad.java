package mx.edu.uacm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Actividad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String categoria;/*
                            * @OneToMany(mappedBy="Actividad",cascade=CascadeType.ALL,orphanRemoval=true,
                            * fetch=FetchType.LAZY)
                            */
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Estudiante estudiante;

  public Actividad() {

  }

  public Actividad(String n, String c) {
    nombre = n;
    categoria = c;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Estudiante getEstudiante() {
    return estudiante;
  }

  public void setEstudiante(Estudiante estudiante) {
    this.estudiante = estudiante;
  }

  @Override
  public boolean equals(Object o) {
    Actividad a = (Actividad) o;
    if (nombre.equals(a.getNombre()) && categoria.equals(a.getCategoria()) && id == a.getId()) {
      if (estudiante == a.getEstudiante()) {
        return true;
      } else if (estudiante.getMatricula().equals(a.getEstudiante().getMatricula())) {
        return true;
      }
    }
    return false;
  }

}
