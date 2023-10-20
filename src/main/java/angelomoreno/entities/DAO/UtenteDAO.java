package angelomoreno.entities.DAO;

import angelomoreno.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class UtenteDAO {
    private final EntityManager em;
    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("Nuovo utente creato correttamente");
    }

    public Utente getById(UUID id) {
        return em.find(Utente.class, id);
    }

    public void delete(UUID id) {
        Utente found = em.find(Utente.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("L'utente è stato cancellato con successo");
        } else {
            System.err.println("L'utente con id" + id + " non è stato trovato");
        }
    }

    public void refresh(UUID id) {
        Utente found = em.find(Utente.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("L'utente è stato refreshato con successo");
        } else {
            System.err.println("L'utente con id" + id + " non è stato trovato");
        }
    }

    public List<Utente> getAllUtenti() {
        TypedQuery getAllUtentiQuery = em.createQuery("SELECT u FROM Utente u", Utente.class);
        return getAllUtentiQuery.getResultList();
    }

}
