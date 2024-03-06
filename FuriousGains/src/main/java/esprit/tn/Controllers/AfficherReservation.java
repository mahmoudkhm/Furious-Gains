package esprit.tn.Controllers;


import esprit.tn.Models.Reservation;
import esprit.tn.Services.ReservationService;
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



public class AfficherReservation {
    @FXML
    private ListView<Reservation> listRes;

    @FXML
    private TextField supp_res;
    private final ReservationService rs = new ReservationService();

    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listRes.getSelectionModel().getSelectedItem().getId_Res();
        supp_res.setText(String.valueOf(selectedItem));

    }

    @FXML
    void supprimerRes(ActionEvent event) {
        rs.supprimer(Integer.parseInt(supp_res.getText()));
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherReservation.fxml"));
            supp_res.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void initialize() {
        List<Reservation> reservations = null;
        reservations = rs.affichage();
        ObservableList<Reservation> observableList = FXCollections.observableList(reservations);
        listRes.setItems(observableList);

    }


    }




