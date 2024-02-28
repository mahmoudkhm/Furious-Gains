package esprit.tn.Controllers;

import esprit.tn.Models.Commande;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.LivraisonService;
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
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ModifierLivraison {
    @FXML
    private TextField AdresseLivraisonTF;

    @FXML
    private TextField DateLivraisonTF;

    @FXML
    private TextField IdClientTF;

    @FXML
    private TextField rechercherlivraison;

    @FXML
    private TextField IdCommandeTF;

    @FXML
    private ComboBox<String> IdM;

    @FXML
    private TextField ModeLivraisonTF;

    @FXML
    private TextField MontantTF;

    @FXML
    private TextField StatutLivraisonTF;
    private final LivraisonService ls=new LivraisonService();

    @FXML
    void ModifierTF(ActionEvent event) {
        Alert alertType;
        if (!IdCommandeTF.getText().isEmpty() && !DateLivraisonTF.getText().isEmpty() && !StatutLivraisonTF.getText().isEmpty() && !AdresseLivraisonTF.getText().isEmpty() && !IdM.getValue().isEmpty() && !ModeLivraisonTF.getText().isEmpty() && !IdClientTF.getText().isEmpty()&& !MontantTF.getText().isEmpty()  ) {
            if (this.StatutLivraisonTF.getText().matches("[0-9]+")) {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("Status doit être une chaîne et non un numéro!");
                alertType.show();
            }
                    else if (this.ModeLivraisonTF.getText().matches("[0-9]+")) {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("mode de livraison must be string not number !");
                    alertType.show();
            } else if (this.StatutLivraisonTF.getText().matches("[0-9]+")) {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("statut doit être une chaîne et non un numéro !");
                alertType.show();
            } else if (!IdM.getValue().matches("[A-Z]+") && !this.IdM.getValue().matches("[a-z]+"))   {
                if (!DateLivraisonTF.getText().matches("[A-Z]+") && !DateLivraisonTF.getText().matches("[a-z]+") )
                    {
                        alertType = new Alert(Alert.AlertType.CONFIRMATION);
                        alertType.setTitle("CONFIRMATION !");
                        alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                        Optional<ButtonType> result = alertType.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            String date = DateLivraisonTF.getText();
                            String statut = StatutLivraisonTF.getText();
                            int idCom = Integer.parseInt(IdCommandeTF.getText());
                            String mode = ModeLivraisonTF.getText();
                            int idL = Integer.parseInt(IdM.getValue());
                            float montant = Float.parseFloat(MontantTF.getText());
                            int idClient =Integer.parseInt(IdClientTF.getText());
                            String adresse = AdresseLivraisonTF.getText();
                            Livraison livraison = new Livraison( idL,  idCom, date,statut, adresse, montant, mode,idClient);
                            LivraisonService ls = new LivraisonService();
                            ls.modifier(livraison);
                            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherLivraison.fxml"));


                            Parent root = null;
                            try {
                                root = (Parent)loader.load();
                                IdM.getScene().setRoot(root);

                            } catch (IOException e) {
                                System.out.println("Error:" + e.getMessage());
                            }

                        }
                    } else {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("date doit être une chaîne et non un numéro.");
                    alertType.show();
                }
            } else {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("id livraison doit être une chaîne et non un numéro");
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
    void afficherlivraison(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherLivraison.fxml"));
            IdCommandeTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void on_click(ActionEvent event) {
        String selectedValue = IdM.getValue();
        if (selectedValue != null) {
            String liv = selectedValue;
            Livraison livraison = ls.getOneByid(liv);
            IdClientTF.setText(String.valueOf(livraison.getId_client()));
            StatutLivraisonTF.setText(livraison.getStatut_livraison());
            MontantTF.setText(String.valueOf(livraison.getMontant_paiement()));
            AdresseLivraisonTF.setText(livraison.getAdresse_livraison());
            DateLivraisonTF.setText(livraison.getDate_livraison());
            IdCommandeTF.setText(String.valueOf(livraison.getId_commande()));
            ModeLivraisonTF.setText(livraison.getMode_livraison());}
    }

    @FXML
    void initialize() {
        List<Livraison> livraisons = ls.affichage();
        ObservableList<String> idlist = FXCollections.observableArrayList();
        for (Livraison livraison : livraisons) {
            idlist.add(Integer.toString(livraison.getId_livraison()));
        }
        IdM.setItems(idlist);
    }
}
