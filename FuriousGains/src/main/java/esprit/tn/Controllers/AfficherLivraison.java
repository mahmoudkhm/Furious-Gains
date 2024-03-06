package esprit.tn.Controllers;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import esprit.tn.Models.Livraison;
import esprit.tn.Services.LivraisonService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import esprit.tn.Utils.QRCodeGenerator;

public class AfficherLivraison {
    @FXML
    private ListView<Livraison> ListLivraison;

    @FXML
    private TextField id_sup;

    @FXML
    private Button button_stat;
    @FXML
    private VBox qrview;
    @FXML
    private Button bouttonPDF;

    @FXML
    private Button bouttonSMS;

    @FXML
    private TextField rechercherlivraison;
    @FXML
    void chercher(KeyEvent event) {
        String statut = rechercherlivraison.getText();
        if (!statut.isEmpty()) {
            Livraison livraison = ls.getOneByiD(statut);
            if (livraison != null) {
                ObservableList<Livraison> observableList = FXCollections.observableArrayList(livraison);
                ListLivraison.setItems(observableList);
            } else {
                ListLivraison.setItems(FXCollections.emptyObservableList());
            }
        } else {
            List<Livraison> users1= null;
            users1 = ls.affichage();
            ObservableList<Livraison> observableList = FXCollections.observableList(users1);
            ListLivraison.setItems(observableList);
        }

    }
    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = ListLivraison.getSelectionModel().getSelectedItem().getId_livraison();
        id_sup.setText(String.valueOf(selectedItem));

    }

    private final LivraisonService ls =new LivraisonService();
    @FXML
    void statliv(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/staliv.fxml"));
            javafx.scene.Parent root = loader.load();


            Stage stage = (Stage) button_stat.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    @FXML
    void PDF(ActionEvent event) {
generatePDF();
    }
    /*@FXML
    private void generatePDF() {

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            String imagePath = "C:/Users/21621/OneDrive/Bureau/Furious-Gains - Copie/FuriousGains/src/main/resources/img/logo1.jpg";
            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float tableHeight = 20f;
            float cellMargin = 5f;
            float fontSize = 12f;

            // Draw border for the entire page
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(margin, margin);
            contentStream.lineTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.lineTo(margin + tableWidth, margin);
            contentStream.lineTo(margin, margin);
            contentStream.stroke();

            // Add a rectangle for the page header
            float headerHeight = 50f;
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.stroke();

            // Add your organization's logo or text in the header
            // Example: Add text "Your Organization Logo" at the center of the header
            //contentStream.setFont(PDType0Font.load(document, new File("C:/Users/REGAIEG Mahdi/Desktop/Roboto/Roboto-BoldItalic.ttf")), 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin + (tableWidth - 150) / 2, yStart - (headerHeight - 20) / 2);
            contentStream.endText();

            // Définir la police et la taille de police
            PDType0Font font = PDType0Font.load(document, new File("C:/Users/21621/OneDrive/Bureau/Furious-Gains/FuriousGains/src/Be-Natural1/Be-Natural.ttf"));
            float fontSize2 = 16;
            contentStream.setFont(font, fontSize2);

// Définir la couleur du texte en rouge
            float red = 1.0f;
            float green = 0.0f;
            float blue = 0.0f;
            contentStream.setNonStrokingColor(red, green, blue);

// Centrer le texte horizontalement
            float pageWidth = document.getPage(0).getMediaBox().getWidth();
            float textWidth = font.getStringWidth("La liste des Livraison") / 1000 * fontSize;
            float startX = (pageWidth - textWidth) / 2;

// Afficher le texte au milieu de la page
            float startY = yStart - headerHeight - 15;
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText("La liste des Livraisons");
            yStart -= headerHeight + 50;
            contentStream.endText();

            // Draw table headers
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.stroke();

            contentStream.setFont(PDType0Font.load(document, new File("C:/Users/21621/OneDrive/Bureau/Furious-Gains/FuriousGains/src/Be-Natural1/Be-Natural.ttf")), 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin + cellMargin, yStart - 15);
            contentStream.showText("Date");
            contentStream.newLineAtOffset(tableWidth / 4, 0);
            contentStream.showText("Adresse");
            contentStream.newLineAtOffset(tableWidth / 4, 0);
            contentStream.showText("Montant");
            contentStream.newLineAtOffset(tableWidth / 4, 0);
            contentStream.showText("Statut");
            contentStream.endText();
            yStart -= 30;

            // Draw table content
            List<Livraison> livraisons = ListLivraison.getItems();
            for (Livraison l : livraisons) {
                contentStream.setLineWidth(1.0f);
                contentStream.moveTo(margin, yStart);
                contentStream.lineTo(margin + tableWidth, yStart);
                contentStream.stroke();

                contentStream.beginText();
                contentStream.setFont(PDType0Font.load(document, new File("C:/Users/21621/OneDrive/Bureau/Furious-Gains/FuriousGains/src/Be-Natural1/Be-Natural.ttf")), 16);
                contentStream.newLineAtOffset(margin + cellMargin, yStart - 15);
                contentStream.showText(String.valueOf(l.getDate_livraison()));
                contentStream.newLineAtOffset(tableWidth / 4, 0);
                contentStream.showText(l.getAdresse_livraison());
                contentStream.newLineAtOffset(tableWidth / 4, 0);
                contentStream.showText(String.valueOf(l.getMontant_paiement()));
                contentStream.newLineAtOffset(tableWidth / 4, 0);
                contentStream.showText(l.getStatut_livraison());
                contentStream.endText();
                yStart -= tableHeight + cellMargin;
            }

            contentStream.close();

            // Save and open the document
            File file = new File("Livraisons.pdf");
            document.save(file);
            document.close();
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("An error occurred while generating the PDF.");
            alertType.show();
        }
    }*/
    @FXML
    private void generatePDF() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            String imagePath = "C:/Users/21621/OneDrive/Bureau/Furious-Gains/FuriousGains/img/logo1.png";
            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float tableHeight = 20f;
            float cellMargin = 5f;
            float fontSize = 12f;

            // Draw border for the entire page
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(margin, margin);
            contentStream.lineTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.lineTo(margin + tableWidth, margin);
            contentStream.lineTo(margin, margin);
            contentStream.stroke();

            // Add a rectangle for the page header
            float headerHeight = 50f;
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.stroke();

            // Add your organization's logo or text in the header
            float imageWidth = 80; // Adjust the width of the image as needed
            float imageHeight = 90; // Adjust the height of the image as needed
            float imageX = margin + tableWidth - imageWidth - 20; // Adjust the X position of the image to align it to the right
            float imageY = yStart - headerHeight - 47; // Adjust the Y position of the image
            contentStream.drawImage(image, imageX, imageY, imageWidth, imageHeight);

            // Set up page title
            PDType0Font font = PDType0Font.load(document, new File("C:/Users/21621/OneDrive/Bureau/Furious-Gains/FuriousGains/src/Be-Natural1/Be-Natural.ttf"));
            float fontSize2 = 16;
            contentStream.setFont(font, fontSize2);

            // Set text color to red
            float red = 1.0f;
            float green = 0.0f;
            float blue = 0.0f;
            contentStream.setNonStrokingColor(red, green, blue);

            // Center the text horizontally
            float pageWidth = document.getPage(0).getMediaBox().getWidth();
            float textWidth = font.getStringWidth("La liste des Livraisons") / 1000 * fontSize2;
            float startX = (pageWidth - textWidth) / 2;

            // Display the text in the middle of the page
            float startY = yStart - headerHeight - 15;
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText("La liste des Livraisons");
            contentStream.endText();
            yStart -= headerHeight + 50;

            // Draw table headers
            contentStream.setLineWidth(1.5f);
            contentStream.moveTo(margin, yStart);
            contentStream.lineTo(margin + tableWidth, yStart);
            contentStream.stroke();

            contentStream.setFont(font, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin + cellMargin, yStart - 15);
            contentStream.showText("Date");
            contentStream.newLineAtOffset(tableWidth / 4, 0);
            contentStream.showText("Adresse");
            contentStream.newLineAtOffset(tableWidth / 4, 0);
            contentStream.showText("Montant");
            contentStream.newLineAtOffset(tableWidth / 4, 0);
            contentStream.showText("Statut");
            contentStream.endText();
            yStart -= 30;

            // Draw table content
            List<Livraison> livraisons = ListLivraison.getItems();
            for (Livraison l : livraisons) {
                contentStream.setLineWidth(1.0f);
                contentStream.moveTo(margin, yStart);
                contentStream.lineTo(margin + tableWidth, yStart);
                contentStream.stroke();

                contentStream.beginText();
                contentStream.setFont(PDType0Font.load(document, new File("C:/Users/21621/OneDrive/Bureau/Furious-Gains/FuriousGains/src/Be-Natural1/Be-Natural.ttf")), 16);
                contentStream.newLineAtOffset(margin + cellMargin, yStart - 15);
                contentStream.showText(String.valueOf(l.getDate_livraison()));
                contentStream.newLineAtOffset(tableWidth / 4, 0);
                contentStream.showText(l.getAdresse_livraison());
                contentStream.newLineAtOffset(tableWidth / 4, 0);
                contentStream.showText(String.valueOf(l.getMontant_paiement()));
                contentStream.newLineAtOffset(tableWidth / 4, 0);
                contentStream.showText(l.getStatut_livraison());
                contentStream.endText();
                yStart -= tableHeight + cellMargin;
            }
            contentStream.close();

            File file = new File("Livraisons.pdf");
            document.save(file);
            document.close();
            Desktop.getDesktop().open(file);


            System.out.println("PDF generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void QRCode(ActionEvent event) {
        // Récupérer la campagne sélectionnée dans la ListView
        Livraison livraison= ListLivraison.getSelectionModel().getSelectedItem();

        if (livraison != null) {
            // Vérifier si le contenu de la campagne est vide ou null
            if (livraison.getAdresse_livraison() != null && !livraison.getAdresse_livraison().isEmpty()) {
                // Générer le contenu du lien pour le QR code
                String qrContent = livraison.getAdresse_livraison();


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
                alert.setTitle("Contenu de livraison est  vide");
                alert.setContentText("Le contenu de livraison sélectionnée est vide.");
                alert.showAndWait();}
        } else {
            // Afficher un message d'erreur si aucune campagne n'est sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune livraison sélectionnée");
            alert.setContentText("Veuillez sélectionner une livraison pour afficher le QR code.");
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





