package Domain.Validador;

public class NoHayRepeticionDeCaracteres implements Validacion{

  public boolean validar(String nombre,String apellido, String nombreUsuario, String contrasenia) {
    String password = contrasenia;
    char[] stringACaracteres = password.toCharArray();

    for(int i = 0; i < password.length() - 2; i++) {
      if(password.charAt(i) == stringACaracteres[i+1] && stringACaracteres[i+1] == stringACaracteres[i+2]) {
        return false;
      }
    }

    return true;
  }

}
