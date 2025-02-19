package Domain.Colaborador.TipoDeColaboracion;

import Domain.Heladera.Heladera;
import Domain.Repositorios.RepoHeladera;
import jakarta.persistence.*;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Entity
public class DistribuirVianda extends TipoDeColaboracion {

  @ManyToOne
  private Heladera heladeraOrigen;
  @ManyToOne
  private Heladera heladeraDestino;
  @Column
  private int cantidad;
  @Column
  private String motivo;
  @Column
  private LocalDate fecha;

  public DistribuirVianda(Heladera origen, Heladera destino, int cantidad, String motivo, LocalDate fecha) {
    this.heladeraOrigen = origen;
    this.heladeraDestino = destino;
    this.cantidad = cantidad;
    this.motivo = motivo;
    this.fecha = fecha;
  }

  public DistribuirVianda() {

  }

  @Override

  public void colaborar() {
    int contador=0;
    while(cantidad != contador && heladeraOrigen.getViandasEnHeladera().size()!=0  ) {
      heladeraDestino.agregarVianda(heladeraOrigen.quitarVianda());
      contador++;
    }
    RepoHeladera.getInstance().guardar(heladeraOrigen);
    RepoHeladera.getInstance().guardar(heladeraDestino);
  }
  @Override
  public double valorColaboracion() {
    return cantidad;
  }

  public int getCantidad() {
    return cantidad;
  }

  public String getMotivo() {
    return motivo;
  }
  @Override
  public LocalDate getFecha() {
    return fecha;
  }

  public void setHeladeraOrigen(Heladera heladeraOrigen) {
    this.heladeraOrigen = heladeraOrigen;
  }

  public void setHeladeraDestino(Heladera heladeraDestino) {
    this.heladeraDestino = heladeraDestino;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }


}
