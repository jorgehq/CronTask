package Domain.CronTasks;

import Domain.Heladera.Heladera;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CronFallaDeConexionST {
  private static List<Heladera> heladeras;
  private static ScheduledExecutorService scheduler;

  public CronFallaDeConexionST(List<Heladera> heladeras) {
    this.heladeras = heladeras;
    this.scheduler = Executors.newScheduledThreadPool(1);  // Crea un scheduler con un solo hilo
  }

  public void detener() {
    scheduler.shutdown();
  }

  public static void main(String[] args) {
      scheduler.scheduleAtFixedRate(() -> {
        for (Heladera heladera : heladeras) {
          heladera.consultarUltimaLectura(LocalDateTime.now());
        }
      }, 0, 15, TimeUnit.MINUTES);
    }
  }
