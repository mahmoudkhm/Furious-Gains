package esprit.tn.Controllers;

import esprit.tn.Models.Commande;
import esprit.tn.Models.sms;
import esprit.tn.Services.CommandeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterCommande {

    @FXML
    private TextField IdClientTF;

    @FXML
    private TextField IdProduitTF;

    @FXML
    private TextField MontantTF;

    @FXML
    private TextField StatutCommandeTF;
    private final CommandeService cs=new CommandeService();
    @FXML
    void AfficherCommandeTF(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherCommande.fxml"));
            IdClientTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void AjouterCommandeTF(ActionEvent event) {
        cs.ajouter(new Commande(Integer.parseInt(IdClientTF.getText()),StatutCommandeTF.getText(),Float.parseFloat( MontantTF.getText()),Integer.parseInt(IdProduitTF.getText())));

    }
}
