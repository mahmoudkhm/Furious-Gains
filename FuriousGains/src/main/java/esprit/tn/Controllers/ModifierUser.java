package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.RotateEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ModifierUser {
    @FXML
    private TextField adresseTF;

    @FXML
    private ComboBox<String> cinM;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField num_telTF;

    @FXML
    private TextField passwordTF;
    @FXML
    private TextField roleTF;

    @FXML
    private TextField prenomTF;
    private final UserService us = new UserService();
    @FXML
    void handleComboBoxSelection(ActionEvent event) {
        String selectedValue = cinM.getValue();
        if (selectedValue != null) {
            int cin = Integer.parseInt(selectedValue);
            User users = us.getOneByCin(cin);
            nomTF.setText(users.getNom());
            prenomTF.setText(users.getPrenom());
            num_telTF.setText(String.valueOf(users.getNum_tel()));
            adresseTF.setText(users.getAdresse());
            emailTF.setText(users.getEmail());
            passwordTF.setText(users.getPassword());
            roleTF.setText(users.getRole());}
    }

    @FXML
    void ModifierTF(ActionEvent event) {
        Alert alertType;
        if (!nomTF.getText().isEmpty() && !prenomTF.getText().isEmpty() && !num_telTF.getText().isEmpty() && !adresseTF.getText().isEmpty() && !cinM.getValue().isEmpty() && !emailTF.getText().isEmpty() && !passwordTF.getText().isEmpty())
        {
            if (this.nomTF.getText().matches("[0-9]+"))
            {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("Nom must be string not number !");
                alertType.show();
            } else if (this.prenomTF.getText().matches("[0-9]+"))
            {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("Prenom must be string not number !");
                alertType.show();
            } else if (this.adresseTF.getText().matches("[0-9]+")) {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("Adresse must be string not number !");
                alertType.show();
            } else if (!cinM.getValue().matches("[A-Z]+") && !this.cinM.getValue().matches("[a-z]+") && cinM.getValue().length() == 8)
            {
                if (!this.num_telTF.getText().matches("[A-Z]+") && !num_telTF.getText().matches("[a-z]+") && num_telTF.getText().length() == 8) {
                    if (!this.emailTF.getText().matches( "\\w+\\.?\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}")) {
                        alertType = new Alert(Alert.AlertType.ERROR);
                        alertType.setTitle("Error");
                        alertType.setHeaderText("L'adresse email est invalide. Veuillez saisir une adresse email valide (ex: nom_utilisateur@domaine.com) !");
                        alertType.show();
                    } else {
                        alertType = new Alert(Alert.AlertType.CONFIRMATION);
                        alertType.setTitle("CONFIRMATION !");
                        alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                        Optional<ButtonType> result = alertType.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            String nom = nomTF.getText();
                            String prenom = prenomTF.getText();
                            int tel = Integer.parseInt(num_telTF.getText());
                            String adresse = adresseTF.getText();
                            int cin = Integer.parseInt(cinM.getValue());
                            String email = emailTF.getText();
                            String mdp = passwordTF.getText();
                            String role = roleTF.getText();
                            User s = new User( cin,  nom, prenom,tel, adresse, email, mdp,role);
                            UserService us = new UserService();
                            us.modifier2(s);
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherUser.fxml"));


                            Parent root = null;
                            try {
                                root = (Parent)loader.load();
                                this.nomTF.getScene().setRoot(root);
                            } catch (IOException e) {
                                System.out.println("Error:" + e.getMessage());
                            }

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

    @FXML
    void affichage(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(this.getClass().getResource("/AfficherUser.fxml"));

            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void initialize() {
        List<User> users = us.afficher();
        ObservableList<String> cinList = FXCollections.observableArrayList();
        for (User user : users) {
            cinList.add(Integer.toString(user.getCin()));
        }
        cinM.setItems(cinList);
    }
}
