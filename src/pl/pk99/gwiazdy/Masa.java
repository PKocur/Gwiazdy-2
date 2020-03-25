package pl.pk99.gwiazdy;

class Masa implements java.io.Serializable {
    private double masa;

    Masa(double masa) {
        if(masa >= 0.1 && masa <= 50) {
            this.masa = masa;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }

    }

    double get() {
        return masa;
    }
}
