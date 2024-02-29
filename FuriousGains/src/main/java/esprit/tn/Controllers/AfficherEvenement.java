package esprit.tn.Controllers;

import esprit.tn.Models.Evenement;
import esprit.tn.Services.EvenementService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AfficherEvenement {

    @FXML
    private ListView<Evenement> ListEvenement;

    @FXML
    private TextField id_supp;
    @FXML
    private TextField rechercher;


    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = ListEvenement.getSelectionModel().getSelectedItem().getId_event();
        id_supp.setText(String.valueOf(selectedItem));

    }
    private final EvenementService es =new EvenementService();
    @FXML
    void supprimer(ActionEvent event) {
        es.supprimer(Integer.parseInt(id_supp.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
            id_supp.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void initialize()
    {
        List<Evenement> evenements=null;
        evenements=es.affichage();
        ObservableList<Evenement >observableList= FXCollections.observableList(evenements) ;
        ListEvenement.setItems(observableList);





    }
    @FXML
    void rechercherE(KeyEvent event) {
        String cinText = rechercher.getText();
        if (!cinText.isEmpty()) {
            int cin = Integer.parseInt(cinText);
            Evenement evenement = es.getOneByCin(cin);
            if (evenement != null) {
                ObservableList<Evenement> observableList = FXCollections.observableArrayList(evenement);
                ListEvenement.setItems(observableList);
            } else {
                ListEvenement.setItems(FXCollections.emptyObservableList());
            }
        } else {
            List<Evenement> users1= null;
            users1 = es.affichage();
            ObservableList<Evenement> observableList = FXCollections.observableList(users1);
            ListEvenement.setItems(observableList);}

    }
    @FXML
    void trierE(ActionEvent event) {
        List<Evenement> evenements = ListEvenement.getItems();
        evenements.sort(Comparator.comparingInt(Evenement::getNb_participation));
        ListEvenement.setItems(FXCollections.observableList(evenements));
    }
    @FXML
    void trierD(ActionEvent event) {
        ObservableList<Evenement> evenements = ListEvenement.getItems();
        evenements.sort(Comparator.comparingInt(Evenement::getNb_participation).reversed());
        ListEvenement.setItems(evenements);
    }
    }

