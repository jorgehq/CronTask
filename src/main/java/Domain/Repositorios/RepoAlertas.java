package Domain.Repositorios;

import Domain.Incidentes.Alerta;
import Domain.Incidentes.FallaTecnica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoAlertas {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoAlertas instance;
  public RepoAlertas() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoAlertas getInstance() {
    if (instance == null) {
      instance = new RepoAlertas();
    }
    return instance;
  }


  public Alerta buscarPorId(Long id) {

    Alerta falla = em.find(Alerta.class, id);

    return falla;
  }



  public List<Alerta> obtenerTodos() {

    TypedQuery<Alerta> query = em.createQuery("SELECT u FROM Alerta u", Alerta.class);

    List<Alerta> fallas = query.getResultList();
    return fallas;
  }

  public void guardar(Alerta falla) {
    em.getTransaction().begin();
    em.persist(falla);
    em.getTransaction().commit();

  }
}
