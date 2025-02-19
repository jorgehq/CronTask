package Tests.Domain.Persistencia;

import Domain.Colaborador.*;
import Domain.Colaborador.MedioDeContacto.InstantMessageApp;
import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Colaborador.TipoDeColaboracion.*;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Colaborador.Transferencia.FrecuenciaDeDonacion;
import Domain.Heladera.EstadoHeladera;
import Domain.Heladera.Heladera;
import Domain.Heladera.NivelDeLlenado;
import Domain.Heladera.Sensores.AdapterSensorPeso;
import Domain.Heladera.Sensores.AdapterSensorTemperatura;
import Domain.Heladera.Vianda;
import Domain.Importador.ImportadorColaboracionCsv;
import Domain.Notificaciones.Notificacion;
import Domain.Notificaciones.NotificacionFaltanViandas;
import Domain.Notificaciones.NotificacionIncidente;
import Domain.Persona.PersonaVulnerable;
import Domain.Repositorios.*;
import Domain.Solicitudes.SolicitudColaboracion;
import Domain.Tarjeta.Tarjeta;
import Domain.Tarjeta.TarjetaColaborador;
import Domain.Ubicacion.Direccion;
import Domain.Ubicacion.Localidad;
import Domain.Ubicacion.Provincia;
import Domain.Usuarios.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class HibernateMemoryDBTest {
/*
  private EntityManagerFactory emf;
  private EntityManager em;

  @Before
  public void setUp() {

    // Crear el EntityManagerFactory usando el persistence.xml
    //emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    //em = emf.createEntityManager();
  }

  @Test
  public void testGuardarYLeerEntidad() {



    Usuario usuario = new Usuario("Juan Cruz", "Rodriguez", "TREIKON", "JuanoFacultad123!");

    // hago "persistible" el usuario

    List <Usuario> lista=RepoUsuario.getInstance().obtenerTodos();
    System.out.println(lista.size());


    r.guardar(usuario);

    Usuario usuarioRecuperado = r.buscarPorId(usuario.getId());
    ;
    // Comprobar que el usuario se guardó y se recuperó correctamente
      System.out.println(usuario.getId()+" "+usuario.getNombreUsuario());
    assertNotNull(usuarioRecuperado);
    assertEquals("Juan Cruz", usuarioRecuperado.getNombre());
      System.out.println(usuarioRecuperado.getId()+" "+usuarioRecuperado.getNombreUsuario());




  }
  @Test
  public void testRepoColaboradores() throws InterruptedException {

    List<Mediodecontacto> medios=new ArrayList<>();

    medios.add(new Mediodecontacto("jor2018qw@gmail.com", InstantMessageApp.MAIL));


    Colaborador ph=new PersonaHumana("jonny","Colman", LocalDate.of(1998,7,17),new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA,"Alberdi")
            ,medios, TipoDocumento.DNI,984082);

    RepoColaboradores.getInstance().guardar(ph);


    System.out.println(ph.getId());

    Colaborador encontrado = RepoColaboradores.getInstance().buscarPorId(ph.getId());

    if (encontrado instanceof PersonaHumana) {
      PersonaHumana persona = (PersonaHumana) encontrado;
      System.out.println(persona.getNombre() + " " + persona.getId());
    } else if (encontrado instanceof PersonaJuridica) {
      PersonaJuridica negocio = (PersonaJuridica) encontrado;


    }
  }
  @Test
  public void testRepoColaboraciones() {
    Colaborador co = RepoColaboradores.getInstance().buscarPorId(2L);


    Vianda d = new Vianda(LocalDate.of(2026, 2, 2), LocalDate.now(), 1000, 4, false, co, null);
    RepoViandas.getInstance().guardar(d);

  DonarVianda don=new DonarVianda(d);

   // RepoColaboraciones.getInstance().guardar(don);

   // TipoDeColaboracion t = RepoColaboraciones.getInstance().buscarPorId(don.getId());


    if (t instanceof DonarVianda) {
      DonarVianda donado = (DonarVianda) t;
      System.out.println(donado.getId() + " Colaborador " + donado.getVianda().getColaborador().getId() + " Clorias " + donado.getVianda().getCalorias());

    }




  @Test
  public void testRepoColaboracionesPersonaVulnerable() {
    Colaborador co = RepoColaboradores.getInstance().buscarPorId(2L);
    Tarjeta tj=new Tarjeta("12a34345778",LocalDate.now());
    PersonaVulnerable p=new PersonaVulnerable("Ned",LocalDate.of(1996,4,12),LocalDate.now(), Optional.ofNullable(null),
        Optional.of("4356234"),0);
    //RepositorioPersonasVulnerables.getInstance().guardar(p);

    RegistrarUnaPersonaVulnerable r=new RegistrarUnaPersonaVulnerable(p,tj);
    r.colaborar();

    RepoColaboraciones.getInstance().guardar(r);

    TipoDeColaboracion cola=RepoColaboraciones.getInstance().buscarPorId(r.getId());

    if (cola instanceof RegistrarUnaPersonaVulnerable) {
      RegistrarUnaPersonaVulnerable registrado = (RegistrarUnaPersonaVulnerable) cola;
      System.out.println("ID colaboracion "+registrado.getId() + " nombrePSV " + registrado.getPersonaVulnerable().getNombre()
          + " Hijos " + registrado.getPersonaVulnerable().getCantidadDeMenoresACargo()+" "+registrado.getPersonaVulnerable().getCodigoTarjeta());

    }

  }

  @Test
  public void testRepoTarjetaPersonaVulnerable(){
    Colaborador co = RepoColaboradores.getInstance().buscarPorId(9L);

    Tarjeta tj= RepoTarjetas.getInstance().buscarPorNumeroTarjeta("12325678910");

    PersonaVulnerable p=new PersonaVulnerable("Matu2",LocalDate.of(1996,4,12),LocalDate.now(), Optional.ofNullable(null),
        Optional.of("4356234"),1);

    p.asignarTarjeta(tj);

    RepositorioPersonasVulnerables.getInstance().guardar(p);

    RegistrarUnaPersonaVulnerable r=new RegistrarUnaPersonaVulnerable(p,tj);

    RepoColaboraciones.getInstance().guardar(r);

    co.realizarColaboracion(r);

    RepoColaboradores.getInstance().guardar(co);

  }

  @Test
  public void testMovimientoTarjeta(){

  }
  @Test
  public void testRepoTarjetascolaborador(){
    TarjetaColaborador t=new TarjetaColaborador("12345678910",LocalDate.now());
    RepoTarjetaColaborador.getInstance().guardar(t);

    TarjetaColaborador encontrada=RepoTarjetaColaborador.getInstance().buscarPorId(t.getId());
    System.out.println("ID "+encontrada.getId()+" codigo "+encontrada.getCodigo());
  }
  @Test
  public void testHeladera(){


   Heladera heladera = new Heladera("Heladera Primario",100,"10","100",new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA, "Alberdi")
         ,LocalDate.now(),BigDecimal.valueOf(-10),BigDecimal.valueOf(4));

    Heladera heladera2 = new Heladera("Heladera SEcundario",100,"100","100",new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA, "Alberdi")
            ,LocalDate.now(),BigDecimal.valueOf(-10),BigDecimal.valueOf(4));

    RepoHeladera.getInstance().guardar(heladera);
    RepoHeladera.getInstance().guardar(heladera2);




    Heladera encontrada=RepoHeladera.getInstance().buscarPorId(heladera.getId());
    System.out.println( encontrada.getEstado().getSensorPeso().getNumeroDeSerie()+" "+encontrada.getEstado().getSensorTemperatura().getNumeroSerieTemperatura());

  }
  @Test
  public void testMovimientosTarjeta(){
    Heladera h=RepoHeladera.getInstance().buscarPorId(2L);

    h.agregarVianda(new Vianda(LocalDate.of(2026, 2, 2), LocalDate.now(), 1000, 4, false, null, null));
    System.out.println("Cantidad "+h.getViandasEnHeladera().size());
    Tarjeta l=RepoTarjetas.getInstance().buscarPorId(4L);
    l.utilizarTarjeta(0,RepoHeladera.getInstance().buscarPorId(2L));

    System.out.println("Cantidad "+h.getViandasEnHeladera().size());

  }
  @Test
  public void testDonaHeladera(){
    Heladera heladera = new Heladera("Heladera Principal",100,"10","100",new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA, "Alberdi")
        ,LocalDate.now(),BigDecimal.valueOf(-10),BigDecimal.valueOf(4));

    TipoDeColaboracion t=new HacerseCargoDeHeladera(heladera);
    t.colaborar();
    RepoColaboraciones.getInstance().guardar(t);
  }
  @Test
  public void TestDistribucionVianda(){
    Heladera h=RepoHeladera.getInstance().buscarPorId(5L);
    Heladera h1=RepoHeladera.getInstance().buscarPorId(6L);


    System.out.println("Cantidad heladera 1 "+h.getViandasEnHeladera().size());
    System.out.println("Cantidad heladera 2 "+h1.getViandasEnHeladera().size());

    TipoDeColaboracion c=new DistribuirVianda(h,h1,1,"Se murio",LocalDate.now());

    c.colaborar();
    RepoColaboraciones.getInstance().guardar(c);



  }

  @Test
  public void testUsuarioYColaborador(){
    List<Mediodecontacto> m=new ArrayList<>();
    m.add(new Mediodecontacto("mail.gmail",InstantMessageApp.MAIL));
    Colaborador c=new PersonaJuridica("empanadas SA",m,new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA,"Alberdi")
    , Rubro.ALIMENTACION, TipoRazonSocial.EMPRESA);

    Usuario usuario = new Usuario("Juan Cruz", "Rodriguez", "TREIKON22", "JuanoFacultad123!");


    if(RepoUsuario.getInstance().buscarPorNombre("TREIKON").size()!=0){
      System.out.println("El usuario ya existe");

    }else{
      RepoColaboradores.getInstance().guardar(c);
      System.out.println(c.getId()+"================================");
      usuario.asignarColaborador(c);

      RepoUsuario.getInstance().guardar(usuario);





    }

  }

  @Test
  public void testCicloCompletoColaboracion(){
    TipoDeColaboracion t=new DonarDinero(1000,LocalDate.now(),FrecuenciaDeDonacion.DIARIO);

    Colaborador c= RepoColaboradores.getInstance().buscarPorId(1L);
    System.out.println( "Cantidad colaboraciones "+ c.getColaboraciones().size());
   // System.out.println("Valor "+c.getColaboraciones().get(0).valorColaboracion());
    if (c instanceof PersonaHumana) {
      PersonaHumana persona = (PersonaHumana) c;
      System.out.println(persona.getNombre() + " " + persona.getId());
    } else if (c instanceof PersonaJuridica) {
      PersonaJuridica negocio = (PersonaJuridica) c;
      System.out.println( "Cantidad colaboraciones "+ negocio.getColaboraciones().size());
      System.out.println("Razon social "+negocio.getRazonSocial()+" propietario DNI "+negocio.getDocumento());
      //Para guardar objetos en una lista se debe guardar primero la colaboracion y luego meter a la lista
      RepoColaboraciones.getInstance().guardar(t);
      c.realizarColaboracion(t);
      RepoColaboradores.getInstance().guardar(c);

    }
  }
@Test
  public void testVerificarPuntajes(){
      List<Colaborador> lista=RepoColaboradores.getInstance().obtenerTodos();
  for(Colaborador c:lista){
    int puntajeTotal=0;
    for(TipoDeColaboracion t:c.getColaboraciones()){
      puntajeTotal+=t.valorColaboracion();
    }
    System.out.println("Puntaje del colaborador "+c.getDocumento()+" : "+puntajeTotal);
  }

}
@Test
  public void testSuscripcion(){
      Heladera h=RepoHeladera.getInstance().buscarPorId(5L);
  System.out.println("Cantidad de interesados: "+h.getInteresados().size());
      Colaborador c=RepoColaboradores.getInstance().buscarPorId(1L);
      h.suscribirse(c);
      RepoHeladera.getInstance().guardar(h);
      Heladera actualizada=RepoHeladera.getInstance().buscarPorId(h.getId());
  System.out.println("Cantidad de interesados: "+actualizada.getInteresados().size());

}
@Test
  public void testNotificacion(){
  Heladera h=RepoHeladera.getInstance().buscarPorId(5L);
  System.out.println("Capacidad "+h.getCapacidadDeViandas());
  Notificacion n=new NotificacionFaltanViandas(3,h);

  RepoNotificaciones.getInstance().guardar(n);
  h.notificarInteresados(n);
  System.out.println("notificaciones en interesado "+h.getInteresados().get(0).getNotificaciones().size());
  RepoHeladera.getInstance().guardar(h);


  Colaborador c=RepoColaboradores.getInstance().buscarPorId(11L);
  c.realizarNotificacionPor(n);
  System.out.println("notificaciones cantidad "+c.getNotificaciones().size()+" "+c.getNotificaciones().stream().toList().get(0).generarMensaje());
}

@Test
public void testTarjetaColaborador(){
  TarjetaColaborador t = new TarjetaColaborador("12345678912", LocalDate.now());
  Colaborador c = RepoColaboradores.getInstance().buscarPorId(1L);
  //Configurar la relación bidireccional
  t.setColaborador(c);
  c.setTarjeta(t);
  // Guardar el colaborador, que guardará también la tarjeta debido a cascade
  RepoColaboradores.getInstance().guardar(c);

  //Verificar la tarjeta asociada al colaborador
  Colaborador verificado = RepoColaboradores.getInstance().buscarPorId(1L);
  System.out.println("Tarjeta " + verificado.getTarjeta().getCodigo());

}
@Test
  public void testAceptarNotificacion(){
  Colaborador c=RepoColaboradores.getInstance().buscarPorId(11L);
  Heladera h=RepoHeladera.getInstance().buscarPorId(5L);
 // TipoDeColaboracion t=new DonarVianda(new Vianda(LocalDate.of(2026, 2, 2), LocalDate.now(), 1000, 4, false, c, h));
 // RepoColaboraciones.getInstance().guardar(t);
  //En la seguidilla de eventos el usuario crea la colaboracion  el servidor lo recibe y lo guarda/persiste luego usa el metodo del colaborador
  //para solicitar colaboracion la cual aun no esta en la lista del colaborador sino en espera
 // c.registrarSolicitudColaboracion(t,h);

  Heladera verificar=RepoHeladera.getInstance().buscarPorId(5L);
  System.out.println("Solicitudes en colaboracion Cantidad "+verificar.getSolicitudesColaboracion().size());



}

  @Test
  public void testAceptarNotificacionDistribucion(){

      Colaborador c=RepoColaboradores.getInstance().buscarPorId(1L);
      Heladera h=RepoHeladera.getInstance().buscarPorId(5L);
      Heladera h2=RepoHeladera.getInstance().buscarPorId(6L);

      TipoDeColaboracion t=new DistribuirVianda(h,h2,1,"Se murio",LocalDate.now());
      RepoColaboraciones.getInstance().guardar(t);
      //En la seguidilla de eventos el usuario crea la colaboracion  el servidor lo recibe y lo guarda/persiste luego usa el metodo del colaborador
      //para solicitar colaboracion la cual aun no esta en la lista del colaborador sino en espera
      //c.registrarSolicitudColaboracion(t,h);

    Heladera verificar=RepoHeladera.getInstance().buscarPorId(5L);
    System.out.println("Solicitudes en colaboracion Cantidad "+verificar.getSolicitudesColaboracion().size());



  }
@Test
  public void test(){
  Tarjeta t=new Tarjeta("12345678910",LocalDate.now());
  RepoTarjetas.getInstance().guardar(t);
  Tarjeta t1=new Tarjeta("12325678910",LocalDate.now());
  RepoTarjetas.getInstance().guardar(t1);
  Tarjeta t2=new Tarjeta("12343678910",LocalDate.now());
  RepoTarjetas.getInstance().guardar(t2);
  Tarjeta t3=new Tarjeta("12345478910",LocalDate.now());
  RepoTarjetas.getInstance().guardar(t3);
  Tarjeta t4=new Tarjeta("12345658910",LocalDate.now());
  RepoTarjetas.getInstance().guardar(t4);

}
  @Test
  public void testImportarColaboracionDesdeCSV() {
    // Configuración inicial
    String archivoCSV = "migracion.csv"; // Ruta del archivo de prueba
    ImportadorColaboracionCsv importador = new ImportadorColaboracionCsv();

    // Ejecutar la importación
    importador.importar(archivoCSV);

    // Verificar resultados
    List<TipoDeColaboracion> colaboraciones = importador.getColaboraciones();
    System.out.println(colaboraciones.size()+" Cantidad ");
    System.out.println(colaboraciones.get(0).getId());
    System.out.println(colaboraciones.get(1).getId());
    System.out.println(colaboraciones.get(2).getId());


  }

  @Test
  public void testVerTarjetaColaborador() {
    // Configuración inicial
    Heladera c=RepoHeladera.getInstance().buscarPorId(5L);
    System.out.println("tamaño "+c.getSolicitudesColaboracion().size());


  }
  @Test
  public void guardarDAtosBD(){


    Heladera heladera = new Heladera("Heladera Primario",100,"10","100",new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA, "Alberdi")
        ,LocalDate.now(),BigDecimal.valueOf(-10),BigDecimal.valueOf(4));

    Heladera heladera2 = new Heladera("Heladera SEcundario",100,"100","100",new Direccion(Provincia.BUENOS_AIRES, Localidad.LA_MATANZA, "Alberdi")
        ,LocalDate.now(),BigDecimal.valueOf(-10),BigDecimal.valueOf(4));

    RepoHeladera.getInstance().guardar(heladera);
    RepoHeladera.getInstance().guardar(heladera2);



    Tarjeta t=new Tarjeta("12345678910",LocalDate.now());
    RepoTarjetas.getInstance().guardar(t);
    Tarjeta t1=new Tarjeta("12325678910",LocalDate.now());
    RepoTarjetas.getInstance().guardar(t1);
    Tarjeta t2=new Tarjeta("12343678910",LocalDate.now());
    RepoTarjetas.getInstance().guardar(t2);
  }
  @Test
  public void donarViandaTest(){

    Colaborador c=RepoColaboradores.getInstance().buscarPorId(1L);
    Heladera h=RepoHeladera.getInstance().buscarPorId(1L);
    DonarVianda d=new DonarVianda(LocalDate.now(), new Vianda(LocalDate.of(2026, 2, 2), LocalDate.now(), 1000, 4,
        true, c, h));
    c.getColaboraciones().add(d);

  }
  @Test
  public void simulacroPasajeDETarjeta(){
    Heladera h=RepoHeladera.getInstance().buscarPorId(1L);
    SolicitudColaboracion s=h.getSolicitudesColaboracion().get(0);
    System.out.println("Solicitud: "+s.realizada+" cantidad:"+h.getSolicitudesColaboracion().size());

    h.confirmarSolicitudApertura("683950");

    System.out.println("Solicitud: "+s.realizada+" cantidad:"+h.getSolicitudesColaboracion().size());
  }
*/
}