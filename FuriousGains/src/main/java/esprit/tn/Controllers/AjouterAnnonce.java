package esprit.tn.Controllers;

import esprit.tn.Models.Annonce;
import esprit.tn.Models.Avis;
import esprit.tn.Services.AnnonceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class AjouterAnnonce {

    @FXML
    private TextArea desTF;
    private File selectedImageFile;
    String pic;

    @FXML
    private ImageView upload;

    @FXML
    private Button uploadb;

    @FXML
    private TextField id_userTF;

    @FXML
    private ImageView image;

    @FXML
    private TextField tittreATF;
    private final AnnonceService ass=new AnnonceService();

    @FXML
    void AjouterAnnonceTF(ActionEvent event) {
        String titreText = tittreATF.getText();
        String descriptionText = desTF.getText();
        String id_userText = id_userTF.getText();
        if ( titreText.isEmpty() || descriptionText.isEmpty() ||id_userText.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs.");
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }

        try {
            String titre_annonce = titreText;
            String description_annonce = descriptionText;
            int id_user = Integer.parseInt(id_userText);
            ass.ajouter(new Annonce(titre_annonce, description_annonce,pic,id_user));
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherAnnonce.fxml"));
            desTF.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout");
            alert.showAndWait();


    }

   /* @FXML
    void AjouterAnnonceTF(ActionEvent event) {
        ass.ajouter(new Annonce(tittreATF.getText(),desTF.getText(),Integer.parseInt(id_userTF.getText())));


    }

    */

} @FXML
    void AfficherAnnonceTF(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            id_userTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void ajouterImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Images", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif"})});
        selectedImageFile = fileChooser.showOpenDialog(this.image.getScene().getWindow());
        if (this.selectedImageFile != null) {
            Image image = new Image(this.selectedImageFile.toURI().toString());
            this.image.setImage(image);
            String uniqueID = UUID.randomUUID().toString();
            String extension = this.selectedImageFile.getName().substring(this.selectedImageFile.getName().lastIndexOf("."));
            this.pic = uniqueID + extension;
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", "uploads", this.pic);
            try {
                Files.copy(this.selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
