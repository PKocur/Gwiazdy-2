package pl.pk99.gwiazdy;

public class AbsolutnaWielkoscGwiazdowa implements java.io.Serializable {

    private double absolutnaWielkoscGwiazdowa;

    AbsolutnaWielkoscGwiazdowa(double obserwowanaWielkoscGwiazdowa, double odleglosc) {
        absolutnaWielkoscGwiazdowa =
                //Zaokrąglana jest do dwóch miejsc po przecinku
                Math.round((obserwowanaWielkoscGwiazdowa - 5 * Math.log10(odleglosc) + 5)
                        * 100.0) / 100.0;
    }

    double get() {
        return absolutnaWielkoscGwiazdowa;
    }
}
