package Domain.Repositorios;

import Domain.Incidentes.Alerta;
import Domain.Notificaciones.Notificacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoNotificaciones {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoNotificaciones instance;
  public RepoNotificaciones() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoNotificaciones getInstance() {
    if (instance == null) {
      instance = new RepoNotificaciones();
    }
    return instance;
  }


  public Notificacion buscarPorId(Long id) {

    Notificacion noti = em.find(Notificacion.class, id);

    return noti;
  }



  public List<Notificacion> obtenerTodos() {

    TypedQuery<Notificacion> query = em.createQuery("SELECT u FROM Notificacion u", Notificacion.class);

    List<Notificacion> notis = query.getResultList();
    return notis;
  }
  public List<Notificacion> obtenerNotificacionesPorColaborador(Long colaboradorId) {
    String jpql = "SELECT n FROM Notificacion n " +
            "JOIN Colaborador c WHERE c.id = :colaboradorId";
    TypedQuery<Notificacion> query = em.createQuery(jpql, Notificacion.class);
    query.setParameter("colaboradorId", colaboradorId);
    return query.getResultList();
  }

  public void guardar(Notificacion noti) {
    em.getTransaction().begin();
    em.persist(noti);
    em.getTransaction().commit();

  }
}
