package esprit.tn.Controllers;

import esprit.tn.Models.Avis;
import esprit.tn.Services.AvisService;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterAvis {
    @FXML
    private TextField id_produitTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField noteTF;
    private final AvisService as=new AvisService();


    @FXML
    void AfficherAvisTF(ActionEvent event) {

        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            noteTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AjouterAvisTF(ActionEvent event) {
        as.ajouter(new Avis(Integer.parseInt(id_userTF.getText()),Integer.parseInt(id_produitTF.getText()),Integer.parseInt(noteTF.getText())));


    }

}
