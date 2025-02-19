package Domain.Validador;

import Domain.Validador.Config.ValidadorConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoEsInsegura implements Validacion{

  private static ArrayList<String> contraseniasInseguras = new ArrayList<>() {
  };
  private static boolean cargadasContraseniasInseguras = false;

  public NoEsInsegura() {
    this.cargarContraseniasInseguras();
  }

  public boolean validar(String nombre,String apellido, String nombreUsuario, String contrasenia) {
    return !(contraseniasInseguras.contains(contrasenia));
  }
  public void cargarContraseniasInseguras() {
    File file = new File(ValidadorConfig.pathContraseniasMasInseguras);
    try{
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        contraseniasInseguras.add(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    cargadasContraseniasInseguras = true;
  }

}
