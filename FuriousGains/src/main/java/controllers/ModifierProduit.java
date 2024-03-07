package controllers;

import esprit.tn.Models.Produit;
import esprit.tn.Utils.MyConnexion;
import esprit.tn.services.ProduitService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.*;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ModifierProduit {
    @FXML
    private ComboBox<String> idP;

    @FXML
    private Label labelAfficheC;

    @FXML
    private Label labelAfficheP;

    @FXML
    private Label labelAjoutC;

    @FXML
    private Label labelAjoutP;



    @FXML
    private TextField descriptionP;

    @FXML
    private TextField nomP;

    @FXML
    private TextField prixP;

    @FXML
    private TextField quantiteP;
    Connection cnx = MyConnexion.getInstance().getCnx();
   private final ProduitService ps = new ProduitService();
   private final Produit p=new Produit();

    @FXML
    void AfficherP(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProduit.fxml"));
        try {
            Parent root = loader.load();
            AfficherProduit controller = loader.getController();
            labelAfficheP.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void AjouterP(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProduit.fxml"));
        try {
            Parent root = loader.load();
            AjouterProduit controller = loader.getController();
            labelAjoutP.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void AfficherC(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCategorie.fxml"));
        try {
            Parent root = loader.load();
            AfficherCategorie controller = loader.getController();
            labelAfficheC.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void AjouterC(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
        try {
            Parent root = loader.load();
            AjouterCategorie controller = loader.getController();
            labelAjoutC.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

        @FXML
        void UpdateP(ActionEvent event) {

            try {
                // Récupérer les valeurs des champs de texte
                int id = Integer.parseInt(String.valueOf(idP.getValue()));
                String nom = nomP.getText();
                int quantite = Integer.parseInt(quantiteP.getText());
                float prix = Integer.parseInt(prixP.getText());
                String desc = descriptionP.getText();
               // int idC = Integer.parseInt(String.valueOf(categorieP.getText()));

                if (quantite <= 0 || prix <= 0) {
                    //showAlert("Erreur", "La quantité et le prix doivent être supérieurs à zéro.");
                    Notifications.create()
                            .title("Erreur")
                            .text("La quantité et le prix doivent être supérieurs à zéro.")
                            .showInformation();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("La quantité et le prix doivent être supérieurs à zéro.");
                    alert.showAndWait();
                    return;
                }
                if (!isAlpha(nom) || !isAlpha(desc)) {
                    Notifications.create()
                            .title("Erreur")
                            .text("Les champs nom et description ne doivent contenir que des lettres.")
                            .showInformation();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("Les champs nom et description ne doivent contenir que des lettres.");
                    alert.showAndWait();
                    return;
                }
                // Créer un nouvel compte avec les valeurs récupérées
                Produit p = new Produit(id, nom, quantite, prix, desc);

                // Appeler la méthode de mise à jour du compte

                    // Create a confirmation dialog
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setTitle("Confirmation");
                    dialog.setContentText("Are you sure you want to update this product?");
                    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                    // Show the dialog and wait for the admin's response
                    Optional<ButtonType> result = dialog.showAndWait();

                    // If the admin chooses "Yes", delete the product
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        ps.modifier(p);

                        // Afficher une alerte en fonction du résultat de la mise à jour
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Compte updated");
                        alert.showAndWait();
                    }

                } catch (NumberFormatException e) {
                    // Gérer l'exception si la conversion échoue
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Veuillez entrer des valeurs numériques valides.");
                    alert.showAndWait();
                }
            }


    @FXML
    void combo(ActionEvent event) {
        String selectedValue = String.valueOf(idP.getValue());
        if (selectedValue != null) {
            int idd = Integer.parseInt(selectedValue);

              Produit p =ps.getOneById(idd);
              nomP.setText(p.getMarque_produit());
              prixP.setText(String.valueOf(p.getPrix_produit()));
              quantiteP.setText(String.valueOf(p.getQuantite()));
              descriptionP.setText(p.getDescription());
              //categorieP.setText(String.valueOf(p.getId_categorie()));



                //ps.execute();
               // System.out.println("Product updated successfully!");


        }
        }
    @FXML
    void initialize() {
        List<Produit> p = ps.affichage();
        ObservableList<String> id = FXCollections.observableArrayList();
        for (Produit produit : p) {
            id.add(Integer.toString(produit.getId_produit()));
        }
        idP.setItems(id);
    }


    public void UpdateRedP(MouseEvent mouseEvent) {
    }
}

