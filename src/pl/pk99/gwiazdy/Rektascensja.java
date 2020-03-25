package pl.pk99.gwiazdy;

public class Rektascensja implements java.io.Serializable {
    private int[] rektascensja = new int[3];

    Rektascensja (int godziny, int minuty, int sekundy) {
        if (godziny <= 24 && godziny >= 1 && minuty <= 60 && minuty >= 0
                && sekundy <= 60 && sekundy >= 0) {
            rektascensja[0] = godziny;
            rektascensja[1] = minuty;
            rektascensja[2] = sekundy;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }
    }

    public int[] get() {
        return rektascensja;
    }

    @Override
    public String toString() {
        return (rektascensja[0] + " stopni, " + rektascensja[1] + " minut, "
                + rektascensja[2]) + " sekund";
    }
}
