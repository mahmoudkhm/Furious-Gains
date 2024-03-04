package esprit.tn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("FuriousGains");
            primaryStage.setScene(scene);
           // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/img/logo1.png")));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/img/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();

    }

}
