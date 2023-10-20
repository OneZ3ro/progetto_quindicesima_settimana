package angelomoreno.entities.DAO;

import angelomoreno.entities.Catalogo;
import angelomoreno.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public class CatalogoDAO {
    private final EntityManager em;
    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo catalogo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(catalogo);
        transaction.commit();
        System.out.println("Nuovo catalogo creato correttamente");
    }

    public Catalogo getById(long id) {
        return em.find(Catalogo.class, id);
    }

    public void delete(long id) {
        Catalogo found = em.find(Catalogo.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Il catalogo è stato cancellato con successo");
        } else {
            System.err.println("Il catalogo con id" + id + " non è stato trovato");
        }
    }

    public void refresh(long id) {
        Catalogo found = em.find(Catalogo.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("Il catalogo è stato refreshato con successo");
        } else {
            System.err.println("Il catalogo con id" + id + " non è stato trovato");
        }
    }

    public List<Catalogo> getAllCataloghi() {
        TypedQuery getAllCataloghiQuery = em.createQuery("SELECT c FROM Catalogo c", Catalogo.class);
        return getAllCataloghiQuery.getResultList();
    }

    public List<Catalogo> getCatalogoByIsbn(long isbn) {
        TypedQuery<Catalogo> getConcertiInStreamingQuery = em.createQuery("SELECT c FROM Catalogo c WHERE c.isbn = :isbn", Catalogo.class);
        getConcertiInStreamingQuery.setParameter("isbn", isbn);
        return getConcertiInStreamingQuery.getResultList();
    }

    public List<Catalogo> getCatalogoByAnnoPubblicazione(int anno_pubblicazione) {
        TypedQuery<Catalogo> getCatalogoByAnnoPubblicazioneQuery = em.createQuery("SELECT c FROM Catalogo c WHERE c.anno_pubblicazione = :anno_pubblicazione", Catalogo.class);
        getCatalogoByAnnoPubblicazioneQuery.setParameter("anno_pubblicazione", anno_pubblicazione);
        return getCatalogoByAnnoPubblicazioneQuery.getResultList();
    }

    public List<Catalogo> getCatalogoByAutore(String autore) {
        TypedQuery<Catalogo> getCatalogoByAutoreQuery = em.createQuery("SELECT c FROM Catalogo c WHERE c.autore = :autore", Catalogo.class);
        getCatalogoByAutoreQuery.setParameter("autore", autore);
        return getCatalogoByAutoreQuery.getResultList();
    }

    public List<Catalogo> getCatalogoByTitolo(String titolo) {
        TypedQuery<Catalogo> getCatalogoByTitoloQuery = em.createQuery("SELECT c FROM Catalogo c WHERE LOWER(c.autore) LIKE LOWER(CONCAT('%', :titolo, '%'))", Catalogo.class);
        getCatalogoByTitoloQuery.setParameter("titolo", titolo);
        return getCatalogoByTitoloQuery.getResultList();
    }

//    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
//        return em.createQuery("SELECT p FROM PartitaDiCalcio p WHERE p.squadraDiCasa = p.squadraVincente", PartitaDiCalcio.class).getResultList();
//    }
//
//    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
//        return em.createQuery("SELECT p FROM PartitaDiCalcio p WHERE p.squadraOspite = p.squadraVincente", PartitaDiCalcio.class).getResultList();
//    }
}
