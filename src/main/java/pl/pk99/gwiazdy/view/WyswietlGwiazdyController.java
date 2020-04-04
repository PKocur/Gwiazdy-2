package pl.pk99.gwiazdy.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.pk99.gwiazdy.GwiazdyManager;

public class WyswietlGwiazdyController implements Controller {

    @FXML
    private Button btnWstecz;
    @FXML
    private Text text;

    @FXML
    public void initialize() {

    }

    public void wsteczClicked () {
        zamknijOkno();
    }

    public void wyswietlGwiazdy () {
        text.setText(GwiazdyManager.wyswietlGwiazdy());
    }

    public void wyswietlGwiazdy (Object[] wyszukaneGwiazdy) {
        text.setText(GwiazdyManager.wyswietlGwiazdy(wyszukaneGwiazdy));
    }

    @Override
    public void zamknijOkno () {
        Stage stage = (Stage) btnWstecz.getScene().getWindow();
        stage.close();
    }
}
