package Domain.Repositorios;

import Domain.Colaborador.RegistroPuntaje.RegistroPuntaje;
import Domain.Colaborador.TipoDeColaboracion.TipoDeColaboracion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoPuntajes {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static RepoPuntajes instance;


    public RepoPuntajes() {

        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }

    public static RepoPuntajes getInstance() {
        if (instance == null) {
            instance = new RepoPuntajes();
        }
        return instance;
    }


    public RegistroPuntaje buscarPorId(Long id) {

        RegistroPuntaje puntajes = em.find(RegistroPuntaje.class, id);

        return puntajes;
    }


    public List<RegistroPuntaje> obtenerTodos() {

        TypedQuery<RegistroPuntaje> query = em.createQuery("SELECT u FROM RegistroPuntaje u", RegistroPuntaje.class);
        List<RegistroPuntaje> puntajes = query.getResultList();
        return puntajes;
    }


    public void guardar(RegistroPuntaje puntajes) {
        em.getTransaction().begin();
        em.persist(puntajes);
        em.getTransaction().commit();
    }

}
