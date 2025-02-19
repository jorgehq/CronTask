package Domain.Notificaciones;

import Domain.Heladera.Heladera;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("FaltanViandas")
public class NotificacionFaltanViandas extends Notificacion {

    public NotificacionFaltanViandas() {

    }
    public NotificacionFaltanViandas(int cantidadViandas,Heladera nombreHeladera) {
        this.heladera=nombreHeladera;
        this.cantidadViandas=cantidadViandas;
        this.fecha= LocalDate.now();
    }





    @Override
    public String generarMensaje() {
        return "Quedan "+cantidadViandas+" viandas en la heladera "+ heladera.getNombre();
    }



    @Override
    public void aceptarSugerencia(int cantidad) {
    if(cantidadViandas>cantidad){
        System.out.println("se donaron "+cantidad+" de viandas");
        cantidadViandas-=cantidad;
    }
    }
}
