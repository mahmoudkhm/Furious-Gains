package esprit.tn.Controllers;

import esprit.tn.Models.Avis;
import esprit.tn.Services.AvisService;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AjouterAvis {
    @FXML
    private TextField id_produitTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField noteTF;
    private final AvisService as = new AvisService();

    @FXML
    private Label remplir;

    @FXML
    void AjouterAvisTF(ActionEvent event) {

        String idUserText = id_userTF.getText();
        String idProduitText = id_produitTF.getText();
        String noteText = noteTF.getText();

        if (idUserText.isEmpty() || idProduitText.isEmpty() || noteText.isEmpty()) {

            System.out.println("Veuillez remplir tous les champs.");
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erreur");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }


        try {
            int idUser = Integer.parseInt(idUserText);
            int idProduit = Integer.parseInt(idProduitText);
            int note = Integer.parseInt(noteText);


            if (note < 1 || note > 5) {
                Alert alert2 =new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("La note doit être entre 1 et 5.");
                alert2.setContentText("Echec d'ajout");
                alert2.showAndWait();
                System.out.println("La note doit être entre 1 et 5.");

            }


            as.ajouter(new Avis(idUser, idProduit, note));

        } catch (NumberFormatException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout");
            alert.showAndWait();

        }
    }
      /*  @FXML
        void AjouterAvisTF(ActionEvent event){
            as.ajouter(new Avis(Integer.parseInt(id_userTF.getText()), Integer.parseInt(id_produitTF.getText()), Integer.parseInt(noteTF.getText())));


        }*/
      @FXML
      void AfficherAvisTF(ActionEvent event) {
          Parent root= null;
          try {
              root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
              noteTF.getScene().setRoot(root);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }

      }

}