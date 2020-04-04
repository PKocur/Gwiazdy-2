package pl.pk99.gwiazdy.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Deklinacje")
public class Deklinacja implements java.io.Serializable {
    private double sekundy;
    private int stopnie, minuty;
    @Id
    private int id;
    @OneToOne
    @MapsId
    private Gwiazda gwiazda;

    public int getId() {
        return id;
    }

    public Gwiazda getGwiazda() {
        return gwiazda;
    }

    public void setGwiazda(Gwiazda gwiazda) {
        this.gwiazda = gwiazda;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Deklinacja () {

    }

    public Deklinacja(boolean polnocna, int stopnie, int minuty, double sekundy) {
        if ((polnocna && stopnie <= 90 && stopnie >= 0) ||
                (!polnocna && stopnie >= -90 && stopnie <= 0) &&
                        (minuty <= 60 && minuty >= 0 && sekundy <= 60 && sekundy >= 0)) {
            this.stopnie = stopnie;
            this.minuty = minuty;
            this.sekundy = sekundy;
        } else {
            throw new IllegalArgumentException("Błędne wartości liczbowe");
        }
    }

    @Override
    public String toString() {
        return (stopnie + " stopni, " + minuty + " minut, "
                +  sekundy) + " sekund";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deklinacja)) return false;
        Deklinacja that = (Deklinacja) o;
        return Double.compare(that.sekundy, sekundy) == 0 &&
                stopnie == that.stopnie &&
                minuty == that.minuty &&
                id == that.id &&
                Objects.equals(gwiazda, that.gwiazda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sekundy, stopnie, minuty, id, gwiazda);
    }
}
