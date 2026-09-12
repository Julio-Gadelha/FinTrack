package dio.fintrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FinApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FinApp.class.getResource("tela-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        scene.getStylesheets().add(FinApp.class.getResource("style.css").toExternalForm());
        stage.setTitle("FinTrack - Controle de Finanças Pessoais");
        stage.setScene(scene);

        stage.show();
    }
}