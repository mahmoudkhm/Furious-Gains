package esprit.tn.Controllers;

import esprit.tn.Models.Annonces;
import esprit.tn.Models.Avis;
import esprit.tn.Services.AnnonceService;
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

public class AfficherAnnonce {
    @FXML
    private ListView<Annonces> listAnnonce;

    @FXML
    private TextField suppAnnonce;
    private final AnnonceService ass=new AnnonceService();



    @FXML
    void supprimerAnnonce(ActionEvent event) {

       ass.supprimer(Integer.parseInt(suppAnnonce.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAnnonce.fxml"));
            suppAnnonce.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listAnnonce.getSelectionModel().getSelectedItem().getId_annonces();
        suppAnnonce.setText(String.valueOf(selectedItem));

    }
    @FXML void initialize(){
        List<Annonces> annonces=null;
        annonces=ass.affichage();
        ObservableList<Annonces> observableList= FXCollections.observableList(annonces);
        listAnnonce.setItems(observableList);

    }

}
