package Domain.Validador;

import Domain.Validador.Config.ValidadorConfig;
public class CumpleLongitudMinima implements Validacion{

  public boolean validar(String nombre,String apellido, String nombreUsuario, String contrasenia) {
    return contrasenia.length() >= ValidadorConfig.minimoCaracter;
  }
}
