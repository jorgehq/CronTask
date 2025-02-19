package Domain.Repositorios;

import Domain.Heladera.Heladera;
import Domain.Persona.Tecnico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class RepoTecnicos {

  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoTecnicos instance;
  public RepoTecnicos() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoTecnicos getInstance() {
    if (instance == null) {
      instance = new RepoTecnicos();
    }
    return instance;
  }


  public Tecnico buscarPorId(Long id) {

    Tecnico tecnico = em.find(Tecnico.class, id);
    return tecnico;
  }



  public List<Tecnico> obtenerTodos() {

    TypedQuery<Tecnico> query = em.createQuery("SELECT u FROM Tecnico u", Tecnico.class);
    List<Tecnico> tecnicos = query.getResultList();

    return tecnicos;
  }

  public void guardar(Tecnico tecnico) {
    em.getTransaction().begin();
    em.persist(tecnico);
    em.getTransaction().commit();

  }

  public void llamarTecnico(Heladera h){

    List<Tecnico> lista=this.obtenerTodos();
    Tecnico tecnicoCercano = lista.get(0);
    for(int x = 1; x<lista.size(); x++){
      Tecnico comparar = lista.get(x);
      tecnicoCercano = tecnicoMasCercano(tecnicoCercano, comparar, h);
    }

    //MandadorDeMail.getInstance().sendMain(tecnicoCercano, heladera);


  }

  private Tecnico tecnicoMasCercano(Tecnico t1, Tecnico t2, Heladera heladera){
    if(distanciaDelTecnicoCon(t1, heladera.getLatitud(), heladera.getLongitud()) < distanciaDelTecnicoCon(t2, heladera.getLatitud(), heladera.getLongitud())){
      return t1;
    }
    return t2;
  }

  private double distanciaDelTecnicoCon(Tecnico tecnico, String latitud, String longitud){
    double distancia;
    float dLatitud = Float.parseFloat(tecnico.getLatitud()) - Float.parseFloat(latitud);
    float dLongitud = Float.parseFloat(tecnico.getLongitud()) - Float.parseFloat(longitud);
    distancia = Math.pow((dLatitud * dLatitud + dLongitud * dLongitud), 0.5);
    return distancia;
  }
}
