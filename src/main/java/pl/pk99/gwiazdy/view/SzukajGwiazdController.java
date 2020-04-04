package pl.pk99.gwiazdy.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.pk99.gwiazdy.GwiazdyManager;

public class SzukajGwiazdController implements Controller {
    @FXML
    private Button btnWyjdz;

    public void gwiazdozbiorClicked() {
        SzukajGwiazdGwiazdozbiorController gwiazdozbiorController =
                new SzukajGwiazdGwiazdozbiorController(this);
    }

    public void odlegloscClicked() {
        SzukajGwiazdOdlegloscController odlegloscController =
                new SzukajGwiazdOdlegloscController(this);
    }

    public void temperaturaClicked() {
        SzukajGwiazdTemperaturaController temperaturaController =
                new SzukajGwiazdTemperaturaController(this);
    }

    public void awgClicked() {
        SzukajGwiazdAwgController awgController =
                new SzukajGwiazdAwgController(this);
    }

    public void polkulaClicked() {
        SzukajGwiazdPolkulaController polkulaController =
                new SzukajGwiazdPolkulaController(this);
    }

    public void supernoweClicked() {
        szukajSupernowe();
    }

    public void szukajGwiazdozbior (String gwiazdozbior) {
        wyswietlWyszukaneGwiazdy(GwiazdyManager.znajdzGwiazdyWGwiazdozbiorze(gwiazdozbior));
    }

    public void szukajOdleglosc (double pc) {
        wyswietlWyszukaneGwiazdy(GwiazdyManager.znajdzGwiazdyWOdleglosci(pc));
    }

    public void szukajTemperatura (double tempMin, double tempMax) {
        wyswietlWyszukaneGwiazdy(GwiazdyManager.znajdzGwiazdyOTemperaturaturze(tempMin, tempMax));
    }

    public void szukajAWG (double wgMin, double wgMax) {
        wyswietlWyszukaneGwiazdy(GwiazdyManager.znajdzGwiazdyOAbsolutnejWielkosciGwiazdowej(wgMin, wgMax));
    }

    public void szukajPolkula (boolean polnocna) {
        wyswietlWyszukaneGwiazdy(GwiazdyManager.znajdzGwiazdyNaPolkuli(polnocna));
    }

    public void szukajSupernowe () {
        wyswietlWyszukaneGwiazdy(GwiazdyManager.znajdzGwiazdyPotencjalneSupernowe());
    }

    private void wyswietlWyszukaneGwiazdy(Object[] gwiazdy) {
        Okno okno = new Okno("wyswietlGwiazdy.fxml", "Wyświetl gwiazdy", 700, 600);
        okno.wyswietl();
        WyswietlGwiazdyController controller = (WyswietlGwiazdyController)okno.getController();
        controller.wyswietlGwiazdy(gwiazdy);
    }

    public void wyjdzClicked () {
        zamknijOkno();
    }

    public void zamknijOkno () {
        Stage stage = (Stage) btnWyjdz.getScene().getWindow();
        stage.close();
    }

}
