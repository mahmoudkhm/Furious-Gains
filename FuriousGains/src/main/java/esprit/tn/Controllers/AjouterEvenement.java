package esprit.tn.Controllers;

import esprit.tn.Models.Evenement;
import esprit.tn.Services.EvenementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AjouterEvenement {
    @FXML
    private TextField Date_eventTF;

    @FXML
    private TextField DescriptionTF;

    @FXML
    private TextField Heure_eventTF;


    @FXML
    private TextField Lieu_eventTF;

    @FXML
    private TextField Nb_participantsTF;

    @FXML
    private TextField Prix_EventTF;

    @FXML
    private TextField nom_eventTF;
    private final EvenementService es=new EvenementService();

    @FXML
    void AjouterTF(ActionEvent event) {
        es.ajouter(new Evenement(nom_eventTF.getText(),Lieu_eventTF.getText(),Float.parseFloat(Prix_EventTF.getText()),Integer.parseInt(Nb_participantsTF.getText()),Date_eventTF.getText(),Heure_eventTF.getText(),DescriptionTF.getText()));

    }
    @FXML
    void afficherevenement(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
           nom_eventTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
