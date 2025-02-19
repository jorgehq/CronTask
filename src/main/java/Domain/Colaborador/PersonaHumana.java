package Domain.Colaborador;

import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Heladera.Heladera;
import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PersonaHumana extends Colaborador {

  @Column
  private String nombre;
  @Column
  private String apellido;
  @Column
  private LocalDate fechaDeNacimiento;
  @Enumerated(EnumType.STRING)
  private TipoDocumento tipoDocumento;
  @Column
  private long documento;

  public PersonaHumana(String nombre, String apellido, LocalDate fechaDeNacimiento, Direccion direccion, List<Mediodecontacto> mediosDeContacto, TipoDocumento tipoDocumento, long documento) {
    super(direccion, mediosDeContacto);
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.documento = documento;
  }

  public PersonaHumana() {

  }

  @Override
  public Heladera ultimaHeladera() {
    return null;
  }

  @Override
  public long getDocumento() {
    return this.documento;
  }

  @Override
  public TipoDocumento getTipoDocumento() {
    return this.tipoDocumento;
  }

  public String getNombre() {
    return nombre;
  }

}
