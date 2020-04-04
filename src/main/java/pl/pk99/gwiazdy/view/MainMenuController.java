package pl.pk99.gwiazdy.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController implements Controller {
    @FXML
    private Button btnDodajGwiazde;

    public void dodajGwiazdeClicked () {
        Okno okno = new Okno("dodajGwiazde.fxml", "Dodaj gwiazdę", 400, 300);
        okno.wyswietl();
    }

    public void usunGwiazdeClicked () {
        Okno okno = new Okno("usunGwiazde.fxml", "Usuń gwiazdę", 200, 150);
        okno.wyswietl();
    }

    public void wyszukajGwiazdyClicked () {
        Okno okno = new Okno("szukajGwiazd.fxml", "Wyszukaj gwiazdy", 400, 350);
        okno.wyswietl();
    }

    public void wyswietlGwiazdyClicked () {
        Okno okno = new Okno("wyswietlGwiazdy.fxml", "Wyświetl gwiazdy", 700, 600);
        okno.wyswietl();
        ((WyswietlGwiazdyController)okno.getController()).wyswietlGwiazdy();
    }

    @Override
    public void zamknijOkno() {
        Stage stage = (Stage) btnDodajGwiazde.getScene().getWindow();
        stage.close();
    }
}
