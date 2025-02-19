package Tests.Domain.Colaborador;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.PersonaHumana;
import Domain.Colaborador.PersonaJuridica;
import Domain.Colaborador.TipoDeColaboracion.RegistrarUnaPersonaVulnerable;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Persona.PersonaVulnerable;
import Domain.Repositorios.RepositorioPersonasVulnerables;
import Domain.Tarjeta.Tarjeta;
import Domain.Ubicacion.Direccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ColaboradorTest {
    Colaborador humano = new PersonaHumana("Jara",null,null,null,null,TipoDocumento.DNI,1234567891);
    Colaborador juridico = new PersonaJuridica("pepe",null,new Direccion(),null,null);


    @BeforeEach
    void inicializar() {

    }
        /*
    @Test
    public void distribuirViandas(){
        Heladera heladeraOrigen = new Heladera(null,10,null,null,null,null,null);
        Heladera heladeraDestino = new Heladera(null,10,null,null,null,null,null);
        Vianda vianda = new Vianda(null,null,null,null,null,null,heladeraOrigen);
        heladeraOrigen.agregarVianda(vianda);
        List<Object> listaDeObjetos = new ArrayList<>();
        listaDeObjetos.add(heladeraOrigen);
        listaDeObjetos.add(heladeraDestino);
        listaDeObjetos.add(1);
        listaDeObjetos.add("estamos prvando cosas nuevas");
        listaDeObjetos.add(LocalDate.now());
        humano.realizarColaboracion(new DistribuirVianda(), listaDeObjetos);
        Assertions.assertTrue(heladeraDestino.quitarVianda()==vianda);
    }
    @Test
    public void donarVianda(){
        Heladera heladera = new Heladera(null,10,null,null,null,null,null);


        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos.add(LocalDate.now());
        listaObjetos.add(LocalDate.now());
        listaObjetos.add(1945);
        listaObjetos.add(2);
        listaObjetos.add(true);
        listaObjetos.add(humano);
        listaObjetos.add(heladera);
        humano.realizarColaboracion(new DonarVianda(), listaObjetos);

        Assertions.assertTrue(heladera.quitarVianda().getHeladera() == heladera);
    }
    // hay q cambiarlo
  /*
    @Test
    public void donarPlata(){

        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos.add(LocalDate.now());
        listaObjetos.add(1000);
        listaObjetos.add(FrecuenciaDeDonacion.MENSUAL);
        juridico.realizarColaboracion(new DonarDinero(), listaObjetos);
        Assertions.assertTrue(juridico.ultimaTranferencia().getMonto() == 1000);
    }
    @Test
    public void hacerseCargoDeHeladera(){
        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos.add("Heladera De Jara");
        listaObjetos.add(8);
        listaObjetos.add("123");
        listaObjetos.add("321");
        listaObjetos.add(juridico.getDireccion());
        listaObjetos.add(LocalDate.now());
        juridico.realizarColaboracion(new HacerseCargoDeHeladera(), listaObjetos);
        Assertions.assertTrue(juridico.ultimaHeladera().getNombre() == "Heladera De Jara");
    }
*/
    /*
    @Test
    public void cuandoSeRegistraUnaPersonaVulnerableSeLeDaUnaTarjeta(){
        Tarjeta tarjeta = new Tarjeta("12345678912",LocalDate.now());
        PersonaVulnerable personaVulnerable = new PersonaVulnerable("Brian", null, null, null, null, 0);
        TipoDeColaboracion registrar = new RegistrarUnaPersonaVulnerable(personaVulnerable,humano,tarjeta);
        humano.realizarColaboracion(registrar);
        Assertions.assertTrue(personaVulnerable.getCodigoTarjeta() == "12345678912");
    }

    @Test
    public void cuandoSeRegistraUnaPersonaVulnerableSeLaGuardaEnUnRepo(){
        Tarjeta tarjeta = new Tarjeta("12345678912",LocalDate.now());
        PersonaVulnerable personaVulnerable = new PersonaVulnerable("Brian", null, null, null, null, 0);
        TipoDeColaboracion registrar = new RegistrarUnaPersonaVulnerable(personaVulnerable,humano,tarjeta);
        humano.realizarColaboracion(registrar);
        Assertions.assertTrue(RepositorioPersonasVulnerables.getInstance().numeroDePersonasVulnerables() == 1);
    }

     */
}
