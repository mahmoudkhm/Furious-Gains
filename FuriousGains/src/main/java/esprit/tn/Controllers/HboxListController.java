package esprit.tn.Controllers;

import esprit.tn.Models.Annonce;
import esprit.tn.Services.AnnonceService;
import esprit.tn.Services.AvisService;
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
import java.time.LocalDate;
import java.util.List;

public class HboxListController {

    @FXML
    private GridPane gridPane;

    @FXML
    private DatePicker datePicker;

    private final AnnonceService as = new AnnonceService();
    private final AvisService vs = new AvisService();
    @FXML
    public void initialize() {
        // Création du bouton pour tout le GridPane
        Button ajouterReservationButton = new Button("Ajouter une réservation");
        ajouterReservationButton.setOnAction(this::ajouterReservationButtonClicked);
        gridPane.add(ajouterReservationButton, 0, 0, 3, 1); // Ajouter le bouton à la première ligne du GridPane

        // Lecture de toutes les voitures
        List<Annonce> annonceList = as.affichage();
        int rowIndex = 1; // Commencer à partir de la deuxième ligne après le bouton
        int columnIndex = 0;
        for (Annonce a : annonceList) {
            addAnnonceToGridPane(a, rowIndex, columnIndex);
            columnIndex++;
            if (columnIndex == 3) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }
    private void addAnnonceToGridPane(Annonce annonce, int rowIndex, int columnIndex) {
        VBox annonceBox = new VBox();
        annonceBox.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;");

        Label titreLabel = new Label("Titre: " + annonce.getTitre_annonce());
        Label descriptionLabel = new Label("Description: " + annonce.getDescription_annonce());
        Label nomLabel = new Label("Nom: " + annonce.getTitre_annonce());

        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        try {
            File file = new File(annonce.getImage());
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button reserverButton = new Button("Réserver");
        reserverButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Ajoutez ici le code pour gérer le clic sur le bouton "Réserver"
            }
        });

        annonceBox.getChildren().addAll(titreLabel, descriptionLabel, nomLabel, imageView, reserverButton);
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

        // Efface le contenu actuel du GridPane
        gridPane.getChildren().removeIf(node -> node instanceof VBox);

        // Met à jour l'affichage avec les voitures disponibles pour la date sélectionnée
        List<Annonce> availableCars = null;
        int rowIndex = 1;
        int columnIndex = 0;
        for (Annonce v : availableCars) {
            addAnnonceToGridPane(v, rowIndex, columnIndex);
            columnIndex++;
            if (columnIndex == 3) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }


    private void showAlert(String erreur, String s) {
    }

}
