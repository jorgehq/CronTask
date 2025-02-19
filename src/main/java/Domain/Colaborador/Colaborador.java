package Domain.Colaborador;

import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import Domain.Colaborador.TipoDocumento.TipoDocumento;
import Domain.Exception.SolicitudTarjetaInvalida;
import Domain.Heladera.Heladera;
import Domain.Incidentes.FallaTecnica;

import Domain.Notificaciones.Notificacion;

import Domain.Repositorios.RepoColaboradores;
import Domain.Repositorios.RepoFallaTecnica;
import Domain.Repositorios.RepoSolicitudesTarjetas;
import Domain.Solicitudes.SolicitudTarjeta;
import Domain.Tarjeta.TarjetaColaborador;
import Domain.Ubicacion.Direccion;
import Domain.Usuarios.Usuario;
import jakarta.persistence.*;
import org.glassfish.grizzly.utils.ArraySet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Colaborador {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany
  @JoinColumn(name = "colaborador_id")
  private List<TipoDeColaboracion> colaboraciones = new ArrayList<>();
  @Embedded
  private Direccion direccion;

  private Boolean esAdmin=false;
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "colaborador_id")
  private List<Mediodecontacto> mediosDeContacto;

  @ManyToMany
  @JoinTable(
      name = "Notificacion_colaborador",
      joinColumns = @JoinColumn(name = "colaborador_id"),
      inverseJoinColumns = @JoinColumn(name = "notificacion_id"))
  private Set<Notificacion> notificaciones=new HashSet<>();
  @OneToOne(mappedBy = "colaborador",cascade = CascadeType.ALL)
  private TarjetaColaborador tarjeta;
  public Colaborador(){}
  public Colaborador(Direccion direccion, List<Mediodecontacto> mediosDeContactos) {
    this.direccion = direccion;
    this.mediosDeContacto = mediosDeContactos;
  }

    public void realizarColaboracion(TipoDeColaboracion colaboracion) {
        colaboracion.colaborar();
        this.colaboraciones.add(colaboracion);
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void addColaboracion(TipoDeColaboracion colaboracion) {
        this.colaboraciones.add(colaboracion);
    }

    public TarjetaColaborador getTarjeta() {
        return tarjeta;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public abstract Heladera ultimaHeladera();

    public abstract long getDocumento();

    public abstract TipoDocumento getTipoDocumento();

    public List<Mediodecontacto> getMediosDeContacto() {
        return mediosDeContacto;
    }

    public Set<Notificacion> getNotificaciones() {
    return notificaciones;
  }

  public void setEsAdmin(Boolean esAdmin) {
    this.esAdmin = esAdmin;
  }

  public Long getId() {
        return id;
    }

    public double calcularPuntajeColaboraciones(LocalDate fechaInicio, LocalDate fechaFin) {
        double sumatoria = 0.0;
      List<TipoDeColaboracion> colaboracionesDeFecha = this.colaboraciones.stream()
          .filter(c -> c.getFecha() != null)
          .filter(c -> !c.getFecha().isBefore(fechaInicio) && !c.getFecha().isAfter(fechaFin))
          .collect(Collectors.toList());

        for (TipoDeColaboracion realizadas : colaboracionesDeFecha) {
            sumatoria += realizadas.valorColaboracion();
        }
        return sumatoria;
    }

    public void reportarFallaTecnica(Heladera heladera, String descripcion, String pathImagen) {
        // Hay q persistir la falla
        FallaTecnica fallaTecnica = new FallaTecnica(LocalDateTime.now(), heladera, descripcion, pathImagen, this);
        RepoFallaTecnica.getInstance().guardar(fallaTecnica);
    }

    public List<TipoDeColaboracion> getColaboraciones() {
        return colaboraciones;
    }


     public void realizarNotificacionPor( Notificacion n){

         System.out.println("Enviando a  "+ mediosDeContacto.size()+" medios de contactos");

         notificaciones.add(n);

         RepoColaboradores.getInstance().merge(this);

         System.out.println("Cantidad notificaciones en el buzon son de  "+ notificaciones.size()+" notificaciones del usuario "+this.getId());
         mediosDeContacto.forEach(a->a.enviarMensaje(n.generarMensaje()));
     }

  public void registrarSolicitudColaboracion (TipoDeColaboracion colaboracion, Heladera heladera, Map<String, String> datos,int cantidad) {
    this.tarjeta.agregarSolicitud(colaboracion, heladera,datos,cantidad);
  }

  public void solicitarTarjeta() {
    if (this.tarjeta != null) {
      throw new SolicitudTarjetaInvalida("Ya tiene una tarjeta");
    }
    SolicitudTarjeta solicitud = new SolicitudTarjeta(this, this.direccion);
    RepoSolicitudesTarjetas.getInstance().addSolicitud(solicitud);
  }

  public void setTarjeta(TarjetaColaborador tarjeta) {
    this.tarjeta = tarjeta;
    tarjeta.setColaborador(this);
  }
};