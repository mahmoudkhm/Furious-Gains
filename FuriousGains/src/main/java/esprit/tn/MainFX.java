package esprit.tn;

import esprit.tn.Controllers.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class MainFX extends Application implements Initializable {
    int cinn;
    Preferences preferences;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        if (cinn==0){

        Parent root = FXMLLoader.load(getClass().getResource("/Hbox.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/img/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }
        else {
            Login.setCin(cinn);
            Parent root = FXMLLoader.load(getClass().getResource("/Front.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/img/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menu");
            primaryStage.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         preferences = Preferences.userRoot().node(this.getClass().getName());
        // Load saved username and password if available
        String savedUsername = preferences.get("username", "");
        String cin = preferences.get("cin", "");
         cinn=Integer.parseInt(cin);
    }
}
