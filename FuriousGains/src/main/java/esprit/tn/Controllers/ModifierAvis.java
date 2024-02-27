package esprit.tn.Controllers;
import esprit.tn.Models.Avis;
import esprit.tn.Services.AvisService;
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

public class ModifierAvis {
    @FXML
    private ComboBox<String> idAvisM;

    @FXML
    private TextField id_produitTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField noteTF;
    private final AvisService as=new AvisService();

    @FXML
    void AfficherAvis(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAvis.fxml"));
            noteTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {
        List<Avis> avisList = as.affichage();
        ObservableList<String> avisList2 = FXCollections.observableArrayList();
        for (Avis avis : avisList) {
            avisList2.add(Integer.toString(avis.getId_avis()));
        }
        idAvisM.setItems(avisList2);
    }
    @FXML
    void ModifierAvisTF(ActionEvent event) {
        Alert alertType;
        if (!noteTF.getText().isEmpty() && !id_userTF.getText().isEmpty() && !id_produitTF.getText().isEmpty() && !idAvisM.getValue().isEmpty()) {
            if (!idAvisM.getValue().matches("[A-Z]+") && !this.idAvisM.getValue().matches("[a-z]+")) {
                if (!id_produitTF.getText().matches("[A-Z]+") && !id_produitTF.getText().matches("[a-z]+") ) {

                    alertType = new Alert(Alert.AlertType.CONFIRMATION);
                    alertType.setTitle("CONFIRMATION !");
                    alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                    Optional<ButtonType> result = alertType.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        int note = Integer.parseInt(noteTF.getText());
                        int idproduit = Integer.parseInt(id_produitTF.getText());
                        int idAvis = Integer.parseInt(idAvisM.getValue());
                        int iduser = Integer.parseInt(id_userTF.getText());
                        Avis avis = new Avis(idAvis,iduser,idproduit,note);
                        as.modifier(avis);
                        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherAvis.fxml"));


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
    void handleComboBoxSelection(ActionEvent event) {

        String selectedValue = idAvisM.getValue();
        if (selectedValue != null) {
            int cin = Integer.parseInt(selectedValue);
    }}


}
