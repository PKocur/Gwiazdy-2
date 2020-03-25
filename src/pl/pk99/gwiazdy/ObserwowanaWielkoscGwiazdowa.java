package pl.pk99.gwiazdy;

public class ObserwowanaWielkoscGwiazdowa implements java.io.Serializable {

    private double obserwowanaWielkoscGwiazdowa;

    ObserwowanaWielkoscGwiazdowa(double obserwowanaWielkoscGwiazdowa) {
        if(obserwowanaWielkoscGwiazdowa >= -26.74 && obserwowanaWielkoscGwiazdowa <= 15) {
            this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }
    }

    double get() {
        return obserwowanaWielkoscGwiazdowa;
    }
}
