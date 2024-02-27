package esprit.tn.Controllers;

import esprit.tn.Models.Livraison;
import esprit.tn.Services.LivraisonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AjouterLivraison {
    @FXML
    private TextField AdresseLivraisonTF;

    @FXML
    private TextField DateLivraisonTF;

    @FXML
    private TextField IdClientTF;

    @FXML
    private TextField IdCommandeTF;

    @FXML
    private TextField ModeLivraisonTF;

    @FXML
    private TextField MontantTF;

    @FXML
    private TextField StatutLivraisonTF;


    private final LivraisonService ls =new LivraisonService();
    @FXML
    void AjouterTF(ActionEvent event) {
        ls.ajouter(new Livraison(Integer.parseInt(IdCommandeTF.getText()),DateLivraisonTF.getText(),StatutLivraisonTF.getText(), AdresseLivraisonTF.getText(), Float.parseFloat( MontantTF.getText()), ModeLivraisonTF.getText(),Integer.parseInt(IdClientTF.getText())));

    }
    @FXML
    void afficherlivraison(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherLivraison.fxml"));
            IdCommandeTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
