package Domain.Repositorios;

import Domain.Tarjeta.Tarjeta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoTarjetas {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoTarjetas instance;
  public RepoTarjetas() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoTarjetas getInstance() {
    if (instance == null) {
      instance = new RepoTarjetas();
    }
    return instance;
  }


  public Tarjeta buscarPorId(Long id) {

    Tarjeta tarjeta = em.find(Tarjeta.class, id);
    return tarjeta;
  }

  public Tarjeta buscarPorNumeroTarjeta(String numero) {
    TypedQuery<Tarjeta> query = em.createQuery(
        "SELECT t FROM Tarjeta t WHERE t.codigo = :numero", Tarjeta.class
    );
    query.setParameter("numero", numero);
    return query.getSingleResult();

  }

  public List<Tarjeta> obtenerTodos() {

    TypedQuery<Tarjeta> query = em.createQuery("SELECT u FROM Tarjeta u", Tarjeta.class);
    List<Tarjeta> tarjetas = query.getResultList();

    return tarjetas;
  }

  public void guardar(Tarjeta taarjeta) {
    em.getTransaction().begin();
    em.persist(taarjeta);
    em.getTransaction().commit();

  }
}
