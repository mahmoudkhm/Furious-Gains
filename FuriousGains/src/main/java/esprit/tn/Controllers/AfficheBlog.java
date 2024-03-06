package esprit.tn.Controllers;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import esprit.tn.Models.Annonce;
import esprit.tn.Models.Avis;
import esprit.tn.Services.AnnonceService;
import esprit.tn.Services.AvisService;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
public class AfficheBlog implements Initializable {
    private static int i = 0;
    private static Annonce annonce = new Annonce();
    @FXML
    private Label rateP;

    @FXML
    private Button returnP;
    @FXML
    private Button exit;

    @FXML
    private Label descP;

    @FXML
    private BorderPane PP;

    @FXML
    private ImageView PicP;

    @FXML
    private HBox VV;


    @FXML
    private Button suppPost;

    @FXML
    private Button titleeP;
int cin =14519193;

    @FXML
    void modifyP(ActionEvent event) {

    }
    @FXML
    private Label dateP;

    public static Annonce getAnnonce() {
        return annonce;
    }

    public static void setAnnonce(Annonce annonce) {
        AfficheBlog.postt = postt;
    }

    private static Annonce postt;
    private static int idP;

    public static int getIdP() {
        return idP;
    }

    public static void setIdP(int idP) {
        AfficheBlog.idP = idP;
    }
    private Image img;
AnnonceService annonceService =new AnnonceService();
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

       // tfCom.setPromptText("Add your comment..");
        //AfficheBlog.i = 0;

        try {
            annonce= annonceService.getOneById(idP);
            titleeP.setText(annonce.getTitre_annonce());
            descP.setText(annonce.getDescription_annonce());
            this.img = new Image("file:/C:/Users/nada/Desktop/integration/FuriousGains/src/Images/uploads/" + annonce.getImage());
            System.out.println("image prog : " + this.PicP);
            this.PicP.setImage(this.img);
            System.out.println(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale-
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }



    @FXML
    private void retourBlog(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowPosts.fxml"));
        Parent root = loader.load();
        returnP.getScene().setRoot(root);

    }

    @FXML
    private void val1() throws SQLException {

        Avis avis =new Avis();
        AvisService ps = new AvisService();

        avis.setNote(1);

        ps.modifierAvis(avis, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(avis.getNote())) + "/5");

    }

    @FXML
    private void val2() throws SQLException {

        Avis avis =new Avis();
        AvisService ps = new AvisService();

        avis.setNote(2);
        avis.setId_user(1);

        ps.modifierAvis(avis, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(avis.getNote())) + "/5");

    }

    @FXML
    private void val3() throws SQLException {

        Avis avis =new Avis();
        AvisService ps = new AvisService();

        avis.setNote(3);

        ps.modifierAvis(avis, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(avis.getNote())) + "/5");

    }

    @FXML
    private void val4()  {

        Avis avis =new Avis();
        AvisService ps = new AvisService();
        avis.setNote(4);
        ps.modifierAvis(avis, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(avis.getNote())) + "/5");

    }

    @FXML
    private void val5() throws SQLException {

        Avis avis =new Avis();
        AvisService ps = new AvisService();

        avis.setNote(5);

        ps.modifierAvis(avis, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf(df.format(avis.getNote())) + "/5");

    }

    @FXML
    private void clearValue() throws SQLException {

       AvisService ps = new AvisService();

        Avis avis = ps.getonebyid(String.valueOf(idP));
        avis.setId_avis(0);
        ps.modifierAvis(avis, idP);
        DecimalFormat df = new DecimalFormat("#.0");
        rateP.setText(String.valueOf((int) avis.getNote()) + "/5");

    }

    @FXML
    private void modifyP() throws SQLException {
        /*ModifPostController ms = new ModifPostController();
        ms.setIdP(idP);
        // Load the "ModifFournisseur.fxml" file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifPost.fxml"));
        try {
            PostService ps = new PostService();

            Post post = ps.getOneById(idP);

            Parent root = loader.load();
            ModifPostController controller = loader.getController();

            // Set the text fields in the "ModifFournisseur.fxml" file with the values from the labels
            controller.titlemodP.setText(post.getTitle());
            controller.descmodP.setText(post.getDetails());
            controller.setPostt(post);
            System.out.println(controller.getPostt().toString());
            // Show the "ModifFournisseur.fxml" file in a new stage
            Scene scene = titleeP.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @FXML
    private void DeleteP(javafx.event.ActionEvent event) throws IOException, SQLException {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Post");
        alert.setHeaderText("Are you sure you want to delete this post?");
        alert.setContentText("This action is required!");
        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        // If the user clicks "OK", delete the fournisseur
        if (result.get() == ButtonType.OK) {

            annonceService.supprimer(annonce.getId_annonce());
            ShowPostsController.i = 0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowPosts.fxml"));
            Parent root = loader.load();
            suppPost.getScene().setRoot(root);
        } else {
            // Close the dialog and do nothing
            alert.close();
        }

    }


}
