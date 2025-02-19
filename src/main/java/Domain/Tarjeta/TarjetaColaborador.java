package Domain.Tarjeta;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import Domain.Exception.CodigoInvalidoException;
import Domain.Heladera.Heladera;
import Domain.Repositorios.RepoSolicitudColaboracion;
import Domain.Repositorios.RepoTarjetaColaborador;
import Domain.Solicitudes.Controlador.ControladorDeAccesoHeladeras;
import Domain.Solicitudes.SolicitudColaboracion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class TarjetaColaborador {

  @Id
  @GeneratedValue
  Long id;
  private String codigo;
  private LocalDate fechaRegistro;
  private boolean entregada;
  @OneToOne
  @JoinColumn(name = "colaborador_id")
  private Colaborador colaborador;



  public TarjetaColaborador(String codigo, LocalDate fechaRegistro) {
    //chequear si es necesario
      this.codigo = codigo;

    this.fechaRegistro = fechaRegistro;
    this.entregada = false;
  }

  public TarjetaColaborador() {

  }

  public void agregarSolicitud(TipoDeColaboracion colaboracion, Heladera heladera, Map<String, String> datos,int cantidad) {

    SolicitudColaboracion solicitud = new SolicitudColaboracion(colaboracion, heladera, this,datos,cantidad);

    solicitud.setEntregada(true);

    RepoSolicitudColaboracion.getInstance().guardar(solicitud);

  }

  public Colaborador getColaborador() {
    return colaborador;
  }



  public void entregaTarjeta() {
    this.entregada = true;
  }


  public LocalDate getFechaRegistro() {
    return fechaRegistro;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public void setColaborador(Colaborador colaborador) {
    this.colaborador = colaborador;
    this.entregada=true;
  }


  public Long getId() {
    return id;
  }
}
