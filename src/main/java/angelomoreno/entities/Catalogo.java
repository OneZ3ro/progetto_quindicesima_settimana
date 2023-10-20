package angelomoreno.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cataloghi")
public abstract class Catalogo {
    @Id
    @GeneratedValue
    protected long isbn;
    protected String titolo;
    protected int anno_pubblicazione;
    protected long numero_pagine;

    @OneToOne(mappedBy = "elemento_prestato")
    private Prestito prestito;

    public Catalogo(){};

    public Catalogo(String titolo, int anno_pubblicazione, long numero_pagine) {
        this.titolo = titolo;
        this.anno_pubblicazione = anno_pubblicazione;
        this.numero_pagine = numero_pagine;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnno_pubblicazione() {
        return anno_pubblicazione;
    }

    public long getNumero_pagine() {
        return numero_pagine;
    }

    public Prestito getPrestito() {
        return prestito;
    }

    @Override
    public String toString() {
        return  "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", numero_pagine=" + numero_pagine +
                ", prestito=" + prestito;
    }
}
