package Domain.Colaborador.TipoDeColaboracion;

import Domain.Heladera.Heladera;
import Domain.Heladera.Vianda;
import Domain.Repositorios.RepoHeladera;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FabricaColaboraciones {

    public static TipoDeColaboracion crearColaboracionVacia(String tipo) {
      switch (tipo.toLowerCase()) {
        case "donarvianda":
          return new DonarVianda(); // Inicializa con valores vacíos
        case "distribuirvianda":
          return new DistribuirVianda(); // Inicializa con valores vacíos
        default:
          throw new IllegalArgumentException("Tipo de colaboración desconocido: " + tipo);
      }
    }

    public static TipoDeColaboracion completarColaboracion(String tipo, Map<String, String> datos) {


      switch (tipo.toLowerCase()) {
        case "donarvianda":

          Vianda vianda = new Vianda(LocalDate.parse(datos.get("fechaCaducidad"))
                  ,LocalDate.now(),Integer.valueOf(datos.get("calorias"))
                  ,Integer.valueOf(datos.get("peso")),true,null,null);

          return new DonarVianda(LocalDate.now(),vianda);
        case "distribuirvianda":
          Heladera origen = RepoHeladera.getInstance().buscarPorId(Long.parseLong(datos.get("origen")));
          Heladera destino = RepoHeladera.getInstance().buscarPorId(Long.parseLong(datos.get("destino")));

          return new DistribuirVianda(origen, destino, Integer.parseInt(datos.get("cantidad")),
              datos.get("motivo"), LocalDate.now());
        default:
          throw new IllegalArgumentException("Tipo de colaboración desconocido: " + tipo);
      }
    }
  }

