package esprit.tn.Controllers;

import esprit.tn.Models.Reservation;
import esprit.tn.Services.ReservationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterReservation {
    @FXML
    private TextField id_clientTF;

    @FXML
    private TextField id_eventTF;

    @FXML
    private TextField nb_placeTF;

    @FXML
    private TextField status_resTF;
    private final ReservationService rs=new ReservationService();
    @FXML
    void AfficherReservationTF(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherReservation.fxml"));
            id_clientTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AjouterReservationTF(ActionEvent event) {
        rs.ajouter(new Reservation(Integer.parseInt(nb_placeTF.getText()),status_resTF.getText(),Integer.parseInt(id_eventTF.getText()),Integer.parseInt(id_clientTF.getText())));

    }
}
