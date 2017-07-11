package de.fhdortmund.swt2.pruefungsmeister.Persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by jonas on 03.07.17.
 */
public class PersistenceManager implements PruefungsmeisterDAO {
    private static EntityManagerFactory emf;
    private EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory(
                    "de.fhdortmund.swt2.pruefungsmeister.PersistenceUnit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PersistenceManager() {
        em = emf.createEntityManager();
    }

    @Override
    public <T> void persist(T object) {
        em.persist(object);

    }

    @Override
    public <T> void update(T object) {
        em.merge(object);
    }

    @Override
    public void deleteAllPlayers() {
        Query q = em.createNativeQuery("DELETE FROM Player");
        q.executeUpdate();
    }

    @Override
    public void beginTransaction() {
        em.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
        em.getTransaction().commit();
    }

    @Override
    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }
}
