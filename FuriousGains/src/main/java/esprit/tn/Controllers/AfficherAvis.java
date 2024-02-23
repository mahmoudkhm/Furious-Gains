package esprit.tn.Controllers;

import esprit.tn.Models.Avis;
import esprit.tn.Services.AvisService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AfficherAvis {
    @FXML
    private TextField id_supp;

    @FXML
    private ListView<Avis> listAvis;

    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listAvis.getSelectionModel().getSelectedItem().getId_avis();
        id_supp.setText(String.valueOf(selectedItem));

    }
private final AvisService as=new AvisService();
    @FXML
    void supprimer(ActionEvent event) {
        as.supprimer(Integer.parseInt(id_supp.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            id_supp.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML void initialize(){
        List<Avis> avis=null;
        avis=as.affichage();
        ObservableList<Avis> observableList= FXCollections.observableList(avis);
        listAvis.setItems(observableList);

    }
}
