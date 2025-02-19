package Domain.Colaborador.Transferencia;

import java.time.LocalDate;

public class Transferencia {
    private LocalDate fechaDonacion;
    private Integer monto;
    private FrecuenciaDeDonacion frecuencia;

    public Transferencia(LocalDate fechaDonacion, Integer monto, FrecuenciaDeDonacion frecuencia) {
        this.fechaDonacion = fechaDonacion;
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

    public Integer getMonto() {
        return monto;
    }
}