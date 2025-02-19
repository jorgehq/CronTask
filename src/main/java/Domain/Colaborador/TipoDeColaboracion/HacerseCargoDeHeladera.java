package Domain.Colaborador.TipoDeColaboracion;

import Domain.Colaborador.PersonaJuridica;
import Domain.Heladera.Heladera;
import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
@Entity
public class HacerseCargoDeHeladera extends TipoDeColaboracion {

  @OneToOne(cascade = CascadeType.PERSIST)
  Heladera heladera;
  @Column
  LocalDate fecha;

  public HacerseCargoDeHeladera(Heladera heladera) {
    this.heladera = heladera;
  }

  public HacerseCargoDeHeladera() {

  }

  @Override
  public void colaborar() {  //consultar si recibe heladera o los atributos y la crea el
    this.fecha = LocalDate.now();
  }
  @Override
  public double valorColaboracion() {
    //Si ves esto el numero que me da el between es negativo por lo que para invertir el valor lo hoce asi
    return ((double) ChronoUnit.MONTHS.between(LocalDate.now(), heladera.getFechaPuestaEnMarcha().minusMonths(4)) * heladera.getCapacidadDeViandas()) * -5;
  }
  public Heladera getHeladera() {
    return heladera;
  }
  @Override
  public LocalDate getFecha() {
    return this.fecha;
  }
}
