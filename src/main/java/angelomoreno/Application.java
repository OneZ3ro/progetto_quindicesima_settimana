package angelomoreno;

import angelomoreno.entities.*;
import angelomoreno.entities.DAO.*;
import angelomoreno.entities.enums.Periodicity;
import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progetto_quindicesima_settimana");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        try {
            CatalogoDAO cd = new CatalogoDAO(em);
            UtenteDAO ud = new UtenteDAO(em);
            PrestitoDAO pd = new PrestitoDAO(em);
            Faker faker = new Faker();

//            creaUtenti(ud, faker);
//            creaArchivioEPrestiti(ud, cd, pd, faker);

            System.out.println("-------------------------------- GET CATALOGO BY ISBN --------------------------------");
            cd.getCatalogoByIsbn(110).forEach(System.out::println);
            System.out.println("-------------------------------- GET CATALOGO ANNO PUBBLICAZIONE --------------------------------");
            cd.getCatalogoByAnnoPubblicazione(2018).forEach(System.out::println);
            System.out.println("-------------------------------- GET CATALOGO BY AUTORE --------------------------------");
            cd.getCatalogoByAutore("Dr.").forEach(catalogo -> System.out.println(catalogo));
            System.out.println("-------------------------------- GET CATALOGO BY TITLE --------------------------------");
            cd.getCatalogoByTitolo("The W").forEach(catalogo -> System.out.println(catalogo));
            System.out.println("-------------------------------- GET PRESTITI BY NUMERO DI TESSERA --------------------------------");
            pd.getPrestitoByNumeroUser(50).forEach(prestito -> System.out.println(prestito));
            System.out.println("-------------------------------- GET PRESTITI SCADUTI --------------------------------");
            pd.getPrestitiScaduti().forEach(prestito -> System.out.println(prestito));

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void creaUtenti(UtenteDAO ud, Faker faker) {
        for (int i = 0; i < 100; i++) {
            Utente utente = new Utente(faker.funnyName().name(), faker.funnyName().name(), faker.date().birthday());
            ud.save(utente);
        }
    }

    public static void creaArchivioEPrestiti(UtenteDAO ud, CatalogoDAO cd, PrestitoDAO pd, Faker faker){
        Random rndm = new Random();
        for (int i = 0; i < 200; i++) {
            int n = rndm.nextInt(0, 100);
            if (n%2==0) {
                Catalogo libro = new Libro(faker.book().title(), rndm.nextInt(1980, 2024), rndm.nextLong(20, 1001), faker.book().author(), faker.book().genre());
                cd.save(libro);
            } else {
                Catalogo rivista = new Rivista(faker.book().title(), rndm.nextInt(1980, 2024), rndm.nextLong(20, 1001), n%5==0 ? Periodicity.SETTIMANALE : (n%3==0 ? Periodicity.MENSILE : Periodicity.SEMESTRALE));
                cd.save(rivista);
            }
            LocalDate oggi = LocalDate.now();
            LocalDate randomday = oggi.minusDays(rndm.nextInt(0, 40));
            Prestito prestito = new Prestito(ud.getAllUtenti().get(rndm.nextInt(0, ud.getAllUtenti().size())), cd.getAllCataloghi().get(rndm.nextInt(0, cd.getAllCataloghi().size())), randomday, n%3==0 ? randomday.plusDays(rndm.nextLong(50, 100)) : (n%2==0 ? null : randomday));
            pd.save(prestito);
        }
    }
}
