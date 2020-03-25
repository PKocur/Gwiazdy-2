package pl.pk99.gwiazdy;

//Klasa gwiazdy

public class Gwiazda implements java.io.Serializable {

    private String nazwa;
    private String gwiazdozbior;
    private String nazwaKatalogowa;
    private Deklinacja deklinacja;
    private Rektascensja rektascensja;
    private double odleglosc;
    private boolean polnocna;
    private Temperatura temperatura;
    private Masa masa;
    private ObserwowanaWielkoscGwiazdowa obserowanaWielkoscGwiazdowa;
    private AbsolutnaWielkoscGwiazdowa absolutnaWielkoscGwiazdowa;

    AbsolutnaWielkoscGwiazdowa getAbsolutnaWielkoscGwiazdowa() { return absolutnaWielkoscGwiazdowa; }

    double getOdleglosc() { return odleglosc; }

    boolean isPolnocna() {
        return polnocna;
    }

    Temperatura getTemperatura() {
        return temperatura;
    }

    Masa getMasa() {
        return masa;
    }

    String getGwiazdozbior() {
        return gwiazdozbior;
    }

    void setNazwaKatalogowa(String nazwa) {
        nazwaKatalogowa = nazwa;
    }

    String getNazwaKatalogowa() {
        return nazwaKatalogowa;
    }

    Gwiazda(String nazwa, String gwiazdozbior, ObserwowanaWielkoscGwiazdowa obserwowanaWielkoscGwiazdowa,
            double odleglosc, boolean polnocna, Deklinacja deklinacja, Rektascensja rektascensja,
            Temperatura temperatura, Masa masa) {
        this.nazwa = nazwa;
        this.obserowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        absolutnaWielkoscGwiazdowa = new AbsolutnaWielkoscGwiazdowa
                (obserwowanaWielkoscGwiazdowa.get(), odleglosc);
        this.odleglosc = odleglosc;
        this.polnocna = polnocna;
        this.temperatura = temperatura;
        this.masa = masa;
        this.deklinacja = deklinacja;
        this.rektascensja = rektascensja;
        this.gwiazdozbior = gwiazdozbior;
    }

    @Override
    public String toString() {
        System.out.println();
        return ("----------------------------------" +
                "\nNazwa: " + nazwa + "\nNazwa katalogowa: " + nazwaKatalogowa +
                "\nDeklinacja: " + deklinacja.toString() +
                "\nRektascensja: " + rektascensja.toString() +
                "\nOdległość: " + odleglosc + " lat świetlnych" +
                "\nPółkula: " + ((polnocna) ? "Północna" : "Południowa") +
                "\nTemeratura: " + temperatura.get() + " °C" +
                "\nMasa: " + masa.get()) + " masy Słońca" +
                "\nObserwowana wielkość gwiazdowa: " + obserowanaWielkoscGwiazdowa.get() +
                "\nAbsolutna wielkość gwiazdowa: " + absolutnaWielkoscGwiazdowa.get() +
                "\n----------------------------------";
    }
}
