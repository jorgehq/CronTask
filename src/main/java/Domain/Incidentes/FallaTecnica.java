package Domain.Incidentes;

import Domain.Colaborador.Colaborador;
import Domain.Heladera.Heladera;
import Domain.Notificaciones.NotificacionIncidente;
import Domain.Notificaciones.Sugerencias.Sugerencia;
import Domain.Notificaciones.Sugerencias.SugerenciaAlAzar;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class FallaTecnica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private LocalDateTime fechaYHora;
  @ManyToOne
  private Heladera heladera;
  @Column
  private String descripcion; // Tiene q poder ser null
  @Column
  private String pathImagen; // Tiene q poder ser null
  @ManyToOne
  private Colaborador colaborador;

  public FallaTecnica(LocalDateTime fechaYHora, Heladera heladera, String descripcion, String pathImagen, Colaborador colaborador) {
    this.fechaYHora = fechaYHora;
    this.heladera = heladera;
    this.descripcion = descripcion;
    this.pathImagen = pathImagen;
    this.colaborador = colaborador;


  }

  public FallaTecnica() {

  }

  public Heladera getHeladera() {
    return heladera;
  }

  public Colaborador getColaborador() {
    return colaborador;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getFechaYHora() {
    return fechaYHora;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getPathImagen() {
    return pathImagen;
  }
}
