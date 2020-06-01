package pl.pk99.gwiazdy.test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pk99.gwiazdy.Masa;
import pl.pk99.gwiazdy.ObserwowanaWielkoscGwiazdowa;
import pl.pk99.gwiazdy.Temperatura;
import pl.pk99.gwiazdy.model.Deklinacja;
import pl.pk99.gwiazdy.model.Gwiazda;
import pl.pk99.gwiazdy.model.Gwiazdozbior;
import pl.pk99.gwiazdy.model.Rektascensja;

public class GwiazdaTest {

    Gwiazda gwiazda;

    @BeforeEach
    public void dodajGwiazde () {
        Gwiazdozbior gwiazdozbior = new Gwiazdozbior("Test");
        ObserwowanaWielkoscGwiazdowa owg = new ObserwowanaWielkoscGwiazdowa(12.0);
        Deklinacja deklinacja = new Deklinacja(false, -30, 20, 10);
        Rektascensja rektascensja = new Rektascensja(12, 25, 10);
        Temperatura temperatura = new Temperatura(3500);
        Masa masa = new Masa(4.3);
        gwiazda = new Gwiazda("ABC1234", gwiazdozbior, owg, 35, false, deklinacja, rektascensja,
                temperatura, masa);
    }

    @Test
    public void powinien_dostacPrawidlowaNazwe () {
        Assert.assertEquals("ABC1234", gwiazda.getNazwa());
    }

    @Test
    public void powinien_utworzycPrawidlowaNazweKatalogowa_gdy_jestPierwszymElementemGwiazdozbioru () {
        gwiazda.utworzNazweKatalogowa();
        Assert.assertEquals("Alfa Test", gwiazda.getNazwaKatalogowa());
    }

    @Test
    public void powinien_utworzycPrawidlowaNazweKatalogowa_gdy_jestKolejnymElementemGwiazdozbioru () {
        gwiazda.getGwiazdozbior().dodajGwiazde();
        gwiazda.utworzNazweKatalogowa();
        Assert.assertEquals("Beta Test", gwiazda.getNazwaKatalogowa());
    }

    @Test
    public void powinien_dostacPrawidlowyGwiazdozbior () {
        Assert.assertEquals("Test", gwiazda.getGwiazdozbior().getNazwa());
    }

    @Test
    public void powinien_dostacPrawidlowaObserwowalnaWielkoscGwiazdowa () {
        Assert.assertEquals(12.0, gwiazda.getObserowanaWielkoscGwiazdowa().get(), 0.001);
    }

    @Test
    public void powinien_dostacPrawidlowaDeklinacje () {
        double[] deklinacje = {
                gwiazda.getDeklinacja().getStopnie(),
                gwiazda.getDeklinacja().getMinuty(),
                gwiazda.getDeklinacja().getSekundy()
        };
        double[] deklinacjeOczekiwane = {
                -30, 20, 10
        };
        Assert.assertArrayEquals(deklinacjeOczekiwane, deklinacje, 0.001);
    }

    @Test
    public void powinien_dostacPrawidlowaRektascensje () {
        int[] rektascensje = {
                gwiazda.getRektascensja().getGodziny(),
                gwiazda.getRektascensja().getMinuty(),
                gwiazda.getRektascensja().getSekundy()
        };
        int[] rektascensjeOczekiwane = {
                12, 25, 10
        };
        Assert.assertArrayEquals(rektascensjeOczekiwane, rektascensje);
    }

    @Test
    public void powinien_dostacPrawidlowaTemperature () {
        Assert.assertEquals(3500, gwiazda.getTemperatura().get(), 0.001);
    }

    @Test
    public void powinien_dostacPrawidlowaMase() {
        Assert.assertEquals(4.3, gwiazda.getMasa().get(), 0.001);
    }

    @Test
    public void powinien_dostacPrawidlowaOdleglosc() {
        Assert.assertEquals(35, gwiazda.getOdleglosc(), 0.001);
    }

    @Test
    public void powinien_dostacPrawidlowaPolkule() {
        Assert.assertFalse(gwiazda.isPolnocna());
    }

    @Test
    public void powinien_dostacPrawidlowaAbsolutnaWielkoscGwiazdowa() {
        Assert.assertEquals(9.28, gwiazda.getAbsolutnaWielkoscGwiazdowa(), 0.001);
    }

    @Test
    public void powinien_wyswietlicPrawidlowyToString() {
        String oczekiwanyToString = "----------------------------------" +
                "\nNazwa: ABC1234" +
                "\nNazwa katalogowa: null" +
                "\nDeklinacja: -30 stopni, 20 minut, 10.0 sekund" +
                "\nRektascensja: 12 stopni, 25 minut, 10 sekund" +
                "\nOdległość: 35.0 lat świetlnych" +
                "\nPółkula: Południowa" +
                "\nTemeratura: 3500.0 °C" +
                "\nMasa: 4.3 masy Słońca" +
                "\nObserwowana wielkość gwiazdowa: 12.0" +
                "\nAbsolutna wielkość gwiazdowa: 9.28" +
                "\n----------------------------------\n";
        Assert.assertEquals(oczekiwanyToString, gwiazda.toString());
    }
}
