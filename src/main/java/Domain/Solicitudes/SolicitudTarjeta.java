package Domain.Solicitudes;

import Domain.Colaborador.Colaborador;
import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class SolicitudTarjeta {
  @Id
  @GeneratedValue
  Long id;
  public boolean enviada;
  @ManyToOne
  public Colaborador colaborador;
  @Embedded
  public Direccion direccionEntrega;
  public LocalDateTime fechaDeSolicitud;

  public SolicitudTarjeta(Colaborador colaborador, Direccion direccionEntrega) {
    this.colaborador = colaborador;
    this.direccionEntrega = direccionEntrega;
    this.enviada = false;
    this.fechaDeSolicitud = LocalDateTime.now();
  }

  public SolicitudTarjeta() {

  }


  public void setEnviada(boolean enviada) {
    this.enviada = enviada;
  }

  public LocalDateTime getFechaDeSolicitud() {
    return this.fechaDeSolicitud;
  }

  public boolean isEnviada() {
    return this.enviada;
  }

  public Colaborador getColaborador() {
    return colaborador;
  }
}
