package Tests.Domain.Tarjeta;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import APIs.TSensor.TSensor;
import APIs.WSensor.Reading;
import APIs.WSensor.WSensor;
import Domain.Colaborador.Colaborador;
import Domain.Colaborador.PersonaHumana;
import Domain.Colaborador.TipoDeColaboracion.RegistrarUnaPersonaVulnerable;
import Domain.Exception.SinUsosDisponiblesException;
import Domain.Heladera.EstadoHeladera;
import Domain.Heladera.Heladera;
import Domain.Heladera.Sensores.AdapterSensorPeso;
import Domain.Heladera.Sensores.AdapterSensorTemperatura;
import Domain.Heladera.Vianda;
import Domain.Persona.PersonaVulnerable;
import Domain.Tarjeta.Tarjeta;
import Domain.Ubicacion.Direccion;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
public class TarjetaTest {
  Colaborador humano = new PersonaHumana("Jara",null,null,null,null, null, 1122321);
  Tarjeta tarjeta = new Tarjeta("12345678912",LocalDate.now());
  PersonaVulnerable personaVulnerable = new PersonaVulnerable("Brian", null, null, null, null, 0);
  Heladera heladera;
  TSensor sensorTemperaturaAPI = mock(TSensor.class);
  WSensor sensorPesoAPI = mock(WSensor.class);
  @BeforeEach
  void inicializar() {
    humano.realizarColaboracion(new RegistrarUnaPersonaVulnerable(personaVulnerable,humano,tarjeta));
    when(sensorPesoAPI.getWeight("4321")).thenReturn(new Reading(0,"KG"));
    heladera = new Heladera(
            "neverita",
            10,
            "1.15", "1.15",
            new Direccion(), LocalDate.now(),null
            ,null,null,null,null);
    for (int i = 0; i < 10; i++) {
      heladera.agregarVianda(new Vianda(null,null,null,null,null,null,null));
    }
  }

  @Test
  public void unaPersonaPuedeUsar4vecesLaTarjetaAlDia() {
    for (int i = 0; i < 4; i++) {
      personaVulnerable.retirarVianda(heladera);
    }
    Assertions.assertTrue(tarjeta.getIntentos() == 4);
  }

  @Test
  public void cuandoSePasanLosUsosDiariosLanzaUnaExcepcion() {
    for (int i = 0; i < 4; i++) {
      personaVulnerable.retirarVianda(heladera);
    }
    Assertions.assertThrows(SinUsosDisponiblesException.class, () -> personaVulnerable.retirarVianda(heladera));
  }

  @Test
  public void siLaPersonaTieneUnMenorACargoLaPuedeUsarDosVecesMas() {
    PersonaVulnerable personaVulnerable2 = new PersonaVulnerable("juan", null, null, null, null, 1);
    Tarjeta tarjeta = new Tarjeta("12345678901",LocalDate.now());
    humano.realizarColaboracion(new RegistrarUnaPersonaVulnerable(personaVulnerable2,humano,tarjeta));
    for (int i = 0; i < 6; i++) {
      personaVulnerable2.retirarVianda(heladera);
    }
    Assertions.assertTrue(tarjeta.getIntentos() == 6);
  }


}

 */
