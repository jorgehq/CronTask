package Tests.Domain.Repos;

import APIs.TSensor.TSensor;
import APIs.WSensor.WSensor;
import Domain.Colaborador.Colaborador;
import Domain.Colaborador.PersonaHumana;
import Domain.Colaborador.PersonaJuridica;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Repositorios.RepoColaboraciones;
import Domain.Repositorios.RepoColaboradores;
import Domain.Ubicacion.Direccion;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class RepoColaboradoresTests {
  /*
  TSensor sensorTemperaturaAPI = mock(TSensor.class);
  WSensor sensorPesoAPI = mock(WSensor.class);
  Colaborador humano = new PersonaHumana("Jara",null,null,null,null, TipoDocumento.DNI,1234567891);
  Colaborador juridico = new PersonaJuridica("pepe",null,new Direccion(),null,null);
  @BeforeEach
  void inicializar() {
    RepoColaboradores.getInstance().resetInstance();
    RepoColaboraciones.getInstance().resetInstance();
    RepoColaboradores.getInstance().addColaborador(humano);
    RepoColaboradores.getInstance().addColaborador(juridico);
  }

*/

  //@Test
  //public void calcularPuntajes() {
  // Heladera heladeraOrigen = new Heladera(
  //      "neverita",
  //      10,
  //      "1.15", "1.15",
  //      new Direccion(), LocalDate.now(),
  //     new EstadoHeladera(
  //        new BigDecimal("20.1"), // temperatura maxima
  //        new BigDecimal("2.5"),  // temperatura minima
  //        new AdapterSensorTemperatura(sensorTemperaturaAPI, "1234"),
  //        new AdapterSensorPeso(sensorPesoAPI, "4321")));
  //Heladera heladeraDestino = new Heladera(
  //    "neverita",
  //    10,
  //    "1.15", "1.15",
  //    new Direccion(), LocalDate.now(),
  //    new EstadoHeladera(
  //        new BigDecimal("20.1"), // temperatura maxima
  //        new BigDecimal("2.5"),  // temperatura minima
  //        new AdapterSensorTemperatura(sensorTemperaturaAPI, "1234"),
  //        new AdapterSensorPeso(sensorPesoAPI, "4321")));


  //humano.realizarColaboracion(new DonarDinero(1000, LocalDate.now(), FrecuenciaDeDonacion.MENSUAL));
  //humano.realizarColaboracion(new DonarVianda(new Vianda(LocalDate.now().plusMonths(7), LocalDate.now(), 600, 10, true, humano, heladeraOrigen)));


//    juridico.realizarColaboracion(new HacerseCargoDeHeladera(heladeraOrigen));
  //  juridico.realizarColaboracion(new RegistrarUnaPersonaVulnerable(
  //    new PersonaVulnerable(null,null,null,null,null,3),
  //    juridico,new Tarjeta("11111111111", LocalDate.now())));

  //List<Double> puntajes = RepoColaboradores.getInstance().calcularPuntajes(LocalDate.of(2020,9,12),LocalDate.of(2030,12,12));
  //Assertions.assertEquals(puntajes.size(), 2);
  //Assertions.assertEquals(puntajes.get(0),545);
  //Assertions.assertEquals(puntajes.get(1),200);
  //}
}
