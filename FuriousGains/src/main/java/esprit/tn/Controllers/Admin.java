package esprit.tn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Admin {

    @FXML
    private TitledPane butt;
    @FXML
    private Button eventB;
    @FXML
    private Button profilB;
    @FXML
    private Button listR;

    @FXML
    private Button ajres;

    @FXML
    private Button ModifierProfil;

    @FXML
    private Button afficherB;

    @FXML
    private AnchorPane viewPages;

    @FXML
    void afficher(ActionEvent event) {
        Parent fxml;
        if (event.getSource() == this.profilB) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/Profil.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (event.getSource() == this.afficherB) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/Profil.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.ModifierProfil) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/ModifierUser.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (event.getSource() == this.eventB) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.listR) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AfficherReservation.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.ajres) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AjouterReservation.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    int clickCount ;
    @FXML
    void onclick(MouseEvent event) {

        clickCount ++;
        // Augmente le compteur de clics

        if (clickCount % 2 == 0) {
            // Si le nombre de clics est pair, définissez la hauteur préférée sur 29
            butt.setPrefHeight(29);
        } else {
            // Si le nombre de clics est impair, définissez la hauteur préférée sur 79
            butt.setPrefHeight(79);
        }

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
