package Domain.Colaborador.TipoDeColaboracion;

import Domain.Colaborador.Colaborador;
import Domain.Persona.PersonaVulnerable;
import Domain.Repositorios.RepositorioPersonasVulnerables;
import Domain.Tarjeta.Tarjeta;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Entity
@DiscriminatorValue("RegistrarPersonaVulnerable")
public class RegistrarUnaPersonaVulnerable extends TipoDeColaboracion {

  @OneToOne
  private PersonaVulnerable personaVulnerable;

  @OneToOne
  private Tarjeta tarjeta;
  @Column
  LocalDate fecha;



  public RegistrarUnaPersonaVulnerable(PersonaVulnerable personaVulnerable, Tarjeta tarjeta) {
    this.personaVulnerable = personaVulnerable;

    this.tarjeta=tarjeta;
    this.fecha=LocalDate.now();
  }

  public RegistrarUnaPersonaVulnerable() {

  }

  public PersonaVulnerable getPersonaVulnerable() {
    return this.personaVulnerable;
  }


  @Override
  public void colaborar() {
    personaVulnerable.asignarTarjeta(tarjeta);
  }

  @Override
  public double valorColaboracion() {

    if(personaVulnerable.getTarjeta()==null){
      return ((double) ChronoUnit.MONTHS.between(LocalDate.now(),fecha.minusMonths(5))*1)*-2;
    }
    return ((double) ChronoUnit.MONTHS.between(LocalDate.now(),fecha.minusMonths(5))*personaVulnerable.getTarjeta().getIntentos())*-2;

  }
  @Override
  public LocalDate getFecha() {
    return this.fecha;
  }

}
