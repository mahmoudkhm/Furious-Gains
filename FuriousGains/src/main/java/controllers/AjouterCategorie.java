package controllers;

import esprit.tn.Models.Categorie;
import esprit.tn.services.CategorieService;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;


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
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.lang.IllegalArgumentException;

public class AjouterCategorie {

    @FXML
    private Label labelAfficheC;

    @FXML
    private Label labelAfficheP;

    @FXML
    private Label labelAjoutC;

    @FXML
    private Label labelAjoutP;

    @FXML
    private TextField nomC;

    @FXML
    private TextField TypeC;
    private final CategorieService cs = new CategorieService();

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

    private boolean isAlpha(String str) {

        return str.matches("[a-zA-Z]+");
    }
    @FXML
    void AjouterCategory(ActionEvent event) {
        try {
            String nom = nomC.getText();
            String type = TypeC.getText();


            // Vérifier si les champs obligatoires ne sont pas vides
            if (nom.isEmpty() || type.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return; // Sortir de la méthode si les champs obligatoires sont vides
            }

            // Vérifier si les valeurs numériques sont valides

            if (!isAlpha(nom) || !isAlpha(type)) {
                Notifications.create()
                        .title("Erreur")
                        .text("Les champs Nom Categorie et Type Categorie ne doivent contenir que des lettres.")
                        .showInformation();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Les champs Nom et Type ne doivent contenir que des lettres.");
                alert.showAndWait();
                return;
            }
            // Si toutes les validations sont passées, ajouter le produit
            cs.ajouter(new Categorie(nom, type));

            // Afficher une alerte d'information en cas de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("La categorie a été ajoutée avec succès.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void UpdateRedP(MouseEvent mouseEvent) {
    }
}



