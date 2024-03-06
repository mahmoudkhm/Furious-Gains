package esprit.tn.Controllers;

import esprit.tn.Models.Livraison;
import esprit.tn.Models.sms;
import esprit.tn.Services.LivraisonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AjouterLivraison {
    @FXML
    private Button payer_button;
    @FXML
    private DatePicker DateLivraisonTF;

    @FXML
    private TextField AdresseLivraisonTF;

    @FXML
    private TextField IdClientTF;

    @FXML
    private TextField IdCommandeTF;

    @FXML
    private TextField ModeLivraisonTF;

    @FXML
    private TextField MontantTF;

    @FXML
    private TextField StatutLivraisonTF;


    private final LivraisonService ls =new LivraisonService();
   /* @FXML
    void AjouterTF(ActionEvent event) {
        ls.ajouter(new Livraison(Integer.parseInt(IdCommandeTF.getText()),DateLivraisonTF.getText(),StatutLivraisonTF.getText(), AdresseLivraisonTF.getText(), Float.parseFloat( MontantTF.getText()), ModeLivraisonTF.getText(),Integer.parseInt(IdClientTF.getText())));

    }*/
   @FXML
   void AjouterTF(ActionEvent event) {
       LocalDate localDate = (LocalDate)this.DateLivraisonTF.getValue();
       Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
       Date dateliv = Date.from(instant);
       Alert alertType;
       if (!IdCommandeTF.getText().isEmpty() && !StatutLivraisonTF.getText().isEmpty() && !AdresseLivraisonTF.getText().isEmpty()  && !ModeLivraisonTF.getText().isEmpty() && !IdClientTF.getText().isEmpty()&& !MontantTF.getText().isEmpty()  ) {
           if (this.IdCommandeTF.getText().matches("[a-z]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("id client doit être un numero et non une chaine!");
               alertType.show();
           }else if (IdClientTF.getText().matches("[a-z]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("id commande doit être un numero et non une chaine! !");
               alertType.show();
           }
           else if (this.ModeLivraisonTF.getText().matches("[0-9]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("mode de livraison doit etre une chaine et non et numero !");
               alertType.show();
           } else if (this.StatutLivraisonTF.getText().matches("[0-9]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("statut doit être une chaîne et non un numéro !");
               alertType.show();
           } else if (this.MontantTF.getText().matches("[a-z]+")||MontantTF.getText().matches("[A-Z]+")) {
               alertType = new Alert(Alert.AlertType.ERROR);
               alertType.setTitle("Error");
               alertType.setHeaderText("Montant doit être un numero et non une chaine !");
               alertType.show();
           }else
               {Livraison livraison= new Livraison(Integer.parseInt(IdCommandeTF.getText()),dateliv,StatutLivraisonTF.getText(), AdresseLivraisonTF.getText(), Float.parseFloat( MontantTF.getText()), ModeLivraisonTF.getText(),Integer.parseInt(IdClientTF.getText()));
                   ls.ajouter(new Livraison(Integer.parseInt(IdCommandeTF.getText()),dateliv,StatutLivraisonTF.getText(), AdresseLivraisonTF.getText(), Float.parseFloat( MontantTF.getText()), ModeLivraisonTF.getText(),Integer.parseInt(IdClientTF.getText())));
                   sms.sms(livraison,"\n Furious Gains:","+21621174221");
               }
       }
       else
               {alertType = new Alert(Alert.AlertType.ERROR);
                   alertType.setTitle("Champs vide!!! ");
                   alertType.show();
               }


   }
    @FXML
    void afficherlivraison(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherLivraison.fxml"));
            IdCommandeTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void payer(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/PaymentController.fxml"));
            payer_button.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
