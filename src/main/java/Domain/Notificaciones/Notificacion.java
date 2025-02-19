package Domain.Notificaciones;

import Domain.Heladera.Heladera;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoNotificacion")
public abstract class Notificacion {
     @Id
     @GeneratedValue
     Long id;
     int cantidadViandas;

     LocalDate fecha;
     String descripcion;
     @ManyToOne
     Heladera heladera;



     public Notificacion() {
     }

     abstract public String generarMensaje();
     public String generarTitulo() {
          return "Notificacion de colaboracion Heladera en "+heladera.getNombre();
     }
     abstract public void aceptarSugerencia(int cantidad);

     public int getCantidadViandas() {
          return cantidadViandas;
     }

     public Heladera getHeladera() {
          return heladera;
     }

     public Long getId() {
          return id;
     }

     public LocalDate getFecha() {
          return fecha;
     }
     public String getTipoNotificacion() {
          return this instanceof NotificacionFaltanViandas ? "FaltanViandas" :
              this instanceof NotificacionIncidente ? "Incidente" : "Otro";
     }
}
