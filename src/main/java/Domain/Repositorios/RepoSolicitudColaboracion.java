package Domain.Repositorios;

import Domain.Colaborador.Colaborador;
import Domain.Solicitudes.SolicitudColaboracion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepoSolicitudColaboracion {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static RepoSolicitudColaboracion instance;


    public RepoSolicitudColaboracion() {

        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }

    public static RepoSolicitudColaboracion getInstance() {
        if (instance == null) {
            instance = new RepoSolicitudColaboracion();
        }
        return instance;
    }


    public SolicitudColaboracion buscarPorId(Long id) {

        SolicitudColaboracion colaborador = em.find(SolicitudColaboracion.class, id);

        return colaborador;
    }


    public Set<SolicitudColaboracion> obtenerTodos() {
        String jpql = "SELECT DISTINCT s FROM SolicitudColaboracion s LEFT JOIN FETCH s.colaboracion";
        List<SolicitudColaboracion> colaboradores = em.createQuery(jpql, SolicitudColaboracion.class).getResultList();
        return new HashSet<>(colaboradores); // Convertir List a Set
    }


    public void guardar(SolicitudColaboracion c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    public void merge(SolicitudColaboracion c) {
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }
    public void remove(SolicitudColaboracion c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }
}
