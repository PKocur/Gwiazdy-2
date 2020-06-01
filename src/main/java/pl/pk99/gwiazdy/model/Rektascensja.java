package pl.pk99.gwiazdy.model;

import pl.pk99.gwiazdy.model.Gwiazda;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Rektascensje")
public class Rektascensja implements java.io.Serializable {
    private int godziny, minuty, sekundy;

    @Id
    private int id;

    @OneToOne
    @MapsId
    private Gwiazda gwiazda;

    public int getGodziny() {
        return godziny;
    }

    public int getMinuty() {
        return minuty;
    }

    public int getSekundy() {
        return sekundy;
    }

    public int getId() {
        return id;
    }

    public Gwiazda getGwiazda() {
        return gwiazda;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGwiazda(Gwiazda gwiazda) {
        this.gwiazda = gwiazda;
    }

    public Rektascensja () {

    }

    public Rektascensja(int godziny, int minuty, int sekundy) {
        if (godziny <= 24 && godziny >= 1 && minuty <= 60 && minuty >= 0
                && sekundy <= 60 && sekundy >= 0) {
            this.godziny = godziny;
            this.minuty  = minuty;
            this.sekundy  = sekundy;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }
    }

    @Override
    public String toString() {
        return (godziny + " stopni, " + minuty + " minut, "
                + sekundy) + " sekund";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rektascensja)) return false;
        Rektascensja that = (Rektascensja) o;
        return godziny == that.godziny &&
                minuty == that.minuty &&
                sekundy == that.sekundy &&
                id == that.id &&
                Objects.equals(gwiazda, that.gwiazda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(godziny, minuty, sekundy, id, gwiazda);
    }
}
