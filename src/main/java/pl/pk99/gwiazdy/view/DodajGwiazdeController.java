package pl.pk99.gwiazdy.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.pk99.gwiazdy.*;
import pl.pk99.gwiazdy.model.Deklinacja;
import pl.pk99.gwiazdy.model.Gwiazda;
import pl.pk99.gwiazdy.model.Gwiazdozbior;
import pl.pk99.gwiazdy.model.Rektascensja;

public class DodajGwiazdeController implements Controller{
    @FXML
    private Button btnDodaj;
    @FXML
    private Button btnAnuluj;
    @FXML
    private TextField txtNazwa;
    @FXML
    private TextField txtGwiazdozbior;
    @FXML
    private TextField txtOWG;
    @FXML
    private TextField txtOdleglosc;
    @FXML
    private RadioButton rbPolnocna;
    @FXML
    private RadioButton rbPoludniowa;
    @FXML
    private TextField txtDekStopnie;
    @FXML
    private TextField txtDekMinuty;
    @FXML
    private TextField txtDekSekundy;
    @FXML
    private TextField txtRekGodziny;
    @FXML
    private TextField txtRekMinuty;
    @FXML
    private TextField txtRekSekundy;
    @FXML
    private TextField txtTemperatura;
    @FXML
    private TextField txtMasa;

    public void dodajClicked() {
        try {
            String nazwa = txtNazwa.getText();
            Gwiazdozbior gwiazdozbior = new Gwiazdozbior(txtGwiazdozbior.getText());

            ObserwowanaWielkoscGwiazdowa owg = new ObserwowanaWielkoscGwiazdowa(Double.parseDouble(txtOWG.getText()));
            double odleglosc = Double.parseDouble(txtOdleglosc.getText());
            boolean polnocna = rbPolnocna.isSelected();

            int stopnieD = Integer.parseInt(txtDekStopnie.getText());
            int minutyD = Integer.parseInt(txtDekMinuty.getText());
            double sekundyD = Double.parseDouble(txtDekSekundy.getText());
            Deklinacja deklinacja = new Deklinacja(polnocna, stopnieD, minutyD, sekundyD);

            int stopnieR = Integer.parseInt(txtRekGodziny.getText());
            int minutyR = Integer.parseInt(txtRekMinuty.getText());
            int sekundyR = Integer.parseInt(txtRekSekundy.getText());
            Rektascensja rektascensja = new Rektascensja(stopnieR, minutyR, sekundyR);

            Temperatura temperatura = new Temperatura(Double.parseDouble(txtTemperatura.getText()));
            Masa masa = new Masa(Double.parseDouble(txtMasa.getText()));

            Gwiazda g = new Gwiazda(nazwa, gwiazdozbior, owg, odleglosc, polnocna, deklinacja, rektascensja, temperatura, masa);

            GwiazdyManager.dodajGwiazde(g);
            zamknijOkno();
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }

    public void anulujClicked () {
        zamknijOkno();
    }

    public void zamknijOkno () {
        Stage stage = (Stage) btnDodaj.getScene().getWindow();
        stage.close();
    }

}
