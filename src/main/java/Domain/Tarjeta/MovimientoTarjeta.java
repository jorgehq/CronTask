package Domain.Tarjeta;

import Domain.Heladera.Heladera;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class MovimientoTarjeta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  public Heladera heladera;
  @Column
  public LocalDate fecha;

  public MovimientoTarjeta() {
  }

  public MovimientoTarjeta(Heladera heladera) {
    this.heladera = heladera;
    this.fecha = LocalDate.now();
  }
}
