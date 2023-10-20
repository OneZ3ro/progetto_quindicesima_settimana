package angelomoreno.entities;

import angelomoreno.entities.enums.Periodicity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "riviste")
public class Rivista extends Catalogo{
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Rivista(){}

    public Rivista(String titolo, int anno_pubblicazione, long numero_pagine, Periodicity periodicity) {
        super(titolo, anno_pubblicazione, numero_pagine);
        this.periodicity = periodicity;
    }
    public Periodicity getPeriodicity() {
        return periodicity;
    }
    @Override
    public String toString() {
        return "Rivista{" +
                "periodicity=" + periodicity +
                ", isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", numero_pagine=" + numero_pagine +
                '}';
    }
}
