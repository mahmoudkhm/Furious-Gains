package esprit.tn.Controllers;

import com.github.sarxos.webcam.Webcam;
import esprit.tn.Models.CodePromo;
import esprit.tn.Models.User;
import esprit.tn.Services.ImageSingleton;
import esprit.tn.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class Profil {

    @FXML
    private Label adresseP;

    @FXML
    private Label cinP;

    @FXML
    private Label emailP;

    @FXML
    private ImageView imageP;

    @FXML
    private Label nomP;

    @FXML
    private Label prenomP;

    @FXML
    private Label roleP;
    private Image img;
    private final UserService us = new UserService();


    @FXML
    void modP(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ModifierUser.fxml"));
            prenomP.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void modPass(ActionEvent event) {

    }

    @FXML
    private DatePicker date_nC;

    @FXML
    private Label Email;
    @FXML
    private ImageView exit;

    @FXML
    private Button exitB;
    @FXML
    void exit(ActionEvent event) {
        exitB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
    }
    @FXML
    void initialize(){
        int cin =Login.getCin();
        User user =new User();
        user= us.getOneByCin(cin);
        nomP.setText(user.getNom());
        prenomP.setText(user.getPrenom());
        this.img = new Image("file:/C:/Users/nada/Desktop/Furious-Gains/FuriousGains/src/Images/uploads/" + user.getImage());
        System.out.println("image prog : " + this.img);
        this.imageP.setImage(this.img);
        roleP.setText(user.getRole());
        cinP.setText(String.valueOf(cin));
        Date date = user.getDateuser();
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        this.date_nC.setValue(localDate);
        adresseP.setText(user.getAdresse());
        emailP.setText(user.getEmail());


    }

    @FXML
    void supp(ActionEvent event) {
        us.delete2(Integer.parseInt(cinP.getText()));
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

}
