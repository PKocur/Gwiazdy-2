package pl.pk99.gwiazdy;

public class Deklinacja implements java.io.Serializable {
    private double[] deklinacja = new double[3];

    Deklinacja(boolean polnocna, int stopnie, int minuty, double sekundy) {
        if ((polnocna && stopnie <= 90 && stopnie >= 0) ||
                (!polnocna && stopnie >= -90 && stopnie <= 0) &&
                        (minuty <= 60 && minuty >= 0 && sekundy <= 60 && sekundy >= 0)) {
            deklinacja[0] = stopnie;
            deklinacja[1] = minuty;
            deklinacja[2] = sekundy;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }
    }

    public double[] get() {
        return deklinacja;
    }

    @Override
    public String toString() {
        return (deklinacja[0] + " stopni, " + deklinacja[1] + " minut, "
                + deklinacja[2]) + " sekund";
    }
}
