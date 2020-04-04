package pl.pk99.gwiazdy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Klasa startowa, uruchamia interfejs i wczytuje bazę danych
public class Main extends Application {

    public static void main(String[] args) {
        initialize();
        launch(args);
    }

    public static void initialize() {
        if (GwiazdyManager.maZapisaneDane())
            GwiazdyManager.wczytajGwiazdy();
        else
            GwiazdyManager.wstawPrzykladowe();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainMenu.fxml"));
        primaryStage.setTitle("Gwiazdy");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
