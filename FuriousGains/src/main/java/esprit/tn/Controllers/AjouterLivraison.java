package esprit.tn.Controllers;

import esprit.tn.Models.Livraison;
import esprit.tn.Models.sms;
import esprit.tn.Services.LivraisonService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.time.*;
import java.util.Date;
import java.util.List;

import static java.time.Duration.*;

public class AjouterLivraison {
    @FXML
    private Button payer_button;
    @FXML
    private DatePicker DateLivraisonTF;

    @FXML
    private Button AjouterAnnonce1;

    @FXML
    private TextField AdresseLivraisonTF;

    @FXML
    private TextField IdClientTF;

    @FXML
    private TextField IdCommandeTF;

    @FXML
    private TextField ModeLivraisonTF;

    @FXML
    private TextField MontantTF;

    @FXML
    private TextField StatutLivraisonTF;


    private final LivraisonService ls =new LivraisonService();

    @FXML
    private GridPane gridPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    public void initialize() {
        // Création du bouton pour tout le GridPane
 // Ajouter le bouton à la première ligne du GridPane

        // Lecture de toutes les voitures
        List<Livraison> livraisonList = ls.affichage();
        int rowIndex = 1; // Commencer à partir de la deuxième ligne après le bouton
        int columnIndex = 0;
        for (Livraison livraison : livraisonList) {
            addAnnonceToGridPane(livraison, rowIndex, columnIndex);
            columnIndex++;
            if (columnIndex == 3) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }
    private void addAnnonceToGridPane(Livraison livraison, int rowIndex, int columnIndex) {
        VBox annonceBox = new VBox();
        annonceBox.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;");

        Label statut = new Label("statut du livraison: " + livraison.getStatut_livraison());
        Label adresse = new Label("adresse: " + livraison.getAdresse_livraison());
        Label montant = new Label("Montant: " + livraison.getMode_livraison());
        Label modelivraison = new Label("Mode livraison: " + livraison.getMode_livraison());
        DatePicker datePickerliv = new DatePicker();

        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        Date date = livraison.getDate_livraison();
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        datePickerliv.setValue(localDate);

        Button LivraisonButton = new Button("Livré");
        LivraisonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Ajoutez ici le code pour gérer le clic sur le bouton "Réserver"
            }
        });

        annonceBox.getChildren().addAll(statut, adresse, montant, modelivraison, datePickerliv,LivraisonButton);
        gridPane.add(annonceBox, columnIndex, rowIndex);
    }
    // Méthode pour ajouter une voiture au GridPane
    @FXML
    private void ajouterReservationButtonClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/calendrier.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Consulter les réservations");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }@FXML
    private void searchButtonClicked(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();

        gridPane.getChildren().removeIf(node -> node instanceof VBox);

        List<Livraison> Livraisons =ls.getLivraisonparDate(selectedDate);
        int rowIndex = 1;
        int columnIndex = 0;
        for (Livraison v : Livraisons) {
            addAnnonceToGridPane(v, rowIndex, columnIndex);
            columnIndex++;
            if (columnIndex == 3) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }

   @FXML
   void AjouterTF(ActionEvent event) {
       LocalDate localDate = (LocalDate)this.DateLivraisonTF.getValue();
       Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
       Date dateliv = Date.from(instant);
       Alert alertType;
       if (!IdCommandeTF.getText().isEmpty() && !StatutLivraisonTF.getText().isEmpty() && !AdresseLivraisonTF.getText().isEmpty()  && !ModeLivraisonTF.getText().isEmpty() && !IdClientTF.getText().isEmpty()&& !MontantTF.getText().isEmpty()  ) {
           if (this.IdCommandeTF.getText().matches("[a-z]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("id client doit être un numero et non une chaine!");
               alertType.show();
           } else if (IdClientTF.getText().matches("[a-z]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("id commande doit être un numero et non une chaine! !");
               alertType.show();
           } else if (this.ModeLivraisonTF.getText().matches("[0-9]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("mode de livraison doit etre une chaine et non et numero !");
               alertType.show();
           } else if (this.StatutLivraisonTF.getText().matches("[0-9]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("statut doit être une chaîne et non un numéro !");
               alertType.show();
           } else if (this.MontantTF.getText().matches("[a-z]+")||MontantTF.getText().matches("[A-Z]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("Montant doit être un numero et non une chaine !");
               alertType.show();
           }else
               {Livraison livraison= new Livraison(Integer.parseInt(IdCommandeTF.getText()),dateliv,StatutLivraisonTF.getText(), AdresseLivraisonTF.getText(), Float.parseFloat( MontantTF.getText()), ModeLivraisonTF.getText(),Integer.parseInt(IdClientTF.getText()));
                   ls.ajouter(new Livraison(Integer.parseInt(IdCommandeTF.getText()),dateliv,StatutLivraisonTF.getText(), AdresseLivraisonTF.getText(), Float.parseFloat( MontantTF.getText()), ModeLivraisonTF.getText(),Integer.parseInt(IdClientTF.getText())));
                   sms.sms(livraison,"\n Furious Gains:","+21621174221");
                   boolean  oeuvreAdded = true;

                   if (oeuvreAdded) {
                       Notifications.create()
                               .title("Notification Title")
                               .text("Livraison AJOUTEE")

                               .showInformation();
                   }
               }
       }
       else
               {alertType = new Alert(Alert.AlertType.ERROR);
                   alertType.setTitle("Champs vide!!! ");
                   alertType.show();
               }


   }
    @FXML
    void afficherlivraison(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherLivraison.fxml"));
            IdCommandeTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void payer(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/PaymentController.fxml"));
            payer_button.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
