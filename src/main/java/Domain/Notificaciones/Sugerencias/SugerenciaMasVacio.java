package Domain.Notificaciones.Sugerencias;

import Domain.Heladera.Heladera;
import Domain.Heladera.NivelDeLlenado;
import Domain.Repositorios.RepoHeladera;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.List;
@Entity
@DiscriminatorValue("MasVacio")
public class SugerenciaMasVacio extends Sugerencia {


    public SugerenciaMasVacio() {
    }

    public SugerenciaMasVacio(Heladera h) {
        this.h = h;
        this.estaAceptado=false;
    }

    @Override
    public void sugerencias(Heladera h) {
        List<Heladera> todasHeladeras= RepoHeladera.getInstance().obtenerTodos().stream().toList();
         this.h=todasHeladeras.stream().filter(he->he.getEstado().getNivelDeLlenado()== NivelDeLlenado.BAJO).toList().get(0);
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
