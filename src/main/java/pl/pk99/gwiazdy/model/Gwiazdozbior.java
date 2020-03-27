package pl.pk99.gwiazdy.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Gwiazdozbiory")
public class Gwiazdozbior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Gwiazdozbior_ID")
    private int id;

    private String nazwa;
    private int liczba;


    public void setId(int id) {
        this.id = id;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public int getId() {
        return id;
    }

    public int getLiczba() {
        return liczba;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void dodajGwiazde() {
        liczba++;
    }

    public void resetujGwiazdy() {
        liczba = 0;
    }

    public Gwiazdozbior() {

    }

    public Gwiazdozbior(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gwiazdozbior)) return false;
        Gwiazdozbior that = (Gwiazdozbior) o;
        return id == that.id &&
                liczba == that.liczba &&
                Objects.equals(nazwa, that.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwa, liczba);
    }
}
