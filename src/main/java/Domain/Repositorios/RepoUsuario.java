package Domain.Repositorios;

import Domain.Usuarios.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoUsuario {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static RepoUsuario instance;
    public RepoUsuario() {

        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }

    public static RepoUsuario getInstance() {
        if (instance == null) {
            instance = new RepoUsuario();
        }
        return instance;
    }


    public Usuario buscarPorId(Long id) {

        Usuario usuario = em.find(Usuario.class, id);
        return usuario;
    }
    public Usuario buscarPorIdColaborador(Long id) {

        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.asignado.id = :id", Usuario.class);
        query.setParameter("id", id);
        List<Usuario> usuarios = query.getResultList();
        Usuario o=usuarios.get(0);
        em.refresh(o);
        em.refresh(o.getAsignado());
        return o;
    }

    public List<Usuario> buscarPorNombre(String nombre) {

        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombre", Usuario.class);
        query.setParameter("nombre", nombre);
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }
    public List<Usuario> buscarPorNombreReal(String nombre) {

        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
        query.setParameter("nombre", nombre);
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }

    public List<Usuario> obtenerTodos() {

        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> usuarios = query.getResultList();

        return usuarios;
    }

    public void guardar(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

    }


}
