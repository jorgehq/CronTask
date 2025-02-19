package Domain.Server;

import Domain.Heladera.Heladera;
import Domain.Notificaciones.Notificacion;
import Domain.Notificaciones.NotificacionFaltanViandas;
import Domain.Notificaciones.NotificacionIncidente;
import Domain.Repositorios.RepoHeladera;
import Domain.Repositorios.RepoNotificaciones;
import Domain.Repositorios.RepoSolicitudColaboracion;
import Domain.Server.Controlador.*;
import Domain.Solicitudes.SolicitudColaboracion;
import io.javalin.Javalin;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class App {
  public static void main(String[] args) {
    
    System.out.println("=======================================================");
    System.out.println("Cron Buscando Problemas en heladeras caducadas ");
    System.out.println("=======================================================");
    // Obtener todas las heladeras en buen estado
    Set<Heladera> lista = RepoHeladera.getInstance()
            .obtenerTodos()
            .stream()
            .collect(Collectors.toSet());

    System.out.println("=======================================================");
    System.out.println("Hay " + lista.size() + " heladeras en buen estado en la lista total");
    System.out.println("=======================================================");

    // Obtener todas las notificaciones existentes
    List<Notificacion> notificaciones = RepoNotificaciones.getInstance().obtenerTodos();

    for (Heladera heladera : lista) {

      if (heladera.getEstadoHeladera().getHeladeraAveriada()) {
        System.out.println("=======================================================");
        System.out.println("SE detectó incidente en la heladera " + heladera.getNombre() + ". Verificando notificación.");
        System.out.println("=======================================================");

        // Buscar la notificación de incidente para esta heladera
        NotificacionIncidente notificacionExistente = (NotificacionIncidente) notificaciones.stream()
                .filter(n -> n.getHeladera().getId() == heladera.getId())
                .filter(n -> n.getTipoNotificacion() == "Incidente").collect(Collectors.toList()).get(0);


        System.out.println("============================Viandas en esta heladera "
                + notificacionExistente.getHeladera().getNombre()
                + " cantidad " + notificacionExistente.getHeladera().getViandasEnHeladera().size()
                + " Esta avferiada " + notificacionExistente.getSugerencia().getHeladera().getEstadoHeladera().getHeladeraAveriada());
        if (notificacionExistente != null && notificacionExistente.getHeladera().getViandasEnHeladera().size() != 0
                && !notificacionExistente.getSugerencia().getHeladera().getEstadoHeladera().getHeladeraAveriada()) {
          // Notificar a los interesados si la notificación existe
          heladera.notificarInteresados(notificacionExistente);
        }
      } else if (heladera.getViandasEnHeladera().size() < 20) {
        System.out.println("=======================================================");
        System.out.println("SE detectó falta de viandas en la heladera " + heladera.getNombre() + ". Mandando notificaciones faltantes.");
        System.out.println("=======================================================");


        List<Notificacion> notificacionesDeEstaHeladera = notificaciones.stream()
                .filter(notificacion -> notificacion.getHeladera().getId().equals(heladera.getId()))
                .collect(Collectors.toList());
// Verificar si ya existe una notificación de falta de viandas para esta heladera

        boolean notificacionExistente = false;
        for (Notificacion notificacion : notificacionesDeEstaHeladera) {

          if (notificacion instanceof NotificacionFaltanViandas) {
            notificacionExistente = true;

            break;
          }
        }

        if (!notificacionExistente) {
          NotificacionFaltanViandas notificacion = new NotificacionFaltanViandas(
                  heladera.getCapacidadDeViandas() - heladera.getViandasEnHeladera().size(),
                  heladera
          );
          RepoNotificaciones.getInstance().guardar(notificacion);
          heladera.notificarInteresados(notificacion);
        }
        heladera.notificarInteresados(notificacionesDeEstaHeladera.get(0));
      }

    }
  }
}