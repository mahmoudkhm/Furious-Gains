package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Securite.BCrypt;
import esprit.tn.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifierPWD implements Initializable {

    @FXML
    private PasswordField pwd1;
int cin ;
    @FXML
    private PasswordField pwd2;
    @FXML
    private Label ver;

    @FXML
    private Label ver1;
    @FXML
    private TextField pwda1;

    @FXML
    private TextField pwda2;


    @FXML
    private CheckBox selected1;
    @FXML
    void ModifierMDP(ActionEvent event) {
        if (pwd1.isVisible()){
            pwda1.setText(pwd1.getText());
        }
        if (pwda2.isVisible()){
            pwd2.setText(pwda2.getText());
        }
        if (pwd2.isVisible()){
            pwda2.setText(pwd2.getText());
        }
        if (pwda1.isVisible()){
            pwd1.setText(pwda1.getText());
        }
        if (pwda1.getText().equals(pwda2.getText())&&pwd1.getText().equals(pwd2.getText())){

            String hashedPassword = BCrypt.hashpw(pwd1.getText(), BCrypt.gensalt());
            User u;
            UserService us =new UserService();

            ver1.setText(String.valueOf(cin));
            Alert alertType1 = new Alert(Alert.AlertType.CONFIRMATION);
            alertType1.setTitle("CONFIRMATION !");
            alertType1.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
            Optional<ButtonType> result = alertType1.showAndWait();
            if (result.get() == ButtonType.OK) {
                us.setMotDePasse(cin, hashedPassword);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
                    pwd1.getScene().setRoot(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }else {     Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Modifier ");
            alertType.setContentText("mot de passe invalide.");
            alertType.show();

        }


    }
    @FXML
    private CheckBox selected2;
    @FXML
    void selected2(ActionEvent event) {
        if (selected2.isSelected()){
            pwda2.setText(pwd2.getText());
            pwda2.setVisible(true);
            pwd2.setVisible(false);
        }else {
            pwd2.setText(pwda2.getText());
            pwda2.setVisible(false);
            pwd2.setVisible(true);
        }
    }

    @FXML
    void selected1(ActionEvent event) {
        if (selected1.isSelected()){
            pwda1.setText(pwd1.getText());
            pwda1.setVisible(true);
            pwd1.setVisible(false);
        }else {
            pwd1.setText(pwda1.getText());
            pwda1.setVisible(false);
            pwd1.setVisible(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cin =ReinitialiserPwd.getCin();
        ver.setText(String.valueOf(cin));
    }
}
