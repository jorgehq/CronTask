package Domain.Solicitudes.Controlador;

import Domain.Repositorios.RepoSolicitudColaboracion;
import Domain.Repositorios.RepoTarjetaColaborador;
import Domain.Solicitudes.SolicitudColaboracion;
import Domain.Tarjeta.TarjetaColaborador;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

public class ControladorDeAccesoHeladeras implements ControladorDeAcceso{

  private int tiempoLimiteHoras = 3; // Valor por defecto


  public void notificarTarjetaColaboradoraHabilitada(String numeroDeTarjeta) {


    Set<SolicitudColaboracion> misSolicitudes=RepoSolicitudColaboracion.getInstance().obtenerTodos();
    if(misSolicitudes==null){
    }else{
      misSolicitudes.stream().
          filter(t->t.getTarjeta().getCodigo().equals(numeroDeTarjeta) && !t.isEntregada()).toList();
      misSolicitudes.forEach(s->s.fechaExpiracion.plus(tiempoLimiteHoras, ChronoUnit.DAYS));
      misSolicitudes.forEach(s->s.entregada=true);

    }



  }

  public void setTiempoLimite(int tiempoLimiteHoras) {
    this.tiempoLimiteHoras = tiempoLimiteHoras;
  }


}


