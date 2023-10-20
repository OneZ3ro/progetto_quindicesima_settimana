package angelomoreno.entities.DAO;

import angelomoreno.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PrestitoDAO {
    private final EntityManager em;
    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(prestito);
        transaction.commit();
        System.out.println("Nuovo prestito creato correttamente");
    }

    public Prestito getById(long id) {
        return em.find(Prestito.class, id);
    }

    public void delete(long id) {
        Prestito found = em.find(Prestito.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Il prestito è stato cancellato con successo");
        } else {
            System.err.println("Il prestito con id" + id + " non è stato trovato");
        }
    }

    public void refresh(long id) {
        Prestito found = em.find(Prestito.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("Il prestito è stato refreshato con successo");
        } else {
            System.err.println("Il prestito con id" + id + " non è stato trovato");
        }
    }

}
