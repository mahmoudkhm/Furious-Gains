package esprit.tn.Controllers;

import esprit.tn.Models.Annonce;
//import esprit.tn.Models.Annonces;
import esprit.tn.Services.AnnonceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ModifierAnnonce {
    @FXML
    private TextArea desM;

    @FXML
    private ComboBox<String> idAnnM;

    @FXML
    private TextField iduserM;

    @FXML
    private TextField titreM;
private final AnnonceService ass=new AnnonceService();


    @FXML
    void AfficherAnnonce(ActionEvent event) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherAnnonce.fxml"));
            iduserM.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleComboBoxSelection(ActionEvent event) {

        String selectedValue = idAnnM.getValue();
        if (selectedValue != null) {
            int cin = Integer.parseInt(selectedValue);}

    }
    @FXML
    void ModifierTF(ActionEvent event) {
        Alert alertType;
        if (!titreM.getText().isEmpty() && !iduserM.getText().isEmpty() && !desM.getText().isEmpty() && !idAnnM.getValue().isEmpty()) {
            if (!idAnnM.getValue().matches("[A-Z]+") && !this.idAnnM.getValue().matches("[a-z]+")) {
                if (!iduserM.getText().matches("[A-Z]+") && !iduserM.getText().matches("[a-z]+") ) {
                        alertType = new Alert(Alert.AlertType.CONFIRMATION);
                        alertType.setTitle("CONFIRMATION !");
                        alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                        Optional<ButtonType> result = alertType.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            String  des = desM.getText();
                            String  titre = titreM.getText();
                            int idproduit = Integer.parseInt(iduserM.getText());
                            int idAnnonce = Integer.parseInt(idAnnM.getValue());
                            Annonce annonces = new Annonce(idAnnonce,titre,des,idproduit );
                            ass.modifier(annonces);
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherAnnonce.fxml"));


                            Parent root = null;

                            try {
                                root = (Parent)loader.load();
                                desM.getScene().setRoot(root);
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
        List<Annonce> lannonce = ass.affichage();
        ObservableList<String> annoncelist = FXCollections.observableArrayList();
        for (Annonce annonces : lannonce) {
            annoncelist.add(Integer.toString(annonces.getId_annonce()));
        }
        idAnnM.setItems(annoncelist);
    }
}
