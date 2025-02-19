package Domain.Validador;

import Domain.Validador.Config.ValidadorConfig;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ValidadorDeContrasenias {

  private Map<String, Validacion> validaciones = new HashMap<>();
  public ValidadorDeContrasenias() {
    File file = new  File(ValidadorConfig.pathValidaciones);
    try{
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String nombreValidacion = scanner.nextLine().trim();
        Class<?> clazz = Class.forName(nombreValidacion);
        validaciones.put(nombreValidacion, (Validacion) clazz.getDeclaredConstructor().newInstance());
      }
      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Boolean validar(String nombre,String apellido, String nombreUsuario, String contrasenia) {
    return !this.validaciones.values().stream().anyMatch( condicion -> condicion.validar(nombre, apellido, nombreUsuario, contrasenia) == false);
  }
}
