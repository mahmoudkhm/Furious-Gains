package esprit.tn.Controllers;

import esprit.tn.Models.Commande;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.CommandeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AfficherCommande {
    @FXML
    private ListView<Commande> ListCommande;

    @FXML
    private TextField SuppCommande;
    private final CommandeService cs=new CommandeService();

    @FXML
    void SupprimerCommande(ActionEvent event) {
        cs.supprimer(Integer.parseInt(SuppCommande.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherCommande.fxml"));
            SuppCommande.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void initialize()
    {
        List<Commande> commandes=null;
        commandes=cs.affichage();
        ObservableList<Commande > observableList= FXCollections.observableList(commandes) ;
        ListCommande.setItems(observableList);


    }

    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = ListCommande.getSelectionModel().getSelectedItem().getId_command();
        SuppCommande.setText(String.valueOf(selectedItem));

    }
}
