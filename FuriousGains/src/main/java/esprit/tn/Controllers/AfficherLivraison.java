package esprit.tn.Controllers;

import esprit.tn.Models.Livraison;
import esprit.tn.Services.LivraisonService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AfficherLivraison {
    @FXML
    private ListView<Livraison> ListLivraison;

    @FXML
    private TextField id_sup;

    @FXML
    private Button button_stat;

    @FXML
    private TextField rechercherlivraison;
    @FXML
    void chercher(KeyEvent event) {
        String statut = rechercherlivraison.getText();
        if (!statut.isEmpty()) {
            Livraison livraison = ls.getOneByiD(statut);
            if (livraison != null) {
                ObservableList<Livraison> observableList = FXCollections.observableArrayList(livraison);
                ListLivraison.setItems(observableList);
            } else {
                ListLivraison.setItems(FXCollections.emptyObservableList());
            }
        } else {
            List<Livraison> users1= null;
            users1 = ls.affichage();
            ObservableList<Livraison> observableList = FXCollections.observableList(users1);
            ListLivraison.setItems(observableList);
        }

    }
    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = ListLivraison.getSelectionModel().getSelectedItem().getId_livraison();
        id_sup.setText(String.valueOf(selectedItem));

    }

    private final LivraisonService ls =new LivraisonService();
    @FXML
    void statliv(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/staliv.fxml"));
            javafx.scene.Parent root = loader.load();


            Stage stage = (Stage) button_stat.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Supprimer(ActionEvent event) {
        ls.supprimer(Integer.parseInt(id_sup.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherLivraison.fxml"));
            id_sup.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void initialize()
    {
        List<Livraison> livraisons=null;
        livraisons=ls.affichage();
        ObservableList<Livraison >observableList= FXCollections.observableList(livraisons) ;
        ListLivraison.setItems(observableList);


    }
}
