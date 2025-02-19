package Domain.Persona;

import Domain.Exception.SinUsosDisponiblesException;
import Domain.Heladera.Heladera;
import Domain.Heladera.Vianda;
import Domain.Tarjeta.MovimientoTarjeta;
import Domain.Tarjeta.Tarjeta;
import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;
@Entity
public class PersonaVulnerable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  String nombre;
  @Column
  LocalDate fechaDeNacimiento;
  @Column
  LocalDate fechaDeRegistro;
  @Transient
  Optional<Direccion> domicilio;
  @Transient
  Optional<String> DNI;
  @Column
  Integer cantidadDeMenoresACargo;
  @OneToOne
  Tarjeta tarjeta;

  public PersonaVulnerable(){}
  public PersonaVulnerable(String nombre, LocalDate fechaDeNacimiento, LocalDate fechaDeRegistro, Optional<Direccion> domicilio,  Optional<String> DNI,
                           Integer cantidadDeMenoresACargo) {
    this.nombre = nombre;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.fechaDeRegistro = fechaDeRegistro;
    this.domicilio = domicilio;
    this.DNI = DNI;
    this.cantidadDeMenoresACargo = cantidadDeMenoresACargo;

  }

  public void retirarVianda(Heladera heladera) {
    this.tarjeta.utilizarTarjeta(this.cantidadDeMenoresACargo, heladera);
  }

  public void asignarTarjeta(Tarjeta tarjeta) {
    this.tarjeta = tarjeta;
  }

  public String getCodigoTarjeta() {
    return this.tarjeta.getCodigo();
  }

  public String getNombre() {
    return nombre;
  }

  public LocalDate getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public LocalDate getFechaDeRegistro() {
    return fechaDeRegistro;
  }

  public Direccion getDireccion() {
    if (domicilio.isPresent()) {
      return domicilio.get();
    }
    return null;
  }

  public String getDNI() {
    if (DNI.isPresent()) {
      return DNI.get();
    }
    return null;
  }

  public Integer getCantidadDeMenoresACargo() {
    return cantidadDeMenoresACargo;
  }

  public Tarjeta getTarjeta() {

    return tarjeta;
  }
}
