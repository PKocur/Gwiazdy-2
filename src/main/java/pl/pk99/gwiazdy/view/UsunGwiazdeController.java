package pl.pk99.gwiazdy.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.pk99.gwiazdy.*;
import pl.pk99.gwiazdy.model.Deklinacja;
import pl.pk99.gwiazdy.model.Gwiazda;
import pl.pk99.gwiazdy.model.Gwiazdozbior;
import pl.pk99.gwiazdy.model.Rektascensja;

public class UsunGwiazdeController implements Controller{
    @FXML
    private Button btnUsun;
    @FXML
    private Button btnAnuluj;
    @FXML
    private TextField txtNazwa;
    @FXML
    private Text txtInfo;

    @FXML
    public void initialize() {
        Platform.runLater(() -> btnUsun.requestFocus());

    }

    public void usunClicked() {
        boolean usunieta = GwiazdyManager.usunGwiazde(txtNazwa.getText());
        String tekst = usunieta ? "Gwiazda została pomyślnie usunięta" : "Nie znaleziono gwiazdy";
        txtInfo.setText(tekst);
    }

    public void anulujClicked () {
        zamknijOkno();
    }

    @Override
    public void zamknijOkno () {
        Stage stage = (Stage) btnUsun.getScene().getWindow();
        stage.close();
    }

}
