package angelomoreno.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cataloghi")
public abstract class Catalogo {
    @Id
    private BigInteger isbn;
    private String titolo;
    private int anno_pubblicazione;
    private long numero_pagine;

    @OneToOne(mappedBy = "elemento_prestato")
    private Prestito prestito;

    public Catalogo(){};

    public Catalogo(String titolo, int anno_pubblicazione, long numero_pagine) {
        Random rndm = new Random();
        String c1 = String.valueOf(rndm.nextInt(0, 10));
        String c2 = String.valueOf(rndm.nextInt(0, 10));
        String c3 = String.valueOf(rndm.nextInt(0, 10));
        String c4 = String.valueOf(rndm.nextInt(0, 10));
        String c5 = String.valueOf(rndm.nextInt(0, 10));
        String c6 = String.valueOf(rndm.nextInt(0, 10));
        String c7 = String.valueOf(rndm.nextInt(0, 10));
        String c8 = String.valueOf(rndm.nextInt(0, 10));
        String c9 = String.valueOf(rndm.nextInt(0, 10));
        String c10 = String.valueOf(rndm.nextInt(0, 10));
        String c11 = String.valueOf(rndm.nextInt(0, 10));
        String c12 = String.valueOf(rndm.nextInt(0, 10));
        this.isbn = new BigInteger("9" + c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9 + c10 + c11 + c12);
        this.titolo = titolo;
        this.anno_pubblicazione = anno_pubblicazione;
        this.numero_pagine = numero_pagine;
    }

    public BigInteger getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return anno_pubblicazione;
    }

    public long getNumeroPagine() {
        return numero_pagine;
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
