package esprit.tn.Controllers;

import esprit.tn.Models.Evenement;
import esprit.tn.Services.EvenementService;
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

public class ModifierEvenement {
    @FXML
    private TextField dateTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField heureTF;

    @FXML
    private ComboBox<String> idM;

    @FXML
    private TextField lieu_eventTF;

    @FXML
    private TextField nb_participantsTF;

    @FXML
    private TextField nom_eventTF;

    @FXML
    private TextField prix_eventTF;
    private final EvenementService es=new EvenementService();

    @FXML
    void ModifierTF(ActionEvent event) {
        Alert alertType;
        if (!nom_eventTF.getText().isEmpty() && !lieu_eventTF.getText().isEmpty() && !prix_eventTF.getText().isEmpty() && !nb_participantsTF.getText().isEmpty() && !idM.getValue().isEmpty() && !dateTF.getText().isEmpty() && !heureTF.getText().isEmpty() && !descriptionTF.getText().isEmpty()) {
                if (this.nom_eventTF.getText().matches("[0-9]+")) {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("nom de l'evenement  doit  etre string non number !");
                    alertType.show();
                } else if (this.dateTF.getText().matches("[A-Z]+")) {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("date doit etre  number non string !");
                    alertType.show();
                } else if (this.heureTF.getText().matches("[A-Z]+")) {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("heure doit  etre string non number !");
                    alertType.show();
                } else if (this.descriptionTF.getText().matches("[0-9]+")) {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("adresse doit etre string non number !");
                    alertType.show();
                } else if (!idM.getValue().matches("[A-Z]+") && !this.idM.getValue().matches("[a-z]+")) {
                    if (!this.prix_eventTF.getText().matches("[A-Z]+") && !prix_eventTF.getText().matches("[a-z]+")) {
                        if (!this.nb_participantsTF.getText().matches("[A-Z]+") && !nb_participantsTF.getText().matches("[a-z]+")) {

                            alertType = new Alert(Alert.AlertType.CONFIRMATION);
                            alertType.setTitle("CONFIRMATION !");
                            alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                            Optional<ButtonType> result = alertType.showAndWait();
                            if (result.get() == ButtonType.OK) {

                                int id_event = Integer.parseInt(idM.getValue());
                                String nom_event = nom_eventTF.getText();
                                String lieu_event = lieu_eventTF.getText();
                                String date = dateTF.getText();

                                float prix_event = Float.parseFloat(prix_eventTF.getText());
                                int nb_participation = Integer.parseInt(nb_participantsTF.getText());
                                String heure = heureTF.getText();
                                String description = descriptionTF.getText();
                                Evenement evenement = new Evenement(id_event, nom_event, lieu_event, prix_event, nb_participation, date, heure, description);
                                EvenementService es = new EvenementService();
                                es.modifier(evenement);
                                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherUser.fxml"));


                                Parent root = null;

                                try {
                                    root = (Parent) loader.load();
                                    nom_eventTF.getScene().setRoot(root);

                                } catch (IOException e) {
                                    System.out.println("Error:" + e.getMessage());
                                }


                            }

                        } else {
                            alertType = new Alert(Alert.AlertType.ERROR);
                            alertType.setTitle("Error");
                            alertType.setHeaderText("Le numéro de téléphone doit comporter exactement 8 chiffres.");
                            alertType.show();
                        }
                    } else {
                        alertType = new Alert(Alert.AlertType.ERROR);
                        alertType.setTitle("Error");
                        alertType.setHeaderText("Le numéro de CIN doit comporter exactement 8 chiffres.");
                        alertType.show();
                    }
                } else {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("Enter a valid content !");
                    alertType.show();
                }
            }

        }

    @FXML
    void afficherevenement(ActionEvent event) {

        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
            nom_eventTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {
        List<Evenement> evenemets = es.affichage();
        ObservableList<String> idList = FXCollections.observableArrayList();
        for (Evenement evenement : evenemets) {
            idList.add(Integer.toString(evenement.getId_event()));
        }
        idM.setItems(idList);
    }
}
