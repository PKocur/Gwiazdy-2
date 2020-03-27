package pl.pk99.gwiazdy;

public class Masa implements java.io.Serializable {
    private double masa;

    public Masa(double masa) {
        if(masa >= 0.1 && masa <= 50) {
            this.masa = masa;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }

    }

    public double get() {
        return masa;
    }
}
