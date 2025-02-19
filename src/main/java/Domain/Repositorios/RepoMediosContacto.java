package Domain.Repositorios;

import Domain.Colaborador.MedioDeContacto.Mediodecontacto;
import Domain.Usuarios.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoMediosContacto {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static RepoMediosContacto instance;
    public RepoMediosContacto() {

        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }

    public static RepoMediosContacto getInstance() {
        if (instance == null) {
            instance = new RepoMediosContacto();
        }
        return instance;
    }


    public Mediodecontacto buscarPorId(Long id) {

        Mediodecontacto contacto = em.find(Mediodecontacto.class, id);
        return contacto;
    }


    public void guardar(Mediodecontacto medio) {
        em.getTransaction().begin();
        em.persist(medio);
        em.getTransaction().commit();
        cerrar();
    }

    public void cerrar() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
