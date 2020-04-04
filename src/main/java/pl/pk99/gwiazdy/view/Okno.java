package pl.pk99.gwiazdy.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Okno {
    private String nazwaFXML;
    private String tytulOkna;
    private double wymiarX, wymiarY;
    private Object arg;
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public Okno(String nazwaFXML, String tytulOkna, double wymiarX, double wymiarY) {
        this.nazwaFXML = nazwaFXML;
        this.tytulOkna = tytulOkna;
        this.wymiarX = wymiarX;
        this.wymiarY = wymiarY;
    }

    public void wyswietl () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + nazwaFXML));
            Stage okno = stworzOkno();
            okno.setScene(new Scene(loader.load(), wymiarX, wymiarY));
            controller = loader.getController();
            okno.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wyswietl (Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + nazwaFXML));
            loader.setController(controller);
            Stage okno = stworzOkno();
            okno.setScene(new Scene(loader.load(), wymiarX, wymiarY));
            okno.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stage stworzOkno () {
        Stage okno = new Stage();
        okno.setTitle(tytulOkna);
        okno.setResizable(false);
        okno.initModality(Modality.APPLICATION_MODAL);
        return okno;
    }
}
