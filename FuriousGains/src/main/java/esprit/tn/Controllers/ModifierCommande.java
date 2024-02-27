package esprit.tn.Controllers;

import esprit.tn.Models.Commande;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.CommandeService;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ModifierCommande {

    @FXML
    private ComboBox<String> IdComM;

    @FXML
    private TextField IdProduitTF;

    @FXML
    private TextField MontantTF;

    @FXML
    private TextField IdclientTF;

    @FXML
    private TextField StatutCommandeTF;

    private final CommandeService cs=new CommandeService();

    @FXML
    void afficherc(ActionEvent event) {

        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherCommande.fxml"));
            StatutCommandeTF.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void on_click(ActionEvent event) {
        String selectedValue = IdComM.getValue();
        if (selectedValue != null) {
            int liv = Integer.parseInt(selectedValue);
            Commande commande = cs.getOneByiD(liv);
            IdclientTF.setText(String.valueOf(commande.getId_client()));
            MontantTF.setText(String.valueOf(commande.getMontant_total()));
            StatutCommandeTF.setText(commande.getStatut_commande());
            IdProduitTF.setText(String.valueOf(commande.getId_produit()));}
    }
    @FXML
    void modifierC(ActionEvent event) {

            Alert alertType;
            if (!IdComM.getValue().isEmpty() && !StatutCommandeTF.getText().isEmpty() && !MontantTF.getText().isEmpty()  &&!IdProduitTF.getText().isEmpty()) {
              if (!IdComM.getValue().matches("[A-Z]+") && !this.IdComM.getValue().matches("[a-z]+")){
             if (!IdProduitTF.getText().matches("[A-Z]+") && !MontantTF.getText().matches("[a-z]+")){
                 alertType = new Alert(Alert.AlertType.CONFIRMATION);
                 alertType.setTitle("CONFIRMATION !");
                 alertType.setHeaderText("Voulez-vous vraiment mettre à jour à cet utilisateur ?");
                 Optional<ButtonType> result = alertType.showAndWait();
                 if (result.get() == ButtonType.OK) {
                     String statut = StatutCommandeTF.getText();
                     int idCom= Integer.parseInt(IdComM.getValue());
                     float montant = Float.parseFloat(MontantTF.getText());
                     int idP =Integer.parseInt(IdProduitTF.getText());
                     int idClient =Integer.parseInt(IdclientTF.getText());

                     Commande commande = new Commande( idCom,idClient,statut, montant, idP);
                     cs.modifier(commande);
                     FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/AfficherCommande.fxml"));
                     Parent root = null;
                     try {
                         root = (Parent)loader.load();
                         IdComM.getScene().setRoot(root);

                     } catch (IOException e) {
                         System.out.println("Error:" + e.getMessage());
                     }}
             }else {
                 alertType = new Alert(Alert.AlertType.ERROR);
                 alertType.setTitle("Error");
                 alertType.setHeaderText("montant!");
                 alertType.show();}
                }else {
                    alertType = new Alert(Alert.AlertType.ERROR);
                    alertType.setTitle("Error");
                    alertType.setHeaderText("IDCOM int!");
                    alertType.show();
                }}
                else {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("id m3ibin!");
                alertType.show();
            }


        }
    @FXML
    void initialize() {
        List<Commande> commandes= cs.affichage();
        ObservableList<String> idlist = FXCollections.observableArrayList();
        for (Commande commande : commandes) {
            idlist.add(Integer.toString(commande.getId_command()));
        }
        IdComM.setItems(idlist);

}
}
