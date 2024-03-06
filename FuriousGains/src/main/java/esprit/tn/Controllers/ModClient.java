package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ModClient {


        @FXML
        private TextField adresseTF;

        private Image img;
        @FXML
        private TextField emailTF;
        @FXML
        private TextField nomTF;
        @FXML
        private TextField num_telTF;
        @FXML
        private TextField passwordTF;

    @FXML
    private Label cinM;
        @FXML
        private TextField prenomTF;
        @FXML
        private Button uploadbM;
        @FXML
        private ImageView imageviM;
        @FXML
        private DatePicker datenM;
        private File selectedImageFile;
        String pic;
        int cin =Login.getCin();


        @FXML
        void ajouterImage(ActionEvent event) {
            if (event.getSource() == this.uploadbM){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("Images", new String[]{"*.png", "*.jpg", "*.jpeg", "*.gif"})});
            selectedImageFile = fileChooser.showOpenDialog(this.imageviM.getScene().getWindow());
            if (this.selectedImageFile != null) {
                Image image = new Image(this.selectedImageFile.toURI().toString());
                this.imageviM.setImage(image);
                String uniqueID = UUID.randomUUID().toString();
                String extension = this.selectedImageFile.getName().substring(this.selectedImageFile.getName().lastIndexOf("."));
                this.pic = uniqueID + extension;
                Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", "uploads", this.pic);
                try {
                    Files.copy(this.selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }}

        }
        private final UserService us = new UserService();
        @FXML
        void handleComboBoxSelection(ActionEvent event) {

        }

        @FXML
        void ModifierTF(ActionEvent event) {
                Alert alertType;
                if (!nomTF.getText().isEmpty() && !prenomTF.getText().isEmpty() && !num_telTF.getText().isEmpty() && !adresseTF.getText().isEmpty() && !String.valueOf(cin).isEmpty() && !emailTF.getText().isEmpty()) {
                        if (this.nomTF.getText().matches("[0-9]+")) {
                                alertType = new Alert(Alert.AlertType.ERROR);
                                alertType.setTitle("Error");
                                alertType.setHeaderText("Nom must be string not number !");
                                alertType.show();
                        } else if (this.prenomTF.getText().matches("[0-9]+")) {
                                alertType = new Alert(Alert.AlertType.ERROR);
                                alertType.setTitle("Error");
                                alertType.setHeaderText("Prenom must be string not number !");
                                alertType.show();
                        } else if (this.adresseTF.getText().matches("[0-9]+")) {
                                alertType = new Alert(Alert.AlertType.ERROR);
                                alertType.setTitle("Error");
                                alertType.setHeaderText("Adresse must be string not number !");
                                alertType.show();
                        } else if (!String.valueOf(cin).matches("[A-Z]+") && !String.valueOf(cin).matches("[a-z]+") && String.valueOf(cin).length() == 8) {
                                if (!this.num_telTF.getText().matches("[A-Z]+") && !num_telTF.getText().matches("[a-z]+") && num_telTF.getText().length() == 8) {
                                        if (!this.emailTF.getText().matches("\\w+\\.?\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}")) {
                                                alertType = new Alert(Alert.AlertType.ERROR);
                                                alertType.setTitle("Error");
                                                alertType.setHeaderText("L'adresse email est invalide. Veuillez saisir une adresse email valide (ex: nom_utilisateur@domaine.com) !");
                                                alertType.show();
                                        } else {
                                                LocalDate localDate = (LocalDate) this.datenM.getValue();
                                                if (localDate == null) {
                                                        Alert date = new Alert(Alert.AlertType.ERROR);
                                                        date.setTitle("Erreur de saisie: champ DATE est vide");
                                                        date.setHeaderText((String) null);
                                                        date.setContentText("Il est obligatoire de mettre la date de la compétition !");
                                                        date.showAndWait();
                                                        return;
                                                }
                                                alertType = new Alert(Alert.AlertType.CONFIRMATION);
                                                alertType.setTitle("CONFIRMATION !");
                                                alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                                                Optional<ButtonType> result = alertType.showAndWait();
                                                if (result.get() == ButtonType.OK) {
                                                        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                                                        Date datenuser = Date.from(instant);
                                                        String nom = nomTF.getText();
                                                        String prenom = prenomTF.getText();
                                                        int tel = Integer.parseInt(num_telTF.getText());
                                                        String adresse = adresseTF.getText();
                                                        String email = emailTF.getText();
                                                        int code = 1;
                                                        User s = new User(cin, nom, prenom, datenuser, tel, adresse, email, this.pic, code);
                                                        UserService us = new UserService();
                                                        us.modifier(s);

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
                        alertType.setHeaderText("champ .");
                        alertType.show();
                }
        }



        @FXML
        void initialize() {
            List<User> users2 = us.afficher();
            ObservableList<String> cinList = FXCollections.observableArrayList();
            for (User user : users2) {
                cinList.add(Integer.toString(user.getCin()));
            }
                int cin =Login.getCin();

                if (cin != 0) {
                User users = us.getOneByCin(cin);
                cinM.setText(String.valueOf(cin));
                nomTF.setText(users.getNom());
                prenomTF.setText(users.getPrenom());
                Date date = users.getDateuser();
                Instant instant = Instant.ofEpochMilli(date.getTime());
                ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
                LocalDate localDate = zdt.toLocalDate();
                this.datenM.setValue(localDate);
                num_telTF.setText(String.valueOf(users.getNum_tel()));
                adresseTF.setText(users.getAdresse());
                emailTF.setText(users.getEmail());
                this.img = new Image("file:/C:/Users/nada/Desktop/Furious-Gains/FuriousGains/src/Images/uploads/" + users.getImage());
                System.out.println("image prog : " + this.img);
                this.imageviM.setImage(this.img);


                }
        }


}
