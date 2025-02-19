package Domain.Notificaciones.Sugerencias;

import Domain.Heladera.Heladera;
import Domain.Repositorios.RepoHeladera;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
@DiscriminatorValue("AlAzar")
public class SugerenciaAlAzar extends Sugerencia {



    public SugerenciaAlAzar() {
        this.estaAceptado=false;


    }

    @Override
    public void sugerencias(Heladera h) {
        List<Heladera> todasHeladeras = new ArrayList<>(RepoHeladera.getInstance().obtenerTodos());

        todasHeladeras.removeIf(heladera -> heladera.getId().equals(h.getId()) );
        todasHeladeras.removeIf(heladera -> heladera.getEstado().getHeladeraAveriada());

        if (todasHeladeras.isEmpty()) {
            System.out.println("==================================No hay más heladeras========================================");
            this.h = null;
        } else {
            Random random = new Random();
            int indice = random.nextInt(todasHeladeras.size());
            while (todasHeladeras.get(indice).equals(h)) {
                indice = random.nextInt(todasHeladeras.size());
            }
            this.h = todasHeladeras.get(indice);
        }


    }

    @Override
    public void finalizarSugerencia() {
        if(estaAceptado==false){
            estaAceptado=true;
        }else{
            System.out.println("Sugerencia ya ha sido aceptada");
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
