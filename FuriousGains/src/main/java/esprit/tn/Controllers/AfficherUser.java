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
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AfficherUser {


    @FXML
    private ListView<User> listUser;

    @FXML
    private TextField sup_cin;


    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listUser.getSelectionModel().getSelectedItem().getCin();
        sup_cin.setText(String.valueOf(selectedItem));
    }

    @FXML

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
        /*List<User> users= null;
        users = us.afficher();
        ObservableList<User> observableList= FXCollections.observableList(users);
        tableUser.setItems(observableList);
        cinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        num_telCol.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCole.setCellValueFactory(new PropertyValueFactory<>("role"));*/
        List<User> users1= null;
        users1 = us.afficher();
        ObservableList<User> observableList = FXCollections.observableList(users1);
        listUser.setItems(observableList);
    }



}
