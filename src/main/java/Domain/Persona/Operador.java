package Domain.Persona;

import static java.lang.reflect.Array.get;

import Domain.Solicitudes.SolicitudTarjeta;

import Domain.Tarjeta.TarjetaColaborador;
import Domain.Usuarios.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Operador {

  @Id
  @GeneratedValue
  Long id;
  public String nombre;
  @OneToOne
  private Usuario usuario;

  public void procesarSolicitudTarjeta(SolicitudTarjeta solicitudTarjeta, TarjetaColaborador tarjeta) {

    solicitudTarjeta.colaborador.setTarjeta(tarjeta);
    tarjeta.entregaTarjeta();
    tarjeta.setColaborador(solicitudTarjeta.getColaborador());
    solicitudTarjeta.setEnviada(true);
  }
}
