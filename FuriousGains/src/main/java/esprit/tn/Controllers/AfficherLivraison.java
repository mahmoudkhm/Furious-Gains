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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AfficherLivraison {
    @FXML
    private ListView<Livraison> ListLivraison;

    @FXML
    private TextField id_sup;



    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = ListLivraison.getSelectionModel().getSelectedItem().getId_livraison();
        id_sup.setText(String.valueOf(selectedItem));

    }

    private final LivraisonService ls =new LivraisonService();

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
