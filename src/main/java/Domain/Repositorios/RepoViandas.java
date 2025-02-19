package Domain.Repositorios;

import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import Domain.Heladera.Vianda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoViandas {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoViandas instance;


  public RepoViandas() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoViandas getInstance() {
    if (instance == null) {
      instance = new RepoViandas();
    }
    return instance;
  }


  public Vianda buscarPorId(Long id) {

    Vianda vianda = em.find(Vianda.class, id);

    return vianda;
  }


  public List<Vianda> obtenerTodos() {

    TypedQuery<Vianda> query = em.createQuery("SELECT u FROM Vianda u", Vianda.class);
    List<Vianda> vianda = query.getResultList();
    return vianda;
  }

  public void guardar(Vianda c) {
    em.getTransaction().begin();
    em.persist(c);
    em.getTransaction().commit();
  }

}
