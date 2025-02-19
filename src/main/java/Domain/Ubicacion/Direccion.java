package Domain.Ubicacion;

import jakarta.persistence.*;

@Embeddable
public class Direccion {
  @Enumerated(EnumType.STRING)
  Provincia provincia;
  @Enumerated(EnumType.STRING)
  Localidad localidad;
  @Column
  public String direccion;

  public Direccion(Provincia provincia, Localidad localidad, String direccion) {
    this.provincia = provincia;
    this.localidad = localidad;
    this.direccion = direccion;
  }

  public Direccion() {
  }

  public String getDireccion() {
    return direccion;
  }

  public Provincia getProvincia() {
    return provincia;
  }

  public Localidad getLocalidad() {
    return localidad;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
}
