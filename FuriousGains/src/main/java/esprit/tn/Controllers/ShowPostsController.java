/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.tn.Controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import esprit.tn.Models.Annonce;
import esprit.tn.Services.AnnonceService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class ShowPostsController implements Initializable {

    public static int i = 0;
    private Image img;

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane Empty;
    @FXML
    private BorderPane borderPost;
    @FXML
    private Label rateP;
    @FXML
    private Label postNbr;

    @FXML
    private Label commentP;

    @FXML
    private Button ajoutPP;
    @FXML
    private Button exit;

    @FXML
    private Label descP;

    @FXML
    private Label dateP;

    @FXML
    private Button titleP;

    @FXML
    private ImageView imageP;

    @FXML
    private Button newtP;

    @FXML
    private Button PreviousP;
    @FXML
    private Button returnP;
    private static List<Annonce> lAnnonces;

    public void initialize(URL url, ResourceBundle rb) {
            ShowPostsController.lAnnonces = new AnnonceService().affichage();
            Collections.reverse(ShowPostsController.lAnnonces);


        showPost();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    AnnonceService ps = new AnnonceService();
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
        i++;
        if (ShowPostsController.lAnnonces.size() == i) {
            i = 0;

            showPost();

        } else {
            showPost();

        }
    }
    ));
@FXML 
private Label userNom;
    public void showPost() {

        try {

            if (ShowPostsController.lAnnonces.size() == 0) {
                Empty.getChildren().clear();
                Pane pane = new Pane();
                Label label2 = new Label("Sorry, no Post for today.");
                label2.setAlignment(Pos.CENTER);
                label2.setLayoutY(69.0);
                label2.setPrefHeight(93.0);
                label2.setPrefWidth(458.0);
                label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1 0 0 0;");
                label2.setFont(Font.font("Calibri Italic", 30));
                label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
                pane.getChildren().addAll(label2, ajoutPP);
                Empty.getChildren().add(pane);
            } else {
                Annonce post = ShowPostsController.lAnnonces.get(i);
                int NbrComments = ps.showComments(ShowPostsController.lAnnonces.get(i).getId_annonce()).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                postNbr.setText(i + 1 + "#");
                titleP.setText(post.getTitre_annonce());
                dateP.setText(post.getDescription_annonce());
               // descP.setText(post.getImage());
                // a = new UserService();
                //User c=a.findById(ShowPostsController.posts.get(i).getUserId());
                userNom.setText(post.getTitre_annonce());
                this.img = new Image("file:/C:/Users/nada/Desktop/integration/FuriousGains/src/Images/uploads/" + post.getImage());
                System.out.println("image prog : " + this.imageP);
                this.imageP.setImage(this.img);
                int rate=5;
                if (rate == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) rate) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(rate)) + "/5");
                }

            }
        } catch (SQLException ex) {
           // Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void nextPost() {
        i++;

        try {

            if (ShowPostsController.lAnnonces.size() == i) {
                i = 0;

                showPost();
            } else {
                Annonce post = ShowPostsController.lAnnonces.get(i);
                int NbrComments = ps.showComments(ShowPostsController.lAnnonces.get(i).getId_annonce()).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                postNbr.setText(i + 1 + "#");
                titleP.setText(post.getTitre_annonce());
                //dateP.setText(post.getId_user());
                this.img = new Image("C:/Users/nada/Desktop/integration/FuriousGains/src/Images/uploads/" +post.getImage());
                System.out.println("image prog : " + this.imageP);
                this.imageP.setImage(this.img);
                descP.setText(post.getDescription_annonce());
               /* if (post.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) post.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(post.getNbrRate())) + "/5");
                }*/
                System.out.println(post.getId_user());
            }
        } catch (SQLException ex) {
           // Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void PreviousPost() {
        i--;

        try {

            if (i < 0) {

                i = ShowPostsController.lAnnonces.size() - 1;

                showPost();

            } else {
                Annonce post = ShowPostsController.lAnnonces.get(i);
                int NbrComments = ps.showComments(ShowPostsController.lAnnonces.get(i).getId_annonce()).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                postNbr.setText(i + 1 + "#");
                titleP.setText(post.getTitre_annonce());
               // dateP.setText(post.getDate_post());
                this.img = new Image("C:/Users/nada/Desktop/Prosit9/Furious-Gains-gestion_blog/FuriousGains/src/Images/uploads/" + post.getImage());
                System.out.println("image prog : " + this.imageP);
                this.imageP.setImage(this.img);
                descP.setText(post.getDescription_annonce());
                /*if (post.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) post.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(post.getNbrRate())) + "/5");
                }*/
                System.out.println(post.getId_user());
            }
        } catch (SQLException ex) {
          //  Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

    @FXML
    private void AjoutPost(javafx.event.ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterAnnonce.fxml"));
        Parent root = loader.load();
        ajoutPP.getScene().setRoot(root);

    }

    @FXML
    private void detailP() throws SQLException {
       try {
            timeline.stop();
            List<Annonce> lAnnonces;
            lAnnonces = new AnnonceService().affichage();
            Collections.reverse(lAnnonces);
            Annonce annonce = lAnnonces.get(i);
            AfficheBlog b = new AfficheBlog();
            b.setIdP(annonce.getId_annonce());
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficheBlog.fxml"));
           Parent signInRoot = null;
               signInRoot = loader.load();
               Scene signInScene = new Scene(signInRoot);
               // Create a new stage for the forget password window
               Stage forgetPwdStage = new Stage();
               forgetPwdStage.initModality(Modality.APPLICATION_MODAL); // Set modality to APPLICATION_MODAL
               forgetPwdStage.setScene(signInScene);
               forgetPwdStage.showAndWait();


        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
