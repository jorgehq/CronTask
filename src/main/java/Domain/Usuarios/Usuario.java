package Domain.Usuarios;

import Domain.Colaborador.Colaborador;
import Domain.Exception.ContraseniaInvalidaException;
import Domain.Heladera.Heladera;
import Domain.Validador.ValidadorDeContrasenias;
import jakarta.persistence.*;

@Entity
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  String nombre;
  @Column
  String apellido;
  @Column
  String nombreUsuario;
  @Column
  String contrasenia;

  @OneToOne
  Colaborador asignado;

  public Usuario(){}
  public Usuario(String nombre, String apellido, String nombreUsuario, String contrasenia) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
    /*
    if (new ValidadorDeContrasenias().validar(nombre, apellido, nombreUsuario, contrasenia)) {

    } else {
      throw new ContraseniaInvalidaException("Contrasenia invalida");
    }

     */
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getNombreUsuario() {
    return this.nombreUsuario;
  }
  public void asignarColaborador(Colaborador c){
    this.asignado=c;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public Colaborador getAsignado() {
    return asignado;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }
}
