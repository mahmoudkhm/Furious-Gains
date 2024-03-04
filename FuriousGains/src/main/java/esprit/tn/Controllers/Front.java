package esprit.tn.Controllers;

import esprit.tn.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Front {
    @FXML
    private Button blogB;

    @FXML
    private Button eventB;

    @FXML
    private Button livraisonB;

    @FXML
    private Button nutritionB;

    @FXML
    private Button produitB;

    @FXML
    private AnchorPane viewPages;

    @FXML
    private Button profilB;
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

        }




    @FXML
    private Button ModifierProfil;

    @FXML
    private Button afficherB;
    @FXML
    private TitledPane butt;
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

    @FXML
    void afficherM(ActionEvent event) {
        try {
            Parent fxml = (Parent) FXMLLoader.load(getClass().getResource("/ModClient.fxml"));
            viewPages.getChildren().removeAll(new Node[0]);
            viewPages.getChildren().setAll(new Node[]{fxml});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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


}
