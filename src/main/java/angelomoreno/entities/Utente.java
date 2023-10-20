package angelomoreno.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    private long numero_di_tessera;
    private String nome;
    private String cognome;
    private Date data_di_nascita;

    @OneToMany(mappedBy = "utente")
    private List<Prestito> prestiti;

    public Utente(){}

    public Utente(String nome, String cognome, Date data_di_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(Date data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public long getNumero_di_tessera() {
        return numero_di_tessera;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_di_nascita=" + data_di_nascita +
                ", numero_di_tessera=" + numero_di_tessera +
                '}';
    }
}
