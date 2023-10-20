package angelomoreno.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue
    private long prestito_id;
    @ManyToOne
    @JoinColumn(name = "numero_di_tessera", nullable = false)
    private Utente utente;

    @OneToOne
    @JoinColumn(name = "isbn_libro_prestato")
    private Catalogo elemento_prestato;

    private LocalDate data_inizio_prestito;
    private LocalDate data_restituzione_prevista;
    private Date data_restituzione_effettiva;

    public Prestito(){};

    public Prestito(Utente utente, Catalogo elemento_prestato, LocalDate data_inizio_prestito, Date data_restituzione_effettiva) {
        this.utente = utente;
        this.elemento_prestato = elemento_prestato;
        this.data_inizio_prestito = data_inizio_prestito;
        this.data_restituzione_prevista = data_inizio_prestito.plusDays(30);
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    public LocalDate getData_inizio_prestito() {
        return data_inizio_prestito;
    }

    public void setData_inizio_prestito(LocalDate data_inizio_prestito) {
        this.data_inizio_prestito = data_inizio_prestito;
    }

    public LocalDate getData_restituzione_prevista() {
        return data_restituzione_prevista;
    }

    public Date getData_restituzione_effettiva() {
        return data_restituzione_effettiva;
    }

    public void setData_restituzione_effettiva(Date data_restituzione_effettiva) {
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public Catalogo getElemento_prestato() {
        return elemento_prestato;
    }

    public long getPrestito_id() {
        return prestito_id;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "prestito_id" + prestito_id +
                "utente=" + utente +
                ", elemento_prestato=" + elemento_prestato +
                ", data_inizio_prestito=" + data_inizio_prestito +
                ", data_restituzione_prevista=" + data_restituzione_prevista +
                ", data_restituzione_effettiva=" + data_restituzione_effettiva +
                '}';
    }
}
