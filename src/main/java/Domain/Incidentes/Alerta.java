package Domain.Incidentes;

import Domain.Heladera.Heladera;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Alerta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private LocalDateTime fechaYHora;
  @ManyToOne
  private Heladera heladera;
  @Enumerated(EnumType.STRING)
  private TipoAlerta tipoAlerta;

  public Alerta(LocalDateTime fechaYHora, Heladera heladera, TipoAlerta tipoAlerta) {
    this.fechaYHora = fechaYHora;
    this.heladera = heladera;
    this.tipoAlerta = tipoAlerta;
  }

  public Alerta() {

  }
}
