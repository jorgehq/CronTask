package Domain.Repositorios;

import Domain.Solicitudes.SolicitudTarjeta;
import java.util.ArrayList;
import java.util.List;

public class RepoSolicitudesTarjetas {

  private List<SolicitudTarjeta> solicitudes = new ArrayList<>();
  private static RepoSolicitudesTarjetas instance;


  private RepoSolicitudesTarjetas() {}


  public static RepoSolicitudesTarjetas getInstance() {
    if (instance == null) {
      instance = new RepoSolicitudesTarjetas();
    }
    return instance;
  }


  public void addSolicitud(SolicitudTarjeta solicitud) {
    this.solicitudes.add(solicitud);
  }

  public List<SolicitudTarjeta> getSolicitudes() {
    return this.solicitudes;
  }


  public static void resetInstance() {
    instance = null;
  }
}
