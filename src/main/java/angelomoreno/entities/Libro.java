package angelomoreno.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "libri")
public class Libro extends Catalogo{
    private String autore;
    private String genere;

    public Libro(){};

    public Libro(String titolo, int anno_pubblicazione, long numero_pagine, String autore, String genere) {
        super(titolo, anno_pubblicazione, numero_pagine);
        this.autore = autore;
        this.genere = genere;
    }
//    public Libro(String titolo, int anno_pubblicazione, long numero_pagine, String autore, String genere, BigInteger isbn) {
//        this(titolo, anno_pubblicazione, numero_pagine, autore, genere);
//        this.isbn = isbn;
//    }
//
//    public BigInteger getIsbn() {
//        return this.isbn;
//    }
//
//    public String getTitolo() {
//        return this.titolo;
//    }
//
//    public long getNumeroPagine() {
//        return this.numero_pagine;
//    }
//
//    public int getAnno() {
//        return this.anno_pubblicazione;
//    }
    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }
//    @Override
//    public String toString() {
//        return "Libro{" +
//                "isbn=" + isbn +
//                ", titolo='" + titolo + '\'' +
//                ", anno_pubblicazione=" + anno_pubblicazione +
//                ", numero_pagine=" + numero_pagine +
//                ", autore='" + autore + '\'' +
//                ", genere='" + genere + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", numero_pagine=" + numero_pagine +
                '}';
    }
}
