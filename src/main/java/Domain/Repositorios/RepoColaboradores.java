package Domain.Repositorios;

import Domain.Colaborador.Colaborador;
import Domain.Colaborador.PersonaHumana;
import Domain.Colaborador.PersonaJuridica;
import Domain.Usuarios.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepoColaboradores {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoColaboradores instance;


  public RepoColaboradores() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoColaboradores getInstance() {
    if (instance == null) {
      instance = new RepoColaboradores();
    }
    return instance;
  }


  public Colaborador buscarPorId(Long id) {

    Colaborador colaborador = em.find(Colaborador.class, id);

    em.refresh(colaborador);
    return colaborador;
  }

  public List<Colaborador> buscarPorNombre(String nombre) {

    TypedQuery<Colaborador> query = em.createQuery("SELECT u FROM Colaborador u WHERE u.nombre = :nombre", Colaborador.class);
    query.setParameter("nombre", nombre);
    List<Colaborador> colaboradores = query.getResultList();
    return colaboradores;
  }

  public List<Colaborador> obtenerTodos() {

    TypedQuery<Colaborador> query = em.createQuery("SELECT u FROM Colaborador u", Colaborador.class);
    List<Colaborador> colaboradores = query.getResultList();
    return colaboradores;
  }

  public void guardar(Colaborador c) {
    em.getTransaction().begin();
    em.persist(c);
    em.getTransaction().commit();

  }

  public void merge(Colaborador c) {
    em.getTransaction().begin();
    em.merge(c);
    em.getTransaction().commit();

  }
  public void eliminarNotificacionColaborador(Long colaboradorId, Long notificacionId) {
    em.getTransaction().begin();
    Query query = em.createNativeQuery("DELETE FROM notificacion_colaborador WHERE notificacion_id = :notificacionId AND colaborador_id = :colaboradorId");
    query.setParameter("notificacionId", notificacionId);
    query.setParameter("colaboradorId", colaboradorId);
    query.executeUpdate();
    em.getTransaction().commit();
  }
// No cerrar la conexion ya que es singleton porque rompe todo

}
