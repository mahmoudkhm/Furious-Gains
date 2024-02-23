package esprit.tn.Controllers;

import esprit.tn.Models.Annonces;
import esprit.tn.Services.AnnonceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ModifierAnnonce {
    @FXML
    private ComboBox<String> idM;

    @FXML
    private TextField id_produitTF;
    @FXML
    private TextField noteTF;

    @FXML
    private TextField id_userTF;
private final AnnonceService ass=new AnnonceService();
    @FXML
    void AfficherAnnonce(ActionEvent event) {

    }

    @FXML
    void handleComboBoxSelection(ActionEvent event) {

        String selectedValue = idM.getValue();
        if (selectedValue != null) {
            int cin = Integer.parseInt(selectedValue);}

    }
    @FXML
    void ModifierTF(ActionEvent event) {
        Alert alertType;
        if (!noteTF.getText().isEmpty() && !id_userTF.getText().isEmpty() && !id_produitTF.getText().isEmpty() && !idM.getValue().isEmpty()) {
            if (!idM.getValue().matches("[A-Z]+") && !this.idM.getValue().matches("[a-z]+")) {
                if (!id_produitTF.getText().matches("[A-Z]+") && !id_produitTF.getText().matches("[a-z]+") ) {

                        alertType = new Alert(Alert.AlertType.CONFIRMATION);
                        alertType.setTitle("CONFIRMATION !");
                        alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                        Optional<ButtonType> result = alertType.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            int note = Integer.parseInt(noteTF.getText());
                            int idproduit = Integer.parseInt(id_produitTF.getText());
                            int idAnnonce = Integer.parseInt(idM.getValue());
                            int iduser = Integer.parseInt(id_userTF.getText());
                            Annonces annonces = new Annonces( idAnnonce,iduser,  idproduit, note);
                            ass.modifier(annonces);
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherAnnonce.fxml"));


                            Parent root = null;

                            try {
                                root = (Parent)loader.load();
                                noteTF.getScene().setRoot(root);
                            } catch (IOException e) {
                                System.out.println("Error:" + e.getMessage());
                            }

                        }

                } else {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("Le id produit  doit être un numéro et non une chaîne.");
                    alertType.show();
                }
            } else {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("L'id Annonce doit être un numéro et non une chaîne.");
                alertType.show();
            }
        } else {
            alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Enter a valid content !");
            alertType.show();
        }




    }
    @FXML
    void initialize() {
        List<Annonces> lannonce = ass.affichage();
        ObservableList<String> annoncelist = FXCollections.observableArrayList();
        for (Annonces annonces : lannonce) {
            annoncelist.add(Integer.toString(annonces.getId_annonces()));
        }
        idM.setItems(annoncelist);
    }
}
