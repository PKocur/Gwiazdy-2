package pl.pk99.gwiazdy;

import pl.pk99.gwiazdy.data.GwiazdyDatabaseManager;
import pl.pk99.gwiazdy.model.Deklinacja;
import pl.pk99.gwiazdy.model.Gwiazda;
import pl.pk99.gwiazdy.model.Gwiazdozbior;
import pl.pk99.gwiazdy.model.Rektascensja;

import java.io.File;
import java.util.*;

//Klasa zarządzająca gwiazdami, posiada listę gwiazd (pobraną z bazy) i wszelkie
//funkcjonalności, które na bazie możemy wykonywać (dodawanie, usuwanie oraz
//wyszukiwanie i wyświetlanie gwiazd)

public abstract class GwiazdyManager {
    static private List<Gwiazda> gwiazdy = new ArrayList<Gwiazda>();
    static private List<Gwiazdozbior> gwiazdozbiory = new ArrayList<Gwiazdozbior>();
    static private String sciezkaZapisuBazy = "gwiazdy.mv.db";

    static boolean maZapisaneDane() {
        return new File(sciezkaZapisuBazy).exists();
    }

    public static String wyswietlGwiazdy() {
        StringBuilder gwiazdyToString = new StringBuilder();
        for (Gwiazda g : gwiazdy) {
            gwiazdyToString.append(g.toString());
        }
        return gwiazdyToString.toString();
    }

    public static String wyswietlGwiazdy(Object[] wyszukaneGwiazdy) {
        StringBuilder gwiazdyToString = new StringBuilder();
        if (wyszukaneGwiazdy.length == 0) {
            System.out.println("Nie znaleziono gwiazd spełniających zadane kryteria");
        } else {
            for (Object g : wyszukaneGwiazdy) {
                gwiazdyToString.append(g.toString());
            }
        }
        return gwiazdyToString.toString();
    }


    public static Object[] znajdzGwiazdyWGwiazdozbiorze(String gwiazdozbior) {
        return gwiazdy.stream()
                .filter(g -> g.getGwiazdozbior().getNazwa().equalsIgnoreCase(gwiazdozbior)).toArray();
    }

    public static Object[] znajdzGwiazdyWOdleglosci(double pc) {
        return gwiazdy.stream()
                .filter(g -> g.getOdleglosc() * 0.3066 <= pc).toArray();
    }

    public static Object[] znajdzGwiazdyOTemperaturaturze(double tempMin, double tempMax) {
        return gwiazdy.stream()
                .filter(g -> g.getTemperatura().get() >= tempMin
                        && g.getTemperatura().get() <= tempMax).toArray();
    }

    public static Object[] znajdzGwiazdyOAbsolutnejWielkosciGwiazdowej(double wgMin, double wgMax) {
        return gwiazdy.stream()
                .filter(g -> g.getAbsolutnaWielkoscGwiazdowa() >= wgMin
                        && g.getAbsolutnaWielkoscGwiazdowa() <= wgMax).toArray();
    }

    public static Object[] znajdzGwiazdyNaPolkuli(boolean polnocna) {
        return gwiazdy.stream()
                .filter(g -> g.isPolnocna() == polnocna).toArray();
    }

    public static Object[] znajdzGwiazdyPotencjalneSupernowe() {
        return gwiazdy.stream()
                .filter(g -> g.getMasa().get() > 1.44).toArray();
    }

    /**
     * Metoda wczytuje gwiazdy i gwiazdozbiory z bazy do kolekcji (listy)
     */

    static void wczytajGwiazdy() {
        gwiazdy = GwiazdyDatabaseManager.wczytajGwiazdyZBazy();
        gwiazdozbiory = GwiazdyDatabaseManager.wczytajGwiazdozbioryZBazy();
    }


    /**
     * Metoda dodaje gwiazdę do listy, ustawia jej gwiazdozbiór i nazwę katalogową
     *
     * @param gwiazda obiekt gwiazdy, który ma zostać dodany do kolekcji
     */

    public static void dodajGwiazde(Gwiazda gwiazda) {
        gwiazda.setGwiazdozbior(znajdzGwiazdozbior(gwiazda));
        gwiazda.utworzNazweKatalogowa();
        gwiazdy.add(gwiazda);
        GwiazdyDatabaseManager.zapiszDoBazy(gwiazda);
    }

    /**
     * Metoda dodaje wiele gwiazd do listy, ustawia ich gwiazdozbiór i nazwę katalogową
     *
     * @param gwiazdy obiekty gwiazd, które mają zostać dodane do kolekcji
     */

    public static void dodajGwiazdy(Collection<? extends Gwiazda> gwiazdy) {
        for(Gwiazda g : gwiazdy) {
            g.setGwiazdozbior(znajdzGwiazdozbior(g));
            g.utworzNazweKatalogowa();
            GwiazdyManager.gwiazdy.add(g);
        }
        GwiazdyDatabaseManager.zapiszDoBazyWszystkie(gwiazdy);
    }


    /**
     * Metoda zwraca poprawny gwiazdozbiór do danej gwiazdy (tworzy nowy
     * gwiazdozbiór - jeśli ten jeszcze nie istnieje w bazie - bądź zwraca
     * już istniejący)
     *
     * @param gwiazda obiekt gwiazdy, dla którego szukamy poprawnego gwiazdozbioru
     */

    private static Gwiazdozbior znajdzGwiazdozbior (Gwiazda gwiazda) {
        for(Gwiazdozbior gwiazdozbior : gwiazdozbiory) {
            if(gwiazda.getGwiazdozbior().getNazwa().equals(gwiazdozbior.getNazwa())) {
                return gwiazdozbior;
            }
        }
        gwiazdozbiory.add(gwiazda.getGwiazdozbior());
        return gwiazda.getGwiazdozbior();
    }

    /**
     * Metoda usuwa gwiazdę z listy i uaktualnia nazwy katalogowe innych gwiazd z jej gwiazdozbioru
     *
     * @param nazwaKatalogowa nazwa katalogowa gwiazdy, która ma zostać usunięta
     */

    public static boolean usunGwiazde(String nazwaKatalogowa) {
        boolean usunieta = false;
        int gwiazdozbiorID = 0;
        for (Gwiazda gwiazda : gwiazdy) {
            if (gwiazda.getNazwaKatalogowa().equals(nazwaKatalogowa)) {
                gwiazdozbiorID = gwiazda.getGwiazdozbior().getId();
                gwiazda.getGwiazdozbior().resetujGwiazdy();
                gwiazdy.remove(gwiazda);
                GwiazdyDatabaseManager.usunZBazy(gwiazda);
                usunieta = true;
                break;
            }
        }
        utworzNazwyKatalogowe(gwiazdozbiorID);
        return usunieta;
    }

    /**
     * Metoda tworzy nazwy katalogowe dla wszystkich gwiazd w danym gwiazdozbiorze
     *
     * @param gwiazdozbiorID ID gwiazdozbioru, na podstawie którego tworzona
     *                     jest nazwa katalogowa gwiazdy
     */

    private static void utworzNazwyKatalogowe(int gwiazdozbiorID) {
        for (Gwiazda gwiazda : gwiazdy) {
            if(gwiazda.getGwiazdozbior().getId() == gwiazdozbiorID) {
                gwiazda.utworzNazweKatalogowa();
                GwiazdyDatabaseManager.uaktualnijWBazie(gwiazda);
            }
        }
    }

    /**
     * Metoda wstawia przykładowe gwiazdy do kolekcji
     */

    static void wstawPrzykladowe() {
        List<Gwiazda> gwiazdy = new ArrayList<Gwiazda>();
        List<Gwiazdozbior> gwiazdozbiory = new ArrayList<Gwiazdozbior>();

        ObserwowanaWielkoscGwiazdowa owg1 = new ObserwowanaWielkoscGwiazdowa(13.43);
        Deklinacja d1 = new Deklinacja(true, 25, 32, 55.23);
        Rektascensja r1 = new Rektascensja(22, 35, 12);
        Temperatura t1 = new Temperatura(6303);
        Masa m1 = new Masa(4.5);
        Gwiazdozbior gwiazdozbior = new Gwiazdozbior("Waga");
        gwiazdozbiory.add(gwiazdozbior);

        gwiazdy.add(new Gwiazda("PKK1999", gwiazdozbior, owg1, 26.09, true,
                d1, r1, t1, m1));

        ObserwowanaWielkoscGwiazdowa owg2 = new ObserwowanaWielkoscGwiazdowa(5.13);
        Deklinacja d2 = new Deklinacja(true, 29, 29, 35.4);
        Rektascensja r2 = new Rektascensja(15, 11, 54);
        Temperatura t2 = new Temperatura(3021);
        Masa m2 = new Masa(15.87);
        Gwiazdozbior gwiazdozbior2 = new Gwiazdozbior("Koziorożca");
        gwiazdozbiory.add(gwiazdozbior2);

        gwiazdy.add(new Gwiazda("JMR5386", gwiazdozbior2, owg2, 45.12, true,
                d2, r2, t2, m2));

        ObserwowanaWielkoscGwiazdowa owg3 = new ObserwowanaWielkoscGwiazdowa(-17.28);
        Deklinacja d3 = new Deklinacja(false, -43, 52, 12.84);
        Rektascensja r3 = new Rektascensja(4, 48, 14);
        Temperatura t3 = new Temperatura(12379);
        Masa m3 = new Masa(35.32);

        gwiazdy.add(new Gwiazda("MED4421", gwiazdozbior2, owg3, 32.87, false,
                d3, r3, t3, m3));

        ObserwowanaWielkoscGwiazdowa owg4 = new ObserwowanaWielkoscGwiazdowa(-23.54);
        Deklinacja d4 = new Deklinacja(false, -11, 33, 2.32);
        Rektascensja r4 = new Rektascensja(6, 13, 43);
        Temperatura t4 = new Temperatura(2453);
        Masa m4 = new Masa(42.98);

        gwiazdy.add(new Gwiazda("JOK3052", gwiazdozbior2, owg4, 28.47, false,
                d4, r4, t4, m4));

        ObserwowanaWielkoscGwiazdowa owg5 = new ObserwowanaWielkoscGwiazdowa(7.86);
        Deklinacja d5 = new Deklinacja(true, 32, 12, 9.43);
        Rektascensja r5 = new Rektascensja(1, 48, 15);
        Temperatura t5 = new Temperatura(10065);
        Masa m5 = new Masa(1.25);
        Gwiazdozbior gwiazdozbior3 = new Gwiazdozbior("Mleczny");
        gwiazdozbiory.add(gwiazdozbior3);

        gwiazdy.add( new Gwiazda("MUL9264", gwiazdozbior3, owg5, 68.23, true,
                d5, r5, t5, m5));

        ObserwowanaWielkoscGwiazdowa owg6 = new ObserwowanaWielkoscGwiazdowa(12.16);
        Deklinacja d6 = new Deklinacja(true, 43, 41, 35.18);
        Rektascensja r6 = new Rektascensja(17, 25, 19);
        Temperatura t6 = new Temperatura(8035);
        Masa m6 = new Masa(0.4);

        gwiazdy.add(new Gwiazda("KMD7903", gwiazdozbior3, owg6, 82.47, true,
                d6, r6, t6, m6));

        dodajGwiazdy(gwiazdy);
        GwiazdyManager.gwiazdozbiory.addAll(gwiazdozbiory);
    }

}
