package Domain.Solicitudes;

import Domain.Colaborador.TipoDeColaboracion.DistribuirVianda;
import Domain.Colaborador.TipoDeColaboracion.DonarVianda;
import Domain.Colaborador.TipoDeColaboracion.FabricaColaboraciones;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import Domain.Heladera.Heladera;
import Domain.Repositorios.RepoColaboraciones;
import Domain.Repositorios.RepoColaboradores;
import Domain.Repositorios.RepoSolicitudColaboracion;
import Domain.Tarjeta.TarjetaColaborador;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class SolicitudColaboracion {
  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  @JoinColumn(name = "colaboracion_id")
  public TipoDeColaboracion colaboracion;
  public LocalDate fechaSolicitud;
  public LocalDate fechaExpiracion;
  public boolean realizada=false;
  public boolean expired = false;
  public boolean entregada = false;

  public String descripcion;
  public int cantidad;

  @ElementCollection
  private Map<String, String> datosTemporales = new HashMap<>();

  @ManyToOne
  @JoinColumn(name = "heladera_id")
  public Heladera heladera;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "tarjeta_id")
  public TarjetaColaborador tarjeta;


  public SolicitudColaboracion(TipoDeColaboracion colaboracion, Heladera heladera, TarjetaColaborador tarjeta,Map<String, String>datos,int cantidad) {
    this.tarjeta = tarjeta;
    this.colaboracion = colaboracion;
    this.fechaSolicitud = LocalDate.now();
    this.heladera = heladera;
    //CONTROLA CUANDO SE EXPIRA
    this.fechaExpiracion=LocalDate.now().plusDays(1);
    this.datosTemporales=datos;
    this.cantidad=cantidad;
  }

  public SolicitudColaboracion() {

  }


  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public void setRealizada(boolean realizada) {
    this.realizada = realizada;
  }

  public Long getId() {
    return id;
  }

  public Heladera getHeladera() {
    return heladera;
  }

  public TarjetaColaborador getTarjeta() {
    return tarjeta;
  }

  public boolean isEntregada() {
    return entregada;
  }

  public TipoDeColaboracion getColaboracion() {
    return colaboracion;
  }

  public void setFechaExpiracion(LocalDate fechaExpiracion) {
    this.fechaExpiracion = fechaExpiracion;
  }

  public Map<String, String> getDatosTemporales() {
    return datosTemporales;
  }

  public void setEntregada(boolean entregada) {
    this.entregada = entregada;
  }

  public LocalDate getFechaSolicitud() {
    return fechaSolicitud;
  }

  public LocalDate getFechaExpiracion() {
    return fechaExpiracion;
  }

  public boolean getRealizada() {
    return realizada;
  }

  public boolean getExpired() {
    return expired;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public int getCantidad() {
    return cantidad;
  }

  public boolean isExpired() {
    // Compara la fecha de expiración con la fecha actual
    return expired;
  }
  public void instanciarColaboracion(){
    System.out.println("================================Comensando Fabrica");
    TipoDeColaboracion nueva;
    System.out.println("ID solicitud: "+id);
    System.out.println("DAtos donar vianda "+datosTemporales.get("fechaCaducidad")+ "  "+datosTemporales.get("calorias")+"  "+datosTemporales.get("peso"));
    System.out.println("DAtos donar vianda "+datosTemporales.get("origen")+ "  "+datosTemporales.get("destino"));

    if(colaboracion instanceof DonarVianda){
      nueva=FabricaColaboraciones.completarColaboracion("donarvianda",datosTemporales);
      nueva.setId(colaboracion.getId());
      descripcion="Donacion de vianda";
      ((DonarVianda) nueva).getVianda().setHeladera(heladera);


    }else{
      nueva=FabricaColaboraciones.completarColaboracion("distribuirvianda",datosTemporales);
      nueva.setId(colaboracion.getId());

    }
    colaboracion=nueva;
    realizada=true;

    System.out.println("================================Fabrica creada");

    RepoColaboraciones.getInstance().merge(colaboracion);

    tarjeta.getColaborador().realizarColaboracion(colaboracion);

    RepoColaboradores.getInstance().merge( tarjeta.getColaborador());

    RepoSolicitudColaboracion.getInstance().merge(this);
  }
  public void removerExpirada(){
    if(fechaExpiracion.isBefore(LocalDate.now())){
      RepoColaboraciones.getInstance().remover(colaboracion);
    }
  }
}
