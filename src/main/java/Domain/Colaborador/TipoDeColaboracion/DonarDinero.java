package Domain.Colaborador.TipoDeColaboracion;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.Transferencia.FrecuenciaDeDonacion;
import Domain.Colaborador.Transferencia.Transferencia;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Entity
public class DonarDinero extends TipoDeColaboracion {

  @Column
  private int montoDonado;
  @Column
  private LocalDate fechaDonacion;
  @Enumerated(EnumType.STRING)
  private FrecuenciaDeDonacion frecuencia;


  public DonarDinero(int montoDonado, LocalDate fechaDonacion, FrecuenciaDeDonacion frecuencia) {
    this.montoDonado = montoDonado;
    this.fechaDonacion = fechaDonacion;
    this.frecuencia = frecuencia;
  }

  public DonarDinero() {

  }

  @Override
  public void colaborar() {
  }
  @Override
  public double valorColaboracion() {
    return montoDonado*0.5;
  }

  public int getMontoDonado() {
    return montoDonado;
  }

  public LocalDate getFechaDonacion() {
    return fechaDonacion;
  }

  public FrecuenciaDeDonacion getFrecuencia() {
    return frecuencia;
  }
  @Override
  public LocalDate getFecha() {
    return this.fechaDonacion;
  }
}
