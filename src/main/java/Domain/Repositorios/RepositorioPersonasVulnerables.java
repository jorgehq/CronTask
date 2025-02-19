package Domain.Repositorios;

import Domain.Persona.PersonaVulnerable;
import Domain.Usuarios.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPersonasVulnerables {
  private EntityManagerFactory emf;
  private EntityManager em;
  private static RepositorioPersonasVulnerables instance;
  public RepositorioPersonasVulnerables() {

    emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
    em = emf.createEntityManager();
  }

  public static RepositorioPersonasVulnerables getInstance() {
    if (instance == null) {
      instance = new RepositorioPersonasVulnerables();
    }
    return instance;
  }


  public PersonaVulnerable buscarPorId(Long id) {

    PersonaVulnerable persona = em.find(PersonaVulnerable.class, id);
    return persona;
  }


  public List<PersonaVulnerable> obtenerTodos() {

    TypedQuery<PersonaVulnerable> query = em.createQuery("SELECT u FROM PersonaVulnerable u", PersonaVulnerable.class);
    List<PersonaVulnerable> personas = query.getResultList();

    return personas;
  }

  public void guardar(PersonaVulnerable persona) {
    em.getTransaction().begin();
    em.persist(persona);
    em.getTransaction().commit();

  }
}
