package esprit.tn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Admin {
    @FXML
    private Button blog;

    @FXML
    private ToggleButton btn_workbench;

    @FXML
    private ToggleButton btn_workbench1;

    @FXML
    private ToggleButton btn_workbench11;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    private AnchorPane viewPages;

    @FXML
    void afficher(ActionEvent event) {
        Parent fxml;
        if (event.getSource() == this.btn_workbench) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/Profil.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (event.getSource() == this.btn_workbench1) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AffichageAdmin.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}

    }
    @FXML
    void initialize(){
        try {
            Parent fxml;
            fxml = (Parent) FXMLLoader.load(getClass().getResource("/Profil.fxml"));
            viewPages.getChildren().removeAll(new Node[0]);
            viewPages.getChildren().setAll(new Node[]{fxml});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
