package pl.pk99.gwiazdy.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SzukajGwiazdTemperaturaController implements Controller {
    @FXML
    private Button btnSzukaj;
    @FXML
    private Button btnAnuluj;
    @FXML
    private TextField txtField;
    @FXML
    private TextField txtField2;
    @FXML
    private Text txtTitle;
    @FXML
    private HBox hboxRB;

    SzukajGwiazdController szukajGwiazdController;

    public SzukajGwiazdTemperaturaController(SzukajGwiazdController szukajGwiazdController) {
        this.szukajGwiazdController = szukajGwiazdController;
        Okno okno = new Okno("szukajGwiazdFormularz.fxml", "Szukaj gwiazd", 200, 150);
        okno.wyswietl(this);
    }

    @FXML
    public void initialize() {
        txtTitle.setText("Szukaj w przedziale temperatury");
        txtField.setPromptText("Minimalna temperatura");
        txtField2.setVisible(true);
        txtField2.setPromptText("Maksymalna temperatura");
        Platform.runLater(() -> btnSzukaj.requestFocus());
        hboxRB.managedProperty().bind(hboxRB.visibleProperty());

        btnAnuluj.setOnAction(e -> zamknijOkno());
        btnSzukaj.setOnAction(e -> szukajGwiazdController.szukajTemperatura
                (Double.parseDouble(txtField.getText()), Double.parseDouble(txtField2.getText())));
    }

    @Override
    public void zamknijOkno () {
        Stage stage = (Stage) btnSzukaj.getScene().getWindow();
        stage.close();
    }
}
