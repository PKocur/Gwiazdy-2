package pl.pk99.gwiazdy.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SzukajGwiazdOdlegloscController implements Controller {
    @FXML
    private Button btnSzukaj;
    @FXML
    private Button btnAnuluj;
    @FXML
    private TextField txtField;
    @FXML
    private Text txtTitle;
    @FXML
    private HBox hboxRB;

    SzukajGwiazdController szukajGwiazdController;

    public SzukajGwiazdOdlegloscController(SzukajGwiazdController szukajGwiazdController) {
        this.szukajGwiazdController = szukajGwiazdController;
        Okno okno = new Okno("szukajGwiazdFormularz.fxml", "Szukaj gwiazd", 200, 150);
        okno.wyswietl(this);
    }

    @FXML
    public void initialize() {
        txtTitle.setText("Szukaj w odległości x parseków od Ziemi");
        txtField.setPromptText("Odległość w parsekach");
        hboxRB.managedProperty().bind(hboxRB.visibleProperty());

        Platform.runLater(() -> btnSzukaj.requestFocus());

        btnAnuluj.setOnAction(e -> zamknijOkno());
        btnSzukaj.setOnAction(e -> szukajGwiazdController.szukajOdleglosc(Double.parseDouble(txtField.getText())));
    }

    @Override
    public void zamknijOkno () {
        Stage stage = (Stage) btnSzukaj.getScene().getWindow();
        stage.close();
    }
}
