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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private TextField rechercher;
    @FXML
    void calculerTotalAvis(ActionEvent event) {
        ObservableList<Avis> avisList = listeAvis.getItems();
        int nombreAvis = avisList.size();

       // System.out.println("Nombre d'avis : " + nombreAvis);
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nombre d'avis : " + nombreAvis);
        alert.setContentText("Nombre d'avis : " + nombreAvis);
        alert.showAndWait();
    }


        @FXML
    void rechercherpar(KeyEvent event) {
        String noteText = rechercher.getText();
        if (!noteText.isEmpty()) {
            List<Avis> avis=as.recherche(noteText);
            if (avis != null) {
                ObservableList<Avis> observableList = FXCollections.observableArrayList(avis);
                listeAvis.setItems(observableList);
            } else {
                listeAvis.setItems(FXCollections.emptyObservableList());
            }
        } else {
            List<Avis> users1= null;
            users1 = as.affichage();
            ObservableList<Avis> observableList = FXCollections.observableList(users1);
            listeAvis.setItems(observableList);}

    }
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
    @FXML
    public void calculerTotalAvis() {
        ObservableList<Avis> avisList = listeAvis.getItems();
        int totalAvis = 0;

        for (Avis avis : avisList) {
            totalAvis += avis.getNote();


        }

        System.out.println("Total des avis : " + totalAvis);
    }

}