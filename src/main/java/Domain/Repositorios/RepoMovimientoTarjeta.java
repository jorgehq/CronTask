package Domain.Repositorios;

import Domain.Notificaciones.Notificacion;
import Domain.Tarjeta.MovimientoTarjeta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepoMovimientoTarjeta {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static RepoMovimientoTarjeta instance;
    public RepoMovimientoTarjeta() {

        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();
    }

    public static RepoMovimientoTarjeta getInstance() {
        if (instance == null) {
            instance = new RepoMovimientoTarjeta();
        }
        return instance;
    }


    public MovimientoTarjeta buscarPorId(Long id) {

        MovimientoTarjeta movimiento = em.find(MovimientoTarjeta.class, id);

        return movimiento;
    }



    public List<MovimientoTarjeta> obtenerTodos() {

        TypedQuery<MovimientoTarjeta> query = em.createQuery("SELECT u FROM MovimientoTarjeta u", MovimientoTarjeta.class);

        List<MovimientoTarjeta> movimiento = query.getResultList();
        return movimiento;
    }

    public void guardar(MovimientoTarjeta movimiento) {
        em.getTransaction().begin();
        em.persist(movimiento);
        em.getTransaction().commit();

    }
}
