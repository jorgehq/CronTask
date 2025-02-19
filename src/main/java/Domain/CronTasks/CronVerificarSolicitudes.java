package Domain.CronTasks;

import Domain.Repositorios.RepoSolicitudColaboracion;
import Domain.Solicitudes.SolicitudColaboracion;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CronVerificarSolicitudes {
     public CronVerificarSolicitudes() {
    }
    public void mostrar(){

      System.out.println("=======================================================");
      System.out.println("Cron Buscando Solicitudes caducadas ");
      System.out.println("=======================================================");
    }
    public void verificandoYmandarMensaje(){
      ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
      scheduler.schedule(() -> {

        scheduler.scheduleAtFixedRate(this::verificarSolicitudes, 0, 180, TimeUnit.SECONDS);
      }, 30, TimeUnit.SECONDS);
    }

    public void verificarSolicitudes(){
       this.mostrar();
      Set<SolicitudColaboracion> lista=RepoSolicitudColaboracion.getInstance().obtenerTodos();
      System.out.println("==============================Cantidad Solicitudes en Lista: "+lista.size()+" ===========================================");
      for(SolicitudColaboracion s:lista){

        if(s.fechaExpiracion.isBefore(LocalDate.now()) && !s.isExpired()){
          s.setExpired(true);
          RepoSolicitudColaboracion.getInstance().guardar(s);
            System.out.println("=======================================================");
            System.out.println("Se encontro una solicitud expirada");
            System.out.println("=======================================================");

        }
      }
     }
  }

