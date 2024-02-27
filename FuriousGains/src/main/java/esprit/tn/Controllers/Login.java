package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

public class Login {
    @FXML
    private TextField adresse;

    @FXML
    private PasswordField password;
    private final UserService us = new UserService();

    @FXML
    void connecter(ActionEvent event) {
        Alert alertType;
        String email= adresse.getText();
        String mdp=password.getText();
        Preferences prefs = Preferences.userNodeForPackage(Login.class);
        String emailRegex = "\\w+\\.?\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}";
        if (!email.isEmpty() && !mdp.isEmpty()) {
            if (!email.matches(emailRegex)) {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("L'adresse email est invalide. Veuillez saisir une adresse email valide (ex: nom_utilisateur@domaine.com) !");
                alertType.show();
            } else {
                User user = us.verifierEmailMdp(email, mdp);
                if (user != null) {
                    String  role = user.getRole();
                    String nom = user.getNom();
                    String prenom = user.getPrenom();

                    if (role.equals("Admin")) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("bonjour "+nom +" "+ prenom);
                        alert.setContentText("Connecter en tant qu'admin!.");
                        alert.show();
                        prefs.putInt("iduser", user.getId_user());
                        try {
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AfficherUser.fxml"));
                            Parent root = (Parent)loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else if (role.equals("Client") ) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("bienvenue sur FuriousGains");
                        alert.setHeaderText("bonjour "+nom +" "+  prenom);
                        alert.setContentText("Connecter en tant que Client!.");
                        alert.show();
                        prefs.putInt("iduser", user.getId_user());
                        User u = new User();
                        u.getId_user();
                        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AfficherUser.fxml"));

                        try {
                            Parent root = (Parent)loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Email ou mot de passe invalide !.");
                    alert.show();
                }
            }
        } else {
            alertType = new Alert(Alert.AlertType.WARNING);
            alertType.setTitle("Attention");
            alertType.setContentText("Veuillez saisir l'email et le mot de passe.");
            alertType.show();
        }

    }

    @FXML
    void inscrireUser(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AjouterUser.fxml"));
            adresse.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
