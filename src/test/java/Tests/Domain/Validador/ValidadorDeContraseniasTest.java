package Tests.Domain.Validador;

import Domain.Usuarios.Usuario;
import Domain.Exception.ContraseniaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidadorDeContraseniasTest {
/*
  @Test
  public void contraseniaValida(){

    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "Roca&1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertFalse(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public void esInsegura(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "password");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void contieneElNombre(){
    Assertions.assertThrows(ContraseniaInvalidaException.class, () -> new Usuario("Carlos", "Roberto", "Pepe", "carlos&1234"));
  }

  @Test
  public  void contieneElApellido(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "ROBERto&1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void contieneElNombreDeUsuario(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "Pepe&1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void noCumpleLongitudMinima(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "sdF3&s");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void contieneRepeticionDeCaracteres(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "caRloooos1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void noContieneCaracterEspecial(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "esteBan1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void noContieneNumero(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "esteban%maRcos");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void noContieneMayuscula(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "esteban&1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

  @Test
  public  void noContieneMinuscula(){
    Exception myException = null;
    try {
      Usuario usu = new Usuario("Carlos", "Roberto", "Pepe", "ESTEBAN&1234");
    } catch (Exception e) {
      myException = e;
    }
    Assertions.assertTrue(myException instanceof ContraseniaInvalidaException);
  }

 */
}
