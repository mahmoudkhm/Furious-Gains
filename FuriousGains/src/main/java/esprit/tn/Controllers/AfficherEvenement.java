package esprit.tn.Controllers;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import esprit.tn.Models.Evenement;
import esprit.tn.Services.EvenementService;
import esprit.tn.Services.ExcelExporter;
import esprit.tn.Utils.QRCodeGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class AfficherEvenement {
    @FXML
    private ListView<Evenement> ListEvenement;
    @FXML
    private VBox qrview;
    @FXML
    private GridPane calendarGrid;
    @FXML
    private Button exportbtn;
    @FXML
    private TextField id_supp;
    @FXML
    private TextField rechercher;
    private Map<LocalDate, String> eventData = new HashMap<>();
    @FXML
    void on_click(MouseEvent event) {
        int selectedItem = ListEvenement.getSelectionModel().getSelectedItem().getId_event();
        id_supp.setText(String.valueOf(selectedItem));
    }
    private void populateCalendar() {
        calendarGrid.getChildren().clear();
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayLabel = new Label(daysOfWeek[i]);
            calendarGrid.add(dayLabel, i, 0);
        }
        YearMonth currentMonth = YearMonth.now();
        LocalDate firstDayOfMonth = currentMonth.atDay(1);
        int numRows = 6;
        int numCols = 7;
        LocalDate currentDate = firstDayOfMonth;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Label dayLabel = new Label(Integer.toString(currentDate.getDayOfMonth()));
                calendarGrid.add(dayLabel, col, row + 1);
                if (eventData.containsKey(currentDate)) {
                    String event = eventData.get(currentDate);
                    Label eventIndicator = new Label("•");
                    eventIndicator.setStyle("-fx-text-fill: red;");
                    calendarGrid.add(eventIndicator, col, row + 1);
                }
                currentDate = currentDate.plusDays(1);
            }
        }
    }
    private final EvenementService es = new EvenementService();

    @FXML
    void supprimer(ActionEvent event) {
        es.supprimer(Integer.parseInt(id_supp.getText()));
    }

    @FXML
    void initialize() {
        List<Evenement> evenements = null;
        evenements = es.affichage();
        ObservableList<Evenement> observableList = FXCollections.observableList(evenements);
        ListEvenement.setItems(observableList);

        for (Evenement event : evenements) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getDate_event());
            LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            eventData.put(localDate, event.getNom_event());
        }

        populateCalendar();
    }

    @FXML
    void rechercherE(KeyEvent event) {
        String cinText = rechercher.getText();
        if (!cinText.isEmpty()) {
            int cin = Integer.parseInt(cinText);
            Evenement evenement = es.getOneByCin(cin);
            if (evenement != null) {
                ObservableList<Evenement> observableList = FXCollections.observableArrayList(evenement);
                ListEvenement.setItems(observableList);
            } else {
                ListEvenement.setItems(FXCollections.emptyObservableList());
            }
        } else {
            List<Evenement> users1 = null;
            users1 = es.affichage();
            ObservableList<Evenement> observableList = FXCollections.observableList(users1);
            ListEvenement.setItems(observableList);
        }

    }

    @FXML
    void trierE(ActionEvent event) {
        List<Evenement> evenements = ListEvenement.getItems();
        evenements.sort(Comparator.comparingInt(Evenement::getNb_participation));
        ListEvenement.setItems(FXCollections.observableList(evenements));
    }

    @FXML
    void trierD(ActionEvent event) {
        ObservableList<Evenement> evenements = ListEvenement.getItems();
        evenements.sort(Comparator.comparingInt(Evenement::getNb_participation).reversed());
        ListEvenement.setItems(evenements);
    }

    @FXML
    void exportbtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("events.xlsx");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            ExcelExporter.exportToExcel(ListEvenement, filePath);
        }
    }

    @FXML
    void QRCode(ActionEvent event) {
        // Récupérer la campagne sélectionnée dans la ListView
        Evenement evenement = ListEvenement.getSelectionModel().getSelectedItem();

        if (evenement != null) {
            // Vérifier si le contenu de la campagne est vide ou null
            if (evenement.getNom_event() != null && !evenement.getNom_event().isEmpty()) {
                // Générer le contenu du lien pour le QR code
                String qrContent = evenement.getNom_event();


            try {
                // Générer le code QR pour le contenu
                BitMatrix bitMatrix = QRCodeGenerator.generateQRCodeMatrix(qrContent);

                // Convertir le BitMatrix en une image JavaFX
                WritableImage qrCodeImage = matrixToImage(bitMatrix);

                // Créer un ImageView pour afficher le QR code
                ImageView qrCodeImageView = new ImageView(qrCodeImage);

                // Effacer tout contenu précédent dans la VBox
                qrview.getChildren().clear();

                // Ajouter le QR code ImageView à la VBox
                qrview.getChildren().add(qrCodeImageView);
            } catch (WriterException e) {
                e.printStackTrace(); // Gérer l'exception de manière appropriée
            }}
         else {
            // Afficher un message d'erreur si le contenu de la campagne est vide ou null
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Contenu de evenement est  vide");
            alert.setContentText("Le contenu de l'evenement sélectionnée est vide.");
            alert.showAndWait();}
        } else {
            // Afficher un message d'erreur si aucune campagne n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune campagne sélectionnée");
            alert.setContentText("Veuillez sélectionner une campagne pour afficher le QR code.");
            alert.showAndWait();
        }

    }

    private WritableImage matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelWriter.setArgb(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

}


