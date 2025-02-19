package Domain.Tarjeta;

import Domain.Exception.CodigoInvalidoException;
import Domain.Exception.SinUsosDisponiblesException;
import Domain.Heladera.Heladera;
import Domain.Persona.PersonaVulnerable;
import Domain.Repositorios.RepoTarjetas;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Tarjeta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany
  @JoinColumn(name = "tarjeta_id")
  private List<MovimientoTarjeta> movimientos = new ArrayList<>();
  @Column
  private String codigo; // Podriamos hacer la validacion en el formulario
  @Column
  private LocalDate fechaRegistro;

 private Boolean enUso=false;

  public Tarjeta() {
      this.codigo = generarCodigoAleatorio();
      this.fechaRegistro = LocalDate.now();
  }

  public Tarjeta(String codigo) {
    if (codigo.length() == 11) {
      this.codigo = codigo;
    }
    else {
      throw new CodigoInvalidoException("El codigo debe tener 11 caracteres");
    }
    this.fechaRegistro = null;

  }

  public void setEsnUso(Boolean esnUso) {
    this.enUso = esnUso;
  }

  public void utilizarTarjeta(Integer cantidadDeMenoresACargo, Heladera heladera) {
    System.out.println("Movimientos "+todayUses());
    if (todayUses() < 4 + cantidadDeMenoresACargo * 2) {

      heladera.quitarVianda();

      this.registrarMovimiento(new MovimientoTarjeta(heladera));
    }

  }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setEnUso(Boolean enUso) {
        this.enUso = enUso;
    }

    private Integer todayUses() {
    return this.movimientos.stream()
        .filter(movimiento -> movimiento.fecha.equals(LocalDate.now()))
        .toList().size();
  }

  private void registrarMovimiento(MovimientoTarjeta movimientoTarjeta) {
    this.movimientos.add(movimientoTarjeta);
    RepoTarjetas.getInstance().guardar(this);
  }

  public String getCodigo() {
    return this.codigo;
  }

  public LocalDate getFechaRegistro() {
    return this.fechaRegistro;
  }


  public int getIntentos() {

    return this.movimientos.size();
  }

  public Long getId() {
    return id;
  }

    private String generarCodigoAleatorio() {
        Random random = new Random();
        StringBuilder codigoGenerado = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            codigoGenerado.append(random.nextInt(10));  // Genera un número entre 0 y 9
        }
        return codigoGenerado.toString();
    }

    public Boolean getEnUso() {
        return enUso;
    }
}
