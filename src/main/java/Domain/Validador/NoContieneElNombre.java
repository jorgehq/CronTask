package Domain.Validador;

public class NoContieneElNombre implements Validacion{

  public boolean validar(String nombre,String apellido, String nombreUsuario, String contrasenia) {
    return (!contrasenia.toUpperCase().contains(nombre.toUpperCase())
        && !contrasenia.toUpperCase().contains(apellido.toUpperCase())
        && !contrasenia.toUpperCase().contains(nombreUsuario.toUpperCase()));
  }

}
