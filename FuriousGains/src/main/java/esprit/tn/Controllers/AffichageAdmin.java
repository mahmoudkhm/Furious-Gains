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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AffichageAdmin {


    @FXML
    private ListView<User> listUser;

    @FXML
    private TextField sup_cin;
    @FXML
    private TextField chercherparnom;

    @FXML
    void cherhcre(KeyEvent event) {
        String cinText = chercherparnom.getText();
        if (!cinText.isEmpty()) {
            int cin = Integer.parseInt(cinText);
            User user = us.getOneByCin(cin);
            if (user != null) {
                ObservableList<User> observableList = FXCollections.observableArrayList(user);
                listUser.setItems(observableList);
            } else {
                listUser.setItems(FXCollections.emptyObservableList());
            }
        } else {
            List<User> users1= null;
            users1 = us.afficher();
            ObservableList<User> observableList = FXCollections.observableList(users1);
            listUser.setItems(observableList);}
    }
    @FXML
    void affichmod(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ModifierUser.fxml"));
            sup_cin.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listUser.getSelectionModel().getSelectedItem().getCin();
        sup_cin.setText(String.valueOf(selectedItem));
    }

    @FXML
    void retour(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin.fxml"));
            sup_cin.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private final UserService us =new UserService();
    @FXML


    void supprission(ActionEvent event) {
        us.delete2(Integer.parseInt(sup_cin.getText()));
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherUser.fxml"));
            sup_cin.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    @FXML
    void initialize(){

        List<User> users1= null;
        users1 = us.afficher();
        ObservableList<User> observableList = FXCollections.observableList(users1);
        listUser.setItems(observableList);
    }

}
