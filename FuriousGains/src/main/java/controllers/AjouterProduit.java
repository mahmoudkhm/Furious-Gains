package controllers;

import esprit.tn.Models.Produit;
import esprit.tn.services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import javafx.scene.control.*;

import java.io.IOException;
import java.lang.IllegalArgumentException;


import java.sql.SQLException;

public class AjouterProduit {

    @FXML
    private TextField categorieP;

    @FXML
    private TextField descriptionP;

    @FXML
    private TextField nomP;

    @FXML
    private TextField prixP;

    @FXML
    private TextField quantiteP;
    @FXML
    private Label labelAfficheC;

    @FXML
    private Label labelAfficheP;

    @FXML
    private Label labelAjoutC;

    @FXML
    private Label labelAjoutP;
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
    void AjouterRedP(MouseEvent event) {
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

    private final ProduitService ps = new ProduitService();

    @FXML

    void AjouterP(ActionEvent event) {
        try {
            String nom = nomP.getText();
            String quantiteStr = quantiteP.getText();
            String prixStr = prixP.getText();
            String description = descriptionP.getText();
            String categorieStr = categorieP.getText();

            // Vérifier si les champs obligatoires ne sont pas vides
            if (nom.isEmpty() || quantiteStr.isEmpty() || prixStr.isEmpty() || description.isEmpty() || categorieStr.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return; // Sortir de la méthode si les champs obligatoires sont vides
            }

            // Vérifier si les valeurs numériques sont valides
            int quantite = 0;
            int prix = 0;
            int categorie = 0;
            try {
                quantite = Integer.parseInt(quantiteStr);
                prix = Integer.parseInt(prixStr);
                categorie = Integer.parseInt(categorieStr);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez saisir des valeurs numériques valides pour la quantité, le prix et la catégorie.");
                alert.showAndWait();
                return; // Sortir de la méthode si les valeurs numériques ne sont pas valides
            }

            // Si toutes les validations sont passées, ajouter le produit
            ps.ajouter(new Produit(nom, quantite, prix, description, categorie));

            // Afficher une alerte d'information en cas de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le produit a été ajouté avec succès.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}
