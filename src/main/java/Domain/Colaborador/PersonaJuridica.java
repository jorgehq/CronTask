package Domain.Colaborador;

import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Heladera.Heladera;
import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonaJuridica extends Colaborador {
  @Column
  private String razonSocial;
  @Enumerated(EnumType.STRING)
  private Rubro rubro;
  @Enumerated(EnumType.STRING)
  private TipoRazonSocial tipoRazonSocial;
  @OneToMany
  @JoinColumn(name = "personaEncargada_id")
  private List<Heladera> heladeraList = new ArrayList<>();


  public void agregarHeladera(Heladera heladera) {
    this.heladeraList.add(heladera);
  }

  public PersonaJuridica(){}
  public PersonaJuridica(String razonSocial, List<Mediodecontacto> mediosDeContacto, Direccion direccion, Rubro rubro, TipoRazonSocial tipoRazonSocial) {
    super(direccion, mediosDeContacto);
    this.razonSocial = razonSocial;
    this.rubro = rubro;
    this.tipoRazonSocial = tipoRazonSocial;
  }


  @Override
  public Heladera ultimaHeladera() {
    return this.heladeraList.get(this.heladeraList.size() - 1);
  }

  @Override
  public long getDocumento() {
    return 0;
  }


  @Override
  public TipoDocumento getTipoDocumento() {
    return null;
  }

  public String getNombreUsuario() {
    return null;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public List<Heladera> getHeladeraList() {
    return heladeraList;
  }
}
