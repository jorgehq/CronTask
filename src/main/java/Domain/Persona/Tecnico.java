package Domain.Persona;

import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  String nombre;
  @Column
  String mail;
  @OneToMany
      @JoinColumn(name = "tecnico_id")
  List<Visita> visitas;
  @Embedded
  Direccion direccion;
  @Column
  String longitud;
  @Column
  String latitud;

  public Tecnico() {
  }

  public Tecnico(String nombre, String mail, Direccion direccion, String longitud, String latitud) {
    this.nombre = nombre;
    this.mail = mail;
    this.visitas = new ArrayList<>();
    this.direccion = direccion;
    this.longitud = longitud;
    this.latitud = latitud;
  }

  public String getLongitud() {
    return longitud;
  }

  public String getLatitud() {
    return latitud;
  }
}
