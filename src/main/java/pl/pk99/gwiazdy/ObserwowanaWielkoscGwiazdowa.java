package pl.pk99.gwiazdy;

import javax.persistence.Entity;
import javax.persistence.Table;

public class ObserwowanaWielkoscGwiazdowa implements java.io.Serializable {

    private double obserwowanaWielkoscGwiazdowa;

    public ObserwowanaWielkoscGwiazdowa(double obserwowanaWielkoscGwiazdowa) {
        if(obserwowanaWielkoscGwiazdowa >= -26.74 && obserwowanaWielkoscGwiazdowa <= 15) {
            this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }
    }

    public double get() {
        return obserwowanaWielkoscGwiazdowa;
    }
}
