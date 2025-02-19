package Domain.Notificaciones;

import Domain.Heladera.Heladera;
import Domain.Notificaciones.Sugerencias.Sugerencia;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Incidente")
public class NotificacionIncidente extends Notificacion {
    @ManyToOne(cascade = CascadeType.PERSIST)
    Sugerencia sugerencia;


    public NotificacionIncidente() {

    }

    public NotificacionIncidente( Heladera heladera, Sugerencia criterio,String descripcion) {
        this.descripcion=descripcion;
        this.heladera = heladera;
        this.sugerencia = criterio;
        this.cantidadViandas=heladera.getViandasEnHeladera().size();
        this.fecha= LocalDate.now();
        //RepoSugerencias.
    }



    @Override
    public String generarMensaje() {
        return "La heladera "+heladera.getNombre()+" esta aberiada y se recomienda trasladar las viandas a las siguientes heladeras "+ sugerencia.mostrarHeladera();
    }

    @Override
    public void aceptarSugerencia(int cantidad) {
        if(cantidad>=cantidadViandas){
            sugerencia.finalizarSugerencia();
            cantidadViandas=0;
            System.out.println("Todas las viandas son distribuidas");
        }else{
            cantidadViandas-=cantidad;
            System.out.println( "Viandas aun por distribuir "+cantidadViandas);
        }

    }

    public String getDescripcion() {
        return descripcion;
    }

    public Sugerencia getSugerencia() {
        return sugerencia;
    }
}
