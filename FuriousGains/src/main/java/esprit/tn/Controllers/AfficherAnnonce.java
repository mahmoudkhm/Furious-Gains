package esprit.tn.Controllers;

import esprit.tn.Models.Annonce;
import esprit.tn.Services.AnnonceService;
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

public class AfficherAnnonce {

    @FXML
    private ListView<Annonce> listeAnnonce;

    @FXML
    private TextField suppAnnonce;
    private final AnnonceService ass=new AnnonceService();


    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listeAnnonce.getSelectionModel().getSelectedItem().getId_annonce();
        suppAnnonce.setText(String.valueOf(selectedItem));
    }

    @FXML
    void supprimerAnnonce(ActionEvent event) {
        ass.supprimer(Integer.parseInt(suppAnnonce.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            suppAnnonce.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML void initialize(){
        List<Annonce> annonces=null;
        annonces=ass.affichage();
        ObservableList<Annonce> observableList= FXCollections.observableList(annonces);
        listeAnnonce.setItems(observableList);

    }
}
