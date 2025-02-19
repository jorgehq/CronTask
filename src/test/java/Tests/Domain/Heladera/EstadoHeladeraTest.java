package Tests.Domain.Heladera;

import static org.mockito.Mockito.*;

import APIs.TSensor.TSensor;
import APIs.WSensor.Reading;
import APIs.WSensor.WSensor;
import Domain.Heladera.EstadoHeladera;
import Domain.Heladera.Heladera;
import Domain.Heladera.NivelDeLlenado;
import Domain.Heladera.Sensores.AdapterSensorPeso;
import Domain.Heladera.Sensores.AdapterSensorTemperatura;
import Domain.Heladera.Vianda;
import Domain.Ubicacion.Direccion;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstadoHeladeraTest {
 /*
  Heladera heladera;
  TSensor sensorTemperaturaAPI = mock(TSensor.class);
  WSensor sensorPesoAPI = mock(WSensor.class);

  @BeforeEach
  void inicializar() {
    heladera = new Heladera(
        "neverita",
        10,
        "1.15", "1.15",
        new Direccion(), LocalDate.now(),

        new BigDecimal("20.1"), // temperatura maxima
        new BigDecimal("2.5"),  // temperatura minima
        new AdapterSensorTemperatura(sensorTemperaturaAPI, "1234"),
        new AdapterSensorPeso(sensorPesoAPI, "4321"),
        null);
  }

  @Test
  public void cuandoElNivelDeLlenadoEsMedioYDevuelveLaLecturaEnLibrasElNivelDeLlenadoEsMedio() {
    when(sensorPesoAPI.getWeight("4321")).thenReturn(new Reading(4*0.4*2.205,"lbs"));
    for (int i=0; i<4; i++){
      heladera.agregarVianda(new Vianda(null,null,null,null,null,null,null));
    }
    Assertions.assertEquals(NivelDeLlenado.MEDIO, heladera.getEstado().getNivelDeLlenado());
  }

  @Test
  public void cuandoNoHayViandasElNivelDeLlenadoEsBajo() {
    when(sensorPesoAPI.getWeight("4321")).thenReturn(new Reading(0,"KG"));
    Assertions.assertEquals(NivelDeLlenado.BAJO, heladera.getEstado().getNivelDeLlenado() );
  }
  @Test
  public void cuandoNoHayViandasElNivelDeLlenadoEsMedio() {
    when(sensorPesoAPI.getWeight("4321")).thenReturn(new Reading(4*0.4,"KG"));
    for (int i=0; i<4; i++){
      heladera.agregarVianda(new Vianda(null,null,null,null,null,null,null));
    }
    Assertions.assertEquals(NivelDeLlenado.MEDIO, heladera.getEstado().getNivelDeLlenado());
  }
  @Test
  public void cuandoNoHayViandasElNivelDeLlenadoEsAlto() {
    when(sensorPesoAPI.getWeight("4321")).thenReturn(new Reading(8*0.4,"KG"));
    for (int i=0; i<8; i++){
      heladera.agregarVianda(new Vianda(null,null,null,null,null,null,null));
    }
    Assertions.assertEquals(NivelDeLlenado.ALTO, heladera.getEstado().getNivelDeLlenado());
  }

  @Test
  public void cuandoLaTemperaturaDeLaHeladeraEstaDentroDelRangoLaHeladeraNoEstaAveriada() {
    for(int i=0; i<3; i++){
      heladera.getEstado().updateLecturaSensorTemperatura(new BigDecimal("3"));
    }

    Assertions.assertEquals(false, heladera.getEstado().getHeladeraAveriada());
  }

  @Test
  public void cuandoLaTemperaturaDeLaHeladeraEstaFueraDelRangoLaHeladeraEstaAveriada() {
    for(int i=0; i<3; i++){
      heladera.getEstado().updateLecturaSensorTemperatura(new BigDecimal("1"));
    }

    Assertions.assertEquals(true, heladera.getEstado().getHeladeraAveriada());
  }
*/
}
