package Domain.Notificaciones.Sugerencias;

import Domain.Heladera.Heladera;
import Domain.Repositorios.RepoHeladera;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("UnKilometro")
public class SugerenciaUnKilometro extends Sugerencia {


    public SugerenciaUnKilometro() {
        this.estaAceptado=false;
    }

    @Override
    public void sugerencias(Heladera h) {
        List<Heladera> todasHeladeras= RepoHeladera.getInstance().obtenerTodos().stream().toList();
        this.h= todasHeladeras.stream().filter(hel->distancia1(h,hel) && hel.equals(h)==false).toList().get(0);
    }
    public  boolean distancia1(Heladera incidente,Heladera destino){
        //Comparacion de distancias si est a en el rango de 1K
        return true;
    }
    @Override
    public void finalizarSugerencia() {
        if(estaAceptado==false){
            estaAceptado=true;
        }else{
            System.out.println("Sugerencia ya ha sido finalizada");
        }
    }
    @Override
    public String mostrarHeladera() {
        return h.getNombre();
    }

    @Override
    public Heladera getHeladera() {
        return h;
    }
}
