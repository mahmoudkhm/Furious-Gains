package esprit.tn.Controllers;

import esprit.tn.Models.Annonce;
import esprit.tn.Services.AnnonceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterAnnonce {

    @FXML
    private TextArea desTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField tittreATF;
    private final AnnonceService ass=new AnnonceService();

    @FXML
    void AfficherAnnonceTF(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAnnonce.fxml"));
            desTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AjouterAnnonceTF(ActionEvent event) {
        ass.ajouter(new Annonce(tittreATF.getText(),desTF.getText(),Integer.parseInt(id_userTF.getText())));


    }
}
