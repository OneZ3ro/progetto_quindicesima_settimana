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
import java.util.*;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("progetto_quindicesima_settimana");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner input = new Scanner(System.in);

        try {
            CatalogoDAO cd = new CatalogoDAO(em);
            UtenteDAO ud = new UtenteDAO(em);
            PrestitoDAO pd = new PrestitoDAO(em);
            Faker faker = new Faker();

//            creaUtenti(ud, faker);
//            creaArchivioEPrestiti(ud, cd, pd, faker);
            myLoop:
            while(true) {
                try {
                    System.out.println("Cosa vuoi fare?");
                    System.out.println("[1]: GET CATALOGO BY ISBN");
                    System.out.println("[2]: GET CATALOGO ANNO PUBBLICAZIONE");
                    System.out.println("[3]: GET LIBRO BY AUTORE");
                    System.out.println("[4]: GET CATALOGO BY TITLE");
                    System.out.println("[5]: GET PRESTITI BY NUMERO DI TESSERA");
                    System.out.println("[6]: GET PRESTITI SCADUTI");
                    System.out.println("[0]: TERMINA PROGRAMMA");
                    int choose = Integer.parseInt(input.nextLine());
                    switch (choose) {
                        case 1:
                            System.out.println("Inserisci l'ISBN del tuo libro/rivista");
                            int isbn = Integer.parseInt(input.nextLine());
                            System.out.println("-------------------------------- GET CATALOGO BY ISBN --------------------------------");
                            cd.getCatalogoByIsbn(isbn).forEach(System.out::println);
                            break;
                        case 2:
                            System.out.println("Inserisci l'anno del libro/rivista che stai cercando");
                            int anno = Integer.parseInt(input.nextLine());
                            System.out.println("-------------------------------- GET CATALOGO ANNO PUBBLICAZIONE --------------------------------");
                            cd.getCatalogoByAnnoPubblicazione(anno).forEach(System.out::println);
                            break;
                        case 3:
                            System.out.println("Inserisci il nome (o parte del nome) dell'autore del libro");
                            String autore = input.nextLine();
                            System.out.println("-------------------------------- GET LIBRO BY AUTORE --------------------------------");
                            cd.getCatalogoByAutore(autore).forEach(catalogo -> System.out.println(catalogo));
                            break;
                        case 4:
                            System.out.println("Inserisci il titolo (o parte del titolo) del tuo libro/rivista");
                            String titolo = input.nextLine();
                            System.out.println("-------------------------------- GET CATALOGO BY TITLE --------------------------------");
                            cd.getCatalogoByTitolo(titolo).forEach(catalogo -> System.out.println(catalogo));
                            break;
                        case 5:
                            System.out.println("Inserisci il tuo numero di tessera");
                            int numero_tessera = Integer.parseInt(input.nextLine());
                            System.out.println("-------------------------------- GET PRESTITI BY NUMERO DI TESSERA --------------------------------");
                            pd.getPrestitoByNumeroUser(numero_tessera).forEach(prestito -> System.out.println(prestito));
                            break;
                        case 6:
                            System.out.println("-------------------------------- GET PRESTITI SCADUTI --------------------------------");
                            pd.getPrestitiScaduti().forEach(prestito -> System.out.println(prestito));
                            break;
                        case 0:
                            System.out.println("PROGRAMMA TERMINATO CON SUCCESSO");
                            break myLoop;
                        default:
                            throw new NumberFormatException();
                    }
                } catch (NumberFormatException numberFormatException) {
                    System.err.println("Non hai scelto una delle possibili scelte. Riprova");
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
            input.close();
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
