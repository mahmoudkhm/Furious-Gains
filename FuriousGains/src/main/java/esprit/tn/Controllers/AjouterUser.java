package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterUser {
    @FXML
    private TextField adresseTF;

    @FXML
    private TextField cinTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField num_telTF;

    @FXML
    private TextField passwordTF;

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
       int typeUtilisateur = 1;

       // Vérification du champ cinTF
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

       // Ajouter l'utilisateur seulement si toutes les vérifications sont réussies
       us.utilisateurCanBeAded(new User(cin, nom, prenom, numTel, adresse, email, password, typeUtilisateur));
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

}
