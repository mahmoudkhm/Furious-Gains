package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class AjouterUser {
    @FXML
    private TextField adresseTF;
    private File selectedImageFile;
    @FXML
    private DatePicker daten;
    String pic;

    @FXML
    private TextField cinTF;
    @FXML
    private Button uploadb;
    @FXML
    private PasswordField passwordTF;

    @FXML
    private ImageView imagevi;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField num_telTF;


    @FXML
    private TextField prenomTF;

    private final UserService us = new UserService();

   /* @FXML
    void AjouterTF(ActionEvent event) {
        us.utilisateurCanBeAded(new User(Integer.parseInt(cinTF.getText()), nomTF.getText(), prenomTF.getText(),Integer.parseInt(num_telTF.getText()), adresseTF.getText(), emailTF.getText(),passwordTF.getText(),1));
    }*/
   @FXML
   void AjouterTF(ActionEvent event) {
       int cin = 0;
       String nom = nomTF.getText();
       String prenom = prenomTF.getText();
       int numTel = 0;
       String adresse = adresseTF.getText();
       String email = emailTF.getText();
       String password = passwordTF.getText();
       String typeUtilisateur = "Client";
       LocalDate localDate = (LocalDate)this.daten.getValue();
       try {
           cin = Integer.parseInt(cinTF.getText());
       } catch (NumberFormatException e) {
           showErrorMessage("Le champ CIN doit être un nombre entier.");

           return;
       }
       // Vérification du champ num_telTF
       try {
           numTel = Integer.parseInt(num_telTF.getText());
       } catch (NumberFormatException e) {
           showErrorMessage("Le champ Numéro de téléphone doit être un nombre entier.");
           return;
       }
       // Vérification du champ emailTF
       if (!isValidEmail(email)) {
           showErrorMessage("L'adresse e-mail n'est pas valide.");
           return;
       }
       if (localDate == null) {
           Alert date = new Alert(Alert.AlertType.ERROR);
           date.setTitle("Erreur de saisie: champ DATE est vide");
           date.setHeaderText((String)null);
           date.setContentText("Il est obligatoire de mettre la date de la compétition !");
           date.showAndWait();
           return;
       }

       Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
       Date datenuser = Date.from(instant);
       us.utilisateurCanBeAded(new User(cin, nom, prenom,datenuser, numTel, adresse, email, password,typeUtilisateur,this.pic,1));
       Parent root = null;
       try {
           root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
           nomTF.getScene().setRoot(root);

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

    private boolean isValidEmail(String email) {
        // Vérification de la validité de l'adresse e-mail en utilisant une expression régulière
        // Cette expression régulière est un exemple simple, vous pouvez l'ajuster selon vos besoins
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private void showErrorMessage(String message) {
        // Afficher le message d'erreur dans une boîte de dialogue ou une autre méthode appropriée
        // Par exemple : AlertUtil.showErrorDialog("Erreur", message);
        System.out.println("Erreur : " + message);
    }
    @FXML
    void affichage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherUser.fxml"));
            cinTF.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ajouterImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Images", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif"})});
        selectedImageFile = fileChooser.showOpenDialog(this.imagevi.getScene().getWindow());
        if (this.selectedImageFile != null) {
            Image image = new Image(this.selectedImageFile.toURI().toString());
            this.imagevi.setImage(image);
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
