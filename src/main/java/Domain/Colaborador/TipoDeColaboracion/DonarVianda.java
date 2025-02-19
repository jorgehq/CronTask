package Domain.Colaborador.TipoDeColaboracion;

import Domain.Colaborador.Colaborador;
import Domain.Heladera.Heladera;
import Domain.Heladera.Vianda;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
@Entity
public class DonarVianda extends TipoDeColaboracion {

  @OneToOne(cascade = CascadeType.ALL)
  Vianda vianda;
  @Column
  LocalDate fecha;

  public DonarVianda() {
    this.fecha=LocalDate.now();
  }

  public DonarVianda(LocalDate fechaColaboracion, Vianda vianda) {
    super();
    this.vianda = vianda;
    this.fecha=fechaColaboracion;
  }

  @Override
  public void colaborar() {
    this.fecha = LocalDate.now();
  }
  @Override
  public double valorColaboracion() {
    return (double) ChronoUnit.WEEKS.between(LocalDate.now(),vianda.getFechaDeCaducidad())*1.5;
  }
  @Override
  public LocalDate getFecha() {
    return this.fecha;
  }

  public Vianda getVianda() {
    return vianda;
  }
}
