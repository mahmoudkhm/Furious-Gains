package esprit.tn.Controllers;

import esprit.tn.Models.Reservation;
import esprit.tn.Services.ReservationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

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
    void AjouterReservationTF(ActionEvent event) {
        try {
            int nbPlace = Integer.parseInt(nb_placeTF.getText());
            int idEvent = Integer.parseInt(id_eventTF.getText());
            int idClient = Integer.parseInt(id_clientTF.getText());
            String statusRes = status_resTF.getText();

            if (statusRes.isEmpty()) {
                throw new IllegalArgumentException("Le statut de la réservation ne peut pas être vide.");
            }

            rs.ajouter(new Reservation(nbPlace, statusRes, idEvent, idClient));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides pour les champs numériques.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    private TextField getStatus_resTF;

    public void initialize() {
        status_resTF.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z]*")) {
                return change;
            }
            return null;
        }));
    }

}
