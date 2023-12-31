package angelomoreno.entities.DAO;

import angelomoreno.entities.Catalogo;
import angelomoreno.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

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

    public Prestito getById(UUID id) {
        return em.find(Prestito.class, id);
    }

    public void delete(UUID id) {
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

    public void refresh(UUID id) {
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

    public List<Prestito> getPrestitoByNumeroUser(UUID numero_di_tessera) {
        TypedQuery<Prestito> getPrestitoByNumeroUserQuery = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numero_di_tessera = :numero_di_tessera AND (data_restituzione_prevista > p.data_restituzione_effettiva OR p.data_restituzione_effettiva = null)", Prestito.class);
        getPrestitoByNumeroUserQuery.setParameter("numero_di_tessera", numero_di_tessera);
        return getPrestitoByNumeroUserQuery.getResultList();
    }

    public List<Prestito> getPrestitiScaduti() {
        TypedQuery<Prestito> getPrestitiScadutiQuery = em.createQuery("SELECT p FROM Prestito p WHERE p.data_restituzione_prevista < p.data_restituzione_effettiva OR p.data_restituzione_effettiva = null", Prestito.class);
        return getPrestitiScadutiQuery.getResultList();
    }

}
