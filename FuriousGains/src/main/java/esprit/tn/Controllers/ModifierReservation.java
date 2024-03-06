package esprit.tn.Controllers;
import esprit.tn.Models.Reservation;
import esprit.tn.Services.ReservationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ModifierReservation {
    @FXML
    private ComboBox<String> idR;

    @FXML
    private TextField id_clientTF;

    @FXML
    private TextField id_eventTF;

    @FXML
    private TextField nb_placeTF;

    @FXML
    private TextField status_resTF;


    private final ReservationService rs = new ReservationService();



    @FXML
    void initialize() {
        List<Reservation> reservations = rs.affichage();
        ObservableList<String> idList = FXCollections.observableArrayList();
        for (Reservation reservation : reservations) {
            idList.add(Integer.toString(reservation.getId_Res()));
        }
        idR.setItems(idList);
    }
    @FXML
    void combo(ActionEvent event) {
String selectedValue = String.valueOf(idR.getValue());
if (selectedValue != null) {
    int idr=Integer.parseInt(selectedValue);
    Reservation r = rs.getOneByCin(idr);
    nb_placeTF.setText(String.valueOf(r.getNb_place()));
    status_resTF.setText(r.getStatus_Res());
    id_eventTF.setText(String.valueOf(r.getId_event()));
    id_clientTF.setText(String.valueOf(r.getId_client()));



}

    }
    public void modifierRFT(javafx.event.ActionEvent actionEvent) {
        Alert alertType;
        if (!idR.getValue().isEmpty() && !nb_placeTF.getText().isEmpty() && !id_eventTF.getText().isEmpty() && !id_clientTF.getText().isEmpty() && !status_resTF.getText().isEmpty()) {
            if (this.status_resTF.getText().matches("[0-9]+")) {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("le status de reservation doit etre string et non pas un entier !");
                alertType.show();
            } else if (!idR.getValue().matches("[A-Z]+") && !this.idR.getValue().matches("[a-z]+")) {

                alertType = new Alert(Alert.AlertType.CONFIRMATION);
                alertType.setTitle("CONFIRMATION !");
                alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                Optional<ButtonType> result = alertType.showAndWait();
                if (result.get() == ButtonType.OK) {


                    int id_Res = Integer.parseInt(idR.getValue());
                    int nb_place = Integer.parseInt(nb_placeTF.getText());
                    String status_Res = status_resTF.getText();
                    int id_event = Integer.parseInt(id_eventTF.getText());
                    int id_client = Integer.parseInt(id_clientTF.getText());

                    Reservation r = new Reservation(id_Res, nb_place, status_Res, id_event, id_client);
                    ReservationService rs = new ReservationService();
                    rs.modifier(r);
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherReservation.fxml"));


                    Parent root = null;

                    try {
                        root = (Parent) loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.status_resTF.getScene().setRoot(root);

                }
            }
        } else {
            alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            //  alertType.setHeaderText("Le numéro de téléphone doit comporter exactement 8 chiffres.");
            alertType.show();
        }

    }



    public void afficherRFT(javafx.event.ActionEvent actionEvent) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
            status_resTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
