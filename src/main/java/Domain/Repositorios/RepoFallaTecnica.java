package Domain.Repositorios;

import Domain.Heladera.Heladera;
import Domain.Incidentes.FallaTecnica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepoFallaTecnica {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoFallaTecnica instance;
  public RepoFallaTecnica() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoFallaTecnica getInstance() {
    if (instance == null) {
      instance = new RepoFallaTecnica();
    }
    return instance;
  }


  public FallaTecnica buscarPorId(Long id) {

    FallaTecnica falla = em.find(FallaTecnica.class, id);

    return falla;
  }



  public Set<FallaTecnica> obtenerTodos() {
    TypedQuery<FallaTecnica> query = em.createQuery("SELECT u FROM FallaTecnica u", FallaTecnica.class);
    List<FallaTecnica> fallas = query.getResultList();
    return new HashSet<>(fallas); // Convertimos la List a un Set
  }
  public FallaTecnica obtenerPorHeladera(Heladera heladera) {
      TypedQuery<FallaTecnica> query = em.createQuery(
              "SELECT f FROM FallaTecnica f WHERE f.heladera = :heladera",
              FallaTecnica.class
      );
      query.setParameter("heladera", heladera);
    return query.getSingleResult();
    }
  public void guardar(FallaTecnica falla) {
    em.getTransaction().begin();
    em.persist(falla);
    em.getTransaction().commit();

  }

}
