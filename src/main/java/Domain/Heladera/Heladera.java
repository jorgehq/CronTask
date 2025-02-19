package Domain.Heladera;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.TipoDeColaboracion.DistribuirVianda;
import Domain.Colaborador.TipoDeColaboracion.DonarVianda;
import Domain.Heladera.Sensores.AdapterSensorPeso;
import Domain.Heladera.Sensores.AdapterSensorTemperatura;
import Domain.Notificaciones.Notificacion;
import Domain.Notificaciones.NotificacionIncidente;
import Domain.Repositorios.*;
import Domain.Solicitudes.SolicitudColaboracion;
import Domain.Ubicacion.Direccion;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Heladera {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column
  String nombre;
  @Column
  Integer capacidadDeViandas;
  @Column
  String Latitud;
  @Column
  String Longitud;
  @Embedded
  Direccion direccion;
  @Column
  LocalDate fechaPuestaEnMarcha;
  @OneToMany(mappedBy = "heladera")
  List<Vianda> viandasEnHeladera;
  @OneToOne(cascade = CascadeType.PERSIST)
  EstadoHeladera estadoHeladera;
  @OneToMany(mappedBy = "heladera")
  public List<SolicitudColaboracion> solicitudesColaboracion = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "Heladera_colaborador",
      joinColumns = @JoinColumn(name = "heladera_id"),
      inverseJoinColumns = @JoinColumn(name = "colaborador_id"))
  List<Colaborador> interesados=new ArrayList<>();


  public Heladera() {
  }

  public Heladera(String nombre, Integer capacidadDeViandas, String latitud, String longitud, Direccion direccion, LocalDate fechaPuestaEnMarcha, BigDecimal temperaturaMaxima, BigDecimal temperaturaMinima) {
    this.nombre = nombre;
    this.capacidadDeViandas = capacidadDeViandas;
    Latitud = latitud;
    Longitud = longitud;
    this.direccion = direccion;
    this.fechaPuestaEnMarcha = fechaPuestaEnMarcha;
    this.viandasEnHeladera = new ArrayList<Vianda>();
    this.estadoHeladera = new EstadoHeladera( temperaturaMaxima,  temperaturaMinima, this);
  }




  public void agregarVianda(Vianda vianda) {
    if (this.capacidadDeViandas.equals(this.viandasEnHeladera.size())) {
      throw new RuntimeException("no se puede agregar, capacidad llena");
    }
    vianda.setHeladera(this);
    this.viandasEnHeladera.add(vianda);
   // this.estadoHeladera.actualizarNivelDeLlenado(this.capacidadDeViandas);
  }

  public Vianda quitarVianda() {

    if (this.viandasEnHeladera.isEmpty()) {
      throw new RuntimeException("No hay mas viandas");
    }

    Vianda viandaQuitada = this.viandasEnHeladera.remove(0);
    //this.estadoHeladera.actualizarNivelDeLlenado(this.capacidadDeViandas);
    return viandaQuitada;
  }

  public String getNombre() {
    return nombre;
  }

  public EstadoHeladera getEstado() {
    return this.estadoHeladera;
  }

  public EstadoHeladera getEstadoHeladera() {
    return estadoHeladera;
  }

  public LocalDate getFechaPuestaEnMarcha() {
    return fechaPuestaEnMarcha;
  }

  public Integer getCapacidadDeViandas() {
    return capacidadDeViandas;
  }

  public String getLongitud() {
    return Longitud;
  }

  public Long getId() {
    return id;
  }


  public String getLatitud() {
    return Latitud;
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public void consultarUltimaLectura(LocalDateTime now) {
    this.estadoHeladera.consultarUltimaLectura(now);
  }

  public void marcarComoInactiva() {
    estadoHeladera.marcarComoInactiva();
  }

  public List<Vianda> getViandasEnHeladera() {
    return viandasEnHeladera;
  }

  public void suscribirse(Colaborador c){
    this.interesados.add(c);
  }

  public List<Colaborador> getInteresados() {
    return interesados;
  }

  public List<SolicitudColaboracion> getSolicitudesColaboracion() {
    return solicitudesColaboracion;
  }

  public int cantidadViandas(){
    return viandasEnHeladera.size();
  }

  public void notificarInteresados(Notificacion n) {
    if (n instanceof NotificacionIncidente) {
      marcarComoInactiva();
    }



    List<Colaborador> copiaInteresados = new ArrayList<>(interesados);

    System.out.println("====================Interesados: "+copiaInteresados.size());
    for (Colaborador c : copiaInteresados) {
      boolean notificacionYaEntregada = false;
      for (Notificacion noti : c.getNotificaciones()) {

        if (noti.getHeladera().getId().equals(n.getHeladera().getId()) && n.getTipoNotificacion().equals(noti.getTipoNotificacion())) {
          System.out.println("========================= Ya entregado =================================");
          notificacionYaEntregada = true;

        }
      }
      if (notificacionYaEntregada) {
        System.out.println("================= Ya esta entregado");

      }else{
        System.out.println("================= Se encontro un usuario sin la notificacion y esta suscripto, enviando");

        RepoNotificaciones.getInstance().guardar(n);
        c.realizarNotificacionPor(n);
      }


    }
  }
  public int cantidadSolicitudesVianda(){
    int cantidad=0;
    for(SolicitudColaboracion s:RepoHeladera.getInstance().obtenerSolicitudesPorHeladeraId(id)){

      if(s.getColaboracion() instanceof DonarVianda && !s.getRealizada() && !s.isExpired()){
        cantidad++;
      }
    }
    return cantidad;
  }
  public int cantidadSolicitudesDistribucion(){
    int cantidad=0;
    for(SolicitudColaboracion s:RepoHeladera.getInstance().obtenerSolicitudesPorHeladeraId(id)){
      if(s.getColaboracion() instanceof DistribuirVianda && !s.realizada && !s.isExpired() ){
        cantidad+=s.getCantidad();
      }
    }
    return cantidad;
  }
  public void confirmarSolicitudApertura(String tarjeta){
    List<SolicitudColaboracion> aConfirmar=solicitudesColaboracion.stream().filter(s->s.tarjeta.getCodigo().equals(tarjeta) && s.realizada==false).toList();
    if(aConfirmar.size()==0){
      System.out.println("Error de acceso, no existe la solicitud");
    }else{
      for(SolicitudColaboracion s:aConfirmar){
        if (!s.getExpired()){
          s.realizada=true;
          s.instanciarColaboracion();
        }else{
          SolicitudColaboracion solicitud=RepoSolicitudColaboracion.getInstance().buscarPorId(s.getId());
          System.out.println("==================================Removiendo=========================================");
          //solicitudesColaboracion.remove(solicitud);
          //RepoSolicitudColaboracion.getInstance().remove(s);
        }
      }
    }
  }
}
