package Domain.Heladera;

import Domain.Colaborador.Colaborador;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vianda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column
  LocalDate fechaDeCaducidad;
  @Column
  LocalDate fechaDeDonacion;
  @Column
  Integer calorias;
  @Column
  Integer peso;
  @Column
  Boolean fueEntregada;

  @ManyToOne
  @JoinColumn(name = "colaborador_id")
  Colaborador colaborador;
  @ManyToOne
  @JoinColumn(name = "heladera_id")
  Heladera heladera;

  public Vianda() {

  }

  public void setHeladera(Heladera heladera) {
    this.heladera = heladera;
  }

  public Heladera getHeladera() {
    return heladera;
  }

  public Vianda(LocalDate fechaDeCaducidad, LocalDate fechaDeDonacion, Integer calorias, Integer peso, Boolean fueEntregada, Colaborador colaborador, Heladera heladera) {
    this.fechaDeCaducidad = fechaDeCaducidad;
    this.fechaDeDonacion = fechaDeDonacion;
    this.calorias = calorias;
    this.peso = peso;
    this.fueEntregada = fueEntregada;
    this.colaborador = colaborador;
    this.heladera=heladera;
  }

  public LocalDate getFechaDeCaducidad() {
    return fechaDeCaducidad;
  }

  public Long getId() {
    return id;
  }

  public Integer getCalorias() {
    return calorias;
  }

  public Integer getPeso() {
    return peso;
  }

  public Colaborador getColaborador() {
    return colaborador;
  }
}
