package Domain.Colaborador.RegistroPuntaje;

import Domain.Colaborador.Colaborador;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class RegistroPuntaje {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Colaborador colaborador;

  @Column
  private double puntaje;

  @Column
  private LocalDate fecha;



  public RegistroPuntaje(Colaborador colaborador, double puntaje) {
    this.colaborador = colaborador;
    this.puntaje = puntaje;
    this.fecha = LocalDate.now();
  }

  public RegistroPuntaje() {

  }

  public Colaborador getColaborador() {
    return colaborador;
  }

  public double getPuntaje() {
    return puntaje;
  }

  public LocalDate getFecha() {
    return fecha;
  }
}

