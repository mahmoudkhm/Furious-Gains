package esprit.tn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProduit.fxml"));
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionP.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Furious Gains Desktop");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
