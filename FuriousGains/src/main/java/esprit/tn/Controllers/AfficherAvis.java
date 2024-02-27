package esprit.tn.Controllers;
import esprit.tn.Models.Avis;
import esprit.tn.Services.AnnonceService;
import esprit.tn.Services.AvisService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
public class AfficherAvis {
    @FXML
    private ListView<Avis> listeAvis;

    @FXML
    private TextField suppAvis;
    private final AvisService as=new AvisService();


    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listeAvis.getSelectionModel().getSelectedItem().getId_avis();
        suppAvis.setText(String.valueOf(selectedItem));
    }

    @FXML
    void supprimerAvis(ActionEvent event) {
        as.supprimer(Integer.parseInt(suppAvis.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            suppAvis.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML void initialize(){
        List<Avis> avis=as.affichage();
        ObservableList<Avis> observableList= FXCollections.observableList(avis);
        listeAvis.setItems(observableList);

    }
}