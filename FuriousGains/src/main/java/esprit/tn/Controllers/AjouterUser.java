package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterUser {
    @FXML
    private TextField adresseTF;

    @FXML
    private TextField cinTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField num_telTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField prenomTF;

    private final UserService us = new UserService();

    @FXML
    void AjouterTF(ActionEvent event) {
        us.utilisateurCanBeAded(new User(Integer.parseInt(cinTF.getText()), nomTF.getText(), prenomTF.getText(),Integer.parseInt(num_telTF.getText()), adresseTF.getText(), emailTF.getText(),passwordTF.getText(),1));
    }
    @FXML
    void affichage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherUser.fxml"));
            cinTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
