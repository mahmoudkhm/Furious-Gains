package esprit.tn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AjouterAvis.fxml"));
        try {
            Parent route=loader.load();
            Scene scene=new Scene(route);
            primaryStage.setTitle("Ajouter Annonce");
            primaryStage.setScene(scene);
           primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
