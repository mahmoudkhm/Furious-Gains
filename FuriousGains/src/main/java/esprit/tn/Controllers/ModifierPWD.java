package esprit.tn.Controllers;

import esprit.tn.Models.User;
import esprit.tn.Securite.BCrypt;
import esprit.tn.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModifierPWD {

    @FXML
    private PasswordField pwd1;

    @FXML
    private PasswordField pwd2;

    @FXML
    private TextField pwda1;

    @FXML
    private TextField pwda2;
    @FXML
    private CheckBox selected;

    @FXML
    private CheckBox selected1;
    @FXML
    void ModifierMDP(ActionEvent event) {
        if (pwda1.getText().equals(pwda2.getText())&&pwd1.getText().equals(pwd2.getText())){

            String hashedPassword = BCrypt.hashpw(pwd1.getText(), BCrypt.gensalt());
            User u;
            UserService us =new UserService();
            u=us.getOneBy("password",hashedPassword);
            Alert alertType1 = new Alert(Alert.AlertType.CONFIRMATION);
            alertType1.setTitle("CONFIRMATION !");
            alertType1.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
            Optional<ButtonType> result = alertType1.showAndWait();
            if (result.get() == ButtonType.OK) {
            try {
                us.setMotDePasse(ReinitialiserPwd.getCin(), hashedPassword);
                Alert alertType = new Alert(Alert.AlertType.INFORMATION);
                alertType.setTitle("Modifier avec succee");
                alertType.setContentText("done.");
                alertType.show();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
                    pwd1.getScene().setRoot(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}
        }else {                Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Modifier ");
            alertType.setContentText("mot de passe invalide.");
            alertType.show();

        }


    }

    @FXML
    void selected(ActionEvent event) {
        if (selected.isSelected()){
            pwda2.setText(pwd2.getText());
            pwda2.setVisible(true);
            pwd2.setVisible(false);
        }else {
            pwd2.setText(pwda2.getText());
            pwda2.setVisible(true);
            pwd2.setVisible(false);
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
            pwda1.setVisible(true);
            pwd1.setVisible(false);
        }

    }

}
