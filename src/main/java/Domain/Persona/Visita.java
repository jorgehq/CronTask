package Domain.Persona;

import Domain.Heladera.Heladera;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Visita {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  LocalDateTime fechaYhora;
  @Column
  String imagen;
  @ManyToOne
  Heladera heladera;
  @Column
  String descripcion;
  @Column
  Boolean solucionado;


  public Visita(Boolean solucionado, String descripcion, Heladera heladera, String imagen) {
    this.solucionado = solucionado;
    this.descripcion = descripcion;
    this.heladera = heladera;
    this.imagen = imagen;
    this.fechaYhora = LocalDateTime.now();
  }

  public Visita() {

  }
}
