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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AfficherEvenement {
    @FXML
    private ListView<Evenement> ListEvenement;

    @FXML
    private TextField id_supp;

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
}
