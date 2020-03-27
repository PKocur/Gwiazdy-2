package pl.pk99.gwiazdy.model;

//Klasa gwiazdy

import pl.pk99.gwiazdy.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Gwiazdy")
public class Gwiazda implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String nazwa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Gwiazdozbior_ID")
    private Gwiazdozbior gwiazdozbior;

    private String nazwaKatalogowa;

    @OneToOne(mappedBy = "gwiazda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Deklinacja deklinacja;

    @OneToOne(mappedBy = "gwiazda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Rektascensja rektascensja;

    private double odleglosc;
    private boolean polnocna;

    private Temperatura temperatura;
    private Masa masa;
    private ObserwowanaWielkoscGwiazdowa obserowanaWielkoscGwiazdowa;
    private double absolutnaWielkoscGwiazdowa;

    public Gwiazda () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Rektascensja getRektascensja() {
        return rektascensja;
    }

    public ObserwowanaWielkoscGwiazdowa getObserowanaWielkoscGwiazdowa() {
        return obserowanaWielkoscGwiazdowa;
    }

    public double getAbsolutnaWielkoscGwiazdowa() {
        return absolutnaWielkoscGwiazdowa;
    }

    public Deklinacja getDeklinacja() {
        return deklinacja;
    }

    public double getOdleglosc() { return odleglosc; }

    public boolean isPolnocna() {
        return polnocna;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public Masa getMasa() {
        return masa;
    }

    public Gwiazdozbior getGwiazdozbior() {
        return gwiazdozbior;
    }

    public void setNazwaKatalogowa(String nazwa) {
        nazwaKatalogowa = nazwa;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setGwiazdozbior(Gwiazdozbior gwiazdozbior) {
        this.gwiazdozbior = gwiazdozbior;
    }

    public void setDeklinacja(Deklinacja deklinacja) {
        this.deklinacja = deklinacja;
    }

    public void setRektascensja(Rektascensja rektascensja) {
        this.rektascensja = rektascensja;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public void setPolnocna(boolean polnocna) {
        this.polnocna = polnocna;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public void setMasa(Masa masa) {
        this.masa = masa;
    }

    public void setObserowanaWielkoscGwiazdowa(ObserwowanaWielkoscGwiazdowa obserowanaWielkoscGwiazdowa) {
        this.obserowanaWielkoscGwiazdowa = obserowanaWielkoscGwiazdowa;
    }

    public void setAbsolutnaWielkoscGwiazdowa(double owg, double odleglosc) {
        absolutnaWielkoscGwiazdowa =
                //Zaokrąglana jest do dwóch miejsc po przecinku
                Math.round((owg - 5 * Math.log10(odleglosc) + 5)
                        * 100.0) / 100.0;
    }

    public String getNazwaKatalogowa() {
        return nazwaKatalogowa;
    }

    public Gwiazda(String nazwa, Gwiazdozbior gwiazdozbior, ObserwowanaWielkoscGwiazdowa obserwowanaWielkoscGwiazdowa,
            double odleglosc, boolean polnocna, Deklinacja deklinacja, Rektascensja rektascensja,
            Temperatura temperatura, Masa masa) {
        this.nazwa = nazwa;
        this.obserowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        setAbsolutnaWielkoscGwiazdowa(obserwowanaWielkoscGwiazdowa.get(), odleglosc);
        this.odleglosc = odleglosc;
        this.polnocna = polnocna;
        this.temperatura = temperatura;
        this.masa = masa;
        this.deklinacja = deklinacja;
        this.deklinacja.setGwiazda(this);
        this.rektascensja = rektascensja;
        this.rektascensja.setGwiazda(this);
        this.gwiazdozbior = gwiazdozbior;
    }

    public void utworzNazweKatalogowa() {
        this.gwiazdozbior.dodajGwiazde();
        int liczbaGwiazdGwiazdozbioru = gwiazdozbior.getLiczba();
        nazwaKatalogowa = AlfabetGrecki.values()[liczbaGwiazdGwiazdozbioru - 1] + " " + gwiazdozbior.getNazwa();
    }

    @Override
    public String toString() {
        System.out.println();
        return ("----------------------------------") +
                "\nNazwa: " + nazwa + "\nNazwa katalogowa: " + nazwaKatalogowa +
                "\nDeklinacja: " + deklinacja.toString() +
                "\nRektascensja: " + rektascensja.toString() +
                "\nOdległość: " + odleglosc + " lat świetlnych" +
                "\nPółkula: " + ((polnocna) ? "Północna" : "Południowa") +
                "\nTemeratura: " + temperatura.get() + " °C" +
                "\nMasa: " + masa.get() + " masy Słońca" +
                "\nObserwowana wielkość gwiazdowa: " + obserowanaWielkoscGwiazdowa.get() +
                "\nAbsolutna wielkość gwiazdowa: " + absolutnaWielkoscGwiazdowa +
                "\n----------------------------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gwiazda)) return false;
        Gwiazda gwiazda = (Gwiazda) o;
        return id == gwiazda.id &&
                Double.compare(gwiazda.odleglosc, odleglosc) == 0 &&
                polnocna == gwiazda.polnocna &&
                Double.compare(gwiazda.absolutnaWielkoscGwiazdowa, absolutnaWielkoscGwiazdowa) == 0 &&
                Objects.equals(nazwa, gwiazda.nazwa) &&
                Objects.equals(gwiazdozbior, gwiazda.gwiazdozbior) &&
                Objects.equals(nazwaKatalogowa, gwiazda.nazwaKatalogowa) &&
                Objects.equals(deklinacja, gwiazda.deklinacja) &&
                Objects.equals(rektascensja, gwiazda.rektascensja) &&
                Objects.equals(temperatura, gwiazda.temperatura) &&
                Objects.equals(masa, gwiazda.masa) &&
                Objects.equals(obserowanaWielkoscGwiazdowa, gwiazda.obserowanaWielkoscGwiazdowa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwa, gwiazdozbior, nazwaKatalogowa, deklinacja, rektascensja, odleglosc, polnocna, temperatura, masa, obserowanaWielkoscGwiazdowa, absolutnaWielkoscGwiazdowa);
    }
}
