package Domain.Notificaciones.Sugerencias;

import Domain.Heladera.Heladera;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tiposugerencia")
public abstract class Sugerencia {
     @Id
     @GeneratedValue
     int id;
     @ManyToOne
     Heladera h;
     boolean estaAceptado;

  public Sugerencia() {
  }

  abstract public void sugerencias(Heladera h);
    abstract public void finalizarSugerencia();
    abstract public String mostrarHeladera();
    abstract public Heladera getHeladera();
}
