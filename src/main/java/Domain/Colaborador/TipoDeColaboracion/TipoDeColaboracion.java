package Domain.Colaborador.TipoDeColaboracion;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Tipo_de_Colaboracion")
public abstract class TipoDeColaboracion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  public abstract void colaborar();
  public abstract double valorColaboracion();
  public abstract LocalDate getFecha();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
