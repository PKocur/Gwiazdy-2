package pl.pk99.gwiazdy.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SzukajGwiazdAwgController implements Controller {
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

    public SzukajGwiazdAwgController(SzukajGwiazdController szukajGwiazdController) {
        this.szukajGwiazdController = szukajGwiazdController;
        Okno okno = new Okno("szukajGwiazdFormularz.fxml", "Szukaj gwiazd", 200, 150);
        okno.wyswietl(this);
    }

    @FXML
    public void initialize() {
        txtTitle.setText("Szukaj w przedziale a.w.g.");
        txtField.setPromptText("Minimalna abs.wlk.gwiazd.");
        txtField2.setVisible(true);
        txtField2.setPromptText("Maksymalna abs.wlk.gwiazd.");
        hboxRB.managedProperty().bind(hboxRB.visibleProperty());
        Platform.runLater(() -> btnSzukaj.requestFocus());

        btnAnuluj.setOnAction(e -> zamknijOkno());
        btnSzukaj.setOnAction(e -> szukajGwiazdController.szukajAWG
                (Double.parseDouble(txtField.getText()), Double.parseDouble(txtField2.getText())));
    }

    @Override
    public void zamknijOkno () {
        Stage stage = (Stage) btnSzukaj.getScene().getWindow();
        stage.close();
    }
}
