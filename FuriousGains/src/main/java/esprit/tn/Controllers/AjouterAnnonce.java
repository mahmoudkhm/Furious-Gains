package esprit.tn.Controllers;

import esprit.tn.Models.Annonces;
import esprit.tn.Services.AnnonceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterAnnonce {
    @FXML
    private TextField id_produitTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField noteTF;
    private final AnnonceService ass=new AnnonceService();
    @FXML
    void AfficherAnnonceTF(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAnnonce.fxml"));
            noteTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void AjouterAnnonceTF(ActionEvent event) {
        ass.ajouter(new Annonces(Integer.parseInt(id_userTF.getText()),Integer.parseInt(id_produitTF.getText()),Integer.parseInt(noteTF.getText())));

    }

}
