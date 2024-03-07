package esprit.tn.Controllers;

import esprit.tn.Models.Annonce;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.AnnonceService;
import esprit.tn.Services.AvisService;
import esprit.tn.Services.LivraisonService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class HboxListLivr {

    @FXML
    private GridPane gridPane;

    @FXML
    private DatePicker datePicker;

    private final LivraisonService ls = new LivraisonService();
    @FXML
    public void initialize() {
        // Création du bouton pour tout le GridPane
        Button ajouterReservationButton = new Button("Ajouter une réservation");
        ajouterReservationButton.setOnAction(this::ajouterReservationButtonClicked);
        gridPane.add(ajouterReservationButton, 0, 0, 3, 1); // Ajouter le bouton à la première ligne du GridPane

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

        Button reserverButton = new Button("Réserver");
        reserverButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Ajoutez ici le code pour gérer le clic sur le bouton "Réserver"
            }
        });

        annonceBox.getChildren().addAll(statut, adresse, montant, modelivraison, datePickerliv);
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
    }
    @FXML
    private void searchButtonClicked(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();

        // Efface le contenu actuel du GridPane
        gridPane.getChildren().removeIf(node -> node instanceof VBox);

        // Met à jour l'affichage avec les voitures disponibles pour la date sélectionnée
        List<Livraison> availableCars = null;
        int rowIndex = 1;
        int columnIndex = 0;
        for (Livraison v : availableCars) {
            addAnnonceToGridPane(v, rowIndex, columnIndex);
            columnIndex++;
            if (columnIndex == 3) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }




}
