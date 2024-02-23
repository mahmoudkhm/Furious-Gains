package esprit.tn.Controllers;

import esprit.tn.Models.Avis;
import esprit.tn.Services.AvisService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AjouterAvis {
    @FXML
    private TextArea Description_avisTF;

    @FXML
    private TextField Id_userTF;

    @FXML
    private TextField Titre_avisTF;
private final AvisService as=new AvisService();
    @FXML
    void AjouterTF(ActionEvent event) {
as.ajouter(new Avis(Titre_avisTF.getText(),Description_avisTF.getText(),Integer.parseInt(Id_userTF.getText())));

    }
    @FXML
    void afficher_avis(ActionEvent event) {
         try {
             Parent root=FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            Titre_avisTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
