package Domain.Repositorios;

import Domain.Tarjeta.TarjetaColaborador;
import Domain.Usuarios.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class RepoTarjetaColaborador {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepoTarjetaColaborador instance;
  public RepoTarjetaColaborador() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepoTarjetaColaborador getInstance() {
    if (instance == null) {
      instance = new RepoTarjetaColaborador();
    }
    return instance;
  }


  public TarjetaColaborador buscarPorId(Long id) {

    TarjetaColaborador tarjeta = em.find(TarjetaColaborador.class, id);
    return tarjeta;
  }



  public List<TarjetaColaborador> obtenerTodos() {

    TypedQuery<TarjetaColaborador> query = em.createQuery("SELECT u FROM TarjetaColaborador u", TarjetaColaborador.class);
    List<TarjetaColaborador> tarjetas = query.getResultList();

    return tarjetas;
  }

  public void guardar(TarjetaColaborador taarjeta) {
    em.getTransaction().begin();
    em.persist(taarjeta);
    em.getTransaction().commit();

  }
}
