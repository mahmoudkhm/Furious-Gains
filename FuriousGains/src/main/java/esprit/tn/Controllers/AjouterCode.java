package esprit.tn.Controllers;

import esprit.tn.Models.CodePromo;
import esprit.tn.Models.User;
import esprit.tn.Services.CodeService;
import esprit.tn.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterCode {

    @FXML
    private TextField codeTF;

    @FXML
    private TextField montantTF;

    @FXML
    private TextField statut;

    @FXML
    private TextField utilisationTF;
    private final CodeService cs = new CodeService();

    @FXML
    void affichageCodeTF(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherCode.fxml"));
            codeTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void AjouterCodeTF(ActionEvent event) {
        cs.ajouter(new CodePromo(Integer.parseInt(codeTF.getText()),Integer.parseInt(montantTF.getText()),statut.getText(),Integer.parseInt(utilisationTF.getText())));
    }

}
