package pl.pk99.gwiazdy;

public class Temperatura implements java.io.Serializable {
    private double temperatura;

    public Temperatura(double temperatura) {
        if(temperatura >= 2000) {
            this.temperatura = temperatura;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }

    }

    public double get() {
        return temperatura;
    }
}
