package Domain.Repositorios;

import Domain.Colaborador.TipoDeColaboracion.DonarVianda;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoColaboraciones {

  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoColaboraciones instance;


  public RepoColaboraciones() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoColaboraciones getInstance() {
    if (instance == null) {
      instance = new RepoColaboraciones();
    }
    return instance;
  }


  public TipoDeColaboracion buscarPorId(Long id) {

    TipoDeColaboracion colaboracion = em.find(TipoDeColaboracion.class, id);

    return colaboracion;
  }


  public List<TipoDeColaboracion> obtenerTodos() {

    TypedQuery<TipoDeColaboracion> query = em.createQuery("SELECT u FROM TipoDeColaboracion u", TipoDeColaboracion.class);
    List<TipoDeColaboracion> colaboraciones = query.getResultList();
    return colaboraciones;
  }
  public List<DonarVianda> obtenerDonacionesViandas() {
    TypedQuery<DonarVianda> query = em.createQuery(
            "SELECT d FROM DonarVianda d", DonarVianda.class
    );
    return query.getResultList();
  }

  public void guardar(TipoDeColaboracion c) {
    em.getTransaction().begin();
    em.persist(c);
    em.getTransaction().commit();
  }

  public void merge(TipoDeColaboracion c) {
    em.getTransaction().begin();
    em.merge(c);
    em.getTransaction().commit();
  }
  public void remover(TipoDeColaboracion c){
    em.getTransaction().begin();
    em.remove(c);
    em.getTransaction().commit();

  }

}
