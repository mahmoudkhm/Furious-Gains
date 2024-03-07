package esprit.tn.Controllers;

import esprit.tn.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

public class Front {
    @FXML
    private Button blogB;

    @FXML
    private Button ajoutLiv;


    @FXML
    private Button AjouterAnnonce1;

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
    private Button listR;

    @FXML
    private Button ModifierProfil;

    @FXML
    private Button afficherB;


    @FXML
    private Button nutritionB1;
    @FXML
    private Button blogBAnnonce1;
    @FXML
    private TitledPane butt;
    int clickCount ;
    @FXML
    private Button commandajout;

    @FXML
    private Button commandeAffichage;

    @FXML
    private Button livraisonAff;

    @FXML
    private Button ajres;
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
        if (event.getSource() == this.blogB) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/ShowPosts.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.AjouterAnnonce1) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AjouterAnnonce.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.commandeAffichage) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AfficherCommande.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.livraisonAff) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AfficherLivraison.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.commandajout) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AjouterCommande.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.nutritionB) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AfficherRegime.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.nutritionB1) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AfficherRecette.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (event.getSource() == this.ajoutLiv) {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/AjouterLivraison.fxml"));
                viewPages.getChildren().removeAll(new Node[0]);
                viewPages.getChildren().setAll(new Node[]{fxml});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        }





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
    private Preferences preferences;

    @FXML
    void logout(ActionEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();

            // Get the LoginController instance from the loader
            Login loginController = loader.getController();

            // Notify the LoginController about the disconnection event
            DisconnectEvent disconnectEvent = new DisconnectEvent();
            loginController.logout(disconnectEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent signInRoot = null;
        try {
            signInRoot = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene signInScene = new Scene(signInRoot);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signInScene);
        window.show();
    }

}
