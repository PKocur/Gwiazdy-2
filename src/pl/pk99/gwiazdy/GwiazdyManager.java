package pl.pk99.gwiazdy;

import java.io.*;
import java.util.*;

//Klasa zarządzająca gwiazdami, posiada bazę gwiazd (w formie listy) i wszelkie
//funkcjonalności, które na tej bazie możemy wykonywać (dodawanie, usuwanie oraz
//wyszukiwanie i wyświetlanie gwiazd)


abstract class GwiazdyManager {

    enum AlfabetGrecki {
        Alfa, Beta, Gamma, Delta, Epsilon, Dzeta, Eta,
        Theta, Jota, Kappa, Lambda, My, Ny, Ksi, Omikron,
        Pi, Rho, Sigma, Tau, Ipsylon, Phi, Chi, Psi, Omega
    }

    static private List<Gwiazda> gwiazdy = new ArrayList<Gwiazda>();
    static private Map<String, Integer> gwiazdozbiory = new HashMap<String, Integer>();
    static private String sciezkaZapisuPliku = "data.bin";

    static boolean maZapisaneDane() {
        return new File(sciezkaZapisuPliku).exists();
    }

    static void wyswietlGwiazdy() {
        for (Gwiazda g : gwiazdy) {
            System.out.println(g.toString());
        }
    }

    static void znajdzGwiazdyWGwiazdozbiorze(String gwiazdozbior) {
        Object[] wyszukaneGwiazdy = gwiazdy.stream()
                .filter(g -> g.getGwiazdozbior().equalsIgnoreCase(gwiazdozbior)).toArray();

        wyswietlZnalezioneGwiazdy(wyszukaneGwiazdy);
    }

    static void znajdzGwiazdyWOdleglosci(double pc) {
        Object[] wyszukaneGwiazdy = gwiazdy.stream()
                .filter(g -> g.getOdleglosc() * 0.3066 <= pc).toArray();

        wyswietlZnalezioneGwiazdy(wyszukaneGwiazdy);
    }

    static void znajdzGwiazdyOTemperaturaturze(double tempMin, double tempMax) {
        Object[] wyszukaneGwiazdy = gwiazdy.stream()
                .filter(g -> g.getTemperatura().get() >= tempMin
                        && g.getTemperatura().get() <= tempMax).toArray();

        wyswietlZnalezioneGwiazdy(wyszukaneGwiazdy);
    }

    static void znajdzGwiazdyOAbsolutnejWielkosciGwiazdowej(double wgMin, double wgMax) {
        Object[] wyszukaneGwiazdy = gwiazdy.stream()
                .filter(g -> g.getAbsolutnaWielkoscGwiazdowa().get() >= wgMin
                        && g.getAbsolutnaWielkoscGwiazdowa().get() <= wgMax).toArray();

        wyswietlZnalezioneGwiazdy(wyszukaneGwiazdy);
    }

    static void znajdzGwiazdyNaPolkuli(boolean polnocna) {
        Object[] wyszukaneGwiazdy = gwiazdy.stream()
                .filter(g -> g.isPolnocna() == polnocna).toArray();

        wyswietlZnalezioneGwiazdy(wyszukaneGwiazdy);
    }

    static void znajdzGwiazdyPotencjalneSupernowe() {
        Object[] wyszukaneGwiazdy = gwiazdy.stream()
                .filter(g -> g.getMasa().get() > 1.44).toArray();

        wyswietlZnalezioneGwiazdy(wyszukaneGwiazdy);
    }

    /**
     * Metoda wyświetla znalezione gwiazdy
     *
     * @param wyszukaneGwiazdy tablica obiektów, które mają zostać wyświetlone
     *                         przez metodę
     */

    private static void wyswietlZnalezioneGwiazdy(Object[] wyszukaneGwiazdy) {
        if (wyszukaneGwiazdy.length == 0) {
            System.out.println("Nie znaleziono gwiazd spełniających zadane kryteria");
        } else {
            for (Object g : wyszukaneGwiazdy) {
                System.out.println(g.toString());
            }
        }

    }

    /**
     * Metoda zapisuje gwiazdy z kolekcji (listy) do pliku
     */

    static void zapiszGwiazdy() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(sciezkaZapisuPliku))) {
            outputStream.writeObject(gwiazdy);
            outputStream.writeObject(gwiazdozbiory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoda wczytuje gwiazdy z pliku do kolekcji (listy)
     */

    static void wczytajGwiazdy() {
        Object wczytaneGwiazdy = null;
        Object wczytaneGwiazdozbiory = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(sciezkaZapisuPliku))) {
            wczytaneGwiazdy = inputStream.readObject();
            wczytaneGwiazdozbiory = inputStream.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        gwiazdy = (List<Gwiazda>) wczytaneGwiazdy;
        gwiazdozbiory = (Map<String, Integer>) wczytaneGwiazdozbiory;
    }


    /**
     * Metoda dodaje gwiazdę do listy i ustawia jej nazwę katalogową
     *
     * @param gwiazda obiekt gwiazdy, który ma zostać dodany do kolekcji
     */

    static void dodajGwiazde(Gwiazda gwiazda) {
        gwiazdy.add(gwiazda);
        utworzNazweKatalogowa(gwiazda);
    }

    /**
     * Metoda usuwa gwiazdę z listy i uaktualnia nazwy katalogowe innych gwiazd z jej gwiazdozbioru
     *
     * @param nazwaKatalogowa nazwa katalogowa gwiazdy, która ma zostać usunięta
     */

    static boolean usunGwiazde(String nazwaKatalogowa) {
        boolean usunieta = false;
        String gwiazdozbior = "";
        for (Gwiazda gwiazda : gwiazdy) {
            if (gwiazda.getNazwaKatalogowa().equals(nazwaKatalogowa)) {
                gwiazdozbior = gwiazda.getGwiazdozbior();
                gwiazdy.remove(gwiazda);
                gwiazdozbiory.put(gwiazdozbior, 0);
                usunieta = true;
                break;
            }
        }
        utworzNazwyKatalogowe(gwiazdozbior);
        return usunieta;
    }

    /**
     * Metoda tworzy nazwy katalogowe dla wszystkich gwiazd w danym gwiazdozbiorze
     *
     * @param gwiazdozbior nazwa gwiazdozbioru, na podstawie którego tworzona
     *                     jest nazwa katalogowa gwiazdy
     */

    private static void utworzNazwyKatalogowe(String gwiazdozbior) {
        for (Gwiazda gwiazda : gwiazdy) {
            if (gwiazda.getGwiazdozbior().equalsIgnoreCase(gwiazdozbior)) {
                utworzNazweKatalogowa(gwiazda);
            }
        }
    }

    /**
     * Metoda tworzy nazwę katalogową dla danej gwiazdy
     *
     * @param gwiazda obiekt gwiazdy, do której tworzona jest nazwa katalogowa
     */

    private static void utworzNazweKatalogowa(Gwiazda gwiazda) {
        Integer value;
        value = gwiazdozbiory.getOrDefault(gwiazda.getGwiazdozbior(), 0);
        gwiazdozbiory.put(gwiazda.getGwiazdozbior(), ++value);
        String nazwaKatalogowa = AlfabetGrecki.values()[value - 1] + " " + gwiazda.getGwiazdozbior();
        gwiazda.setNazwaKatalogowa(nazwaKatalogowa);
    }

    /**
     * Metoda wstawia przykładowe gwiazdy do kolekcji
     */

    static void wstawPrzykladowe() {
        Gwiazda[] gwiazdy = new Gwiazda[6];

        ObserwowanaWielkoscGwiazdowa owg1 = new ObserwowanaWielkoscGwiazdowa(13.43);
        Deklinacja d1 = new Deklinacja(true, 25, 32, 55.23);
        Rektascensja r1 = new Rektascensja(22, 35, 12);
        Temperatura t1 = new Temperatura(6303);
        Masa m1 = new Masa(4.5);
        gwiazdy[0] = new Gwiazda("PKK1999", "Waga", owg1, 26.09, true,
                d1, r1, t1, m1);

        ObserwowanaWielkoscGwiazdowa owg2 = new ObserwowanaWielkoscGwiazdowa(5.13);
        Deklinacja d2 = new Deklinacja(true, 29, 29, 35.4);
        Rektascensja r2 = new Rektascensja(15, 11, 54);
        Temperatura t2 = new Temperatura(3021);
        Masa m2 = new Masa(15.87);
        gwiazdy[1] = new Gwiazda("JMR5386", "Koziorożca", owg2, 45.12, true,
                d2, r2, t2, m2);

        ObserwowanaWielkoscGwiazdowa owg3 = new ObserwowanaWielkoscGwiazdowa(-17.28);
        Deklinacja d3 = new Deklinacja(false, -43, 52, 12.84);
        Rektascensja r3 = new Rektascensja(4, 48, 14);
        Temperatura t3 = new Temperatura(12379);
        Masa m3 = new Masa(35.32);

        gwiazdy[2] = new Gwiazda("MED4421", "Koziorożca", owg3, 32.87, false,
                d3, r3, t3, m3);

        ObserwowanaWielkoscGwiazdowa owg4 = new ObserwowanaWielkoscGwiazdowa(-23.54);
        Deklinacja d4 = new Deklinacja(false, -11, 33, 2.32);
        Rektascensja r4 = new Rektascensja(6, 13, 43);
        Temperatura t4 = new Temperatura(2453);
        Masa m4 = new Masa(42.98);

        gwiazdy[3] = new Gwiazda("JOK3052", "Koziorożca", owg4, 28.47, false,
                d4, r4, t4, m4);

        ObserwowanaWielkoscGwiazdowa owg5 = new ObserwowanaWielkoscGwiazdowa(7.86);
        Deklinacja d5 = new Deklinacja(true, 32, 12, 9.43);
        Rektascensja r5 = new Rektascensja(1, 48, 15);
        Temperatura t5 = new Temperatura(10065);
        Masa m5 = new Masa(1.25);

        gwiazdy[4] = new Gwiazda("MUL9264", "Mleczny", owg5, 68.23, true,
                d5, r5, t5, m5);

        ObserwowanaWielkoscGwiazdowa owg6 = new ObserwowanaWielkoscGwiazdowa(12.16);
        Deklinacja d6 = new Deklinacja(true, 43, 41, 35.18);
        Rektascensja r6 = new Rektascensja(17, 25, 19);
        Temperatura t6 = new Temperatura(8035);
        Masa m6 = new Masa(0.4);

        gwiazdy[5] = new Gwiazda("KMD7903", "Mleczny", owg6, 82.47, true,
                d6, r6, t6, m6);

        for(int x = 0; x < 6; x++) {
            GwiazdyManager.dodajGwiazde(gwiazdy[x]);
        }
    }

}
