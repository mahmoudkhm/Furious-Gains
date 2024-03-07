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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class DetailsProduit {

    @FXML
    private ImageView imgP;

    @FXML
    private Label labelAfficheC;

    @FXML
    private Label labelAfficheP;

    @FXML
    private Label labelAjoutC;

    @FXML
    private Label labelAjoutP;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label updateFX;
    private int idProduit;
    @FXML
    private TextField idSupp;
    private Produit selectedProduit;

    ProduitService ps =new ProduitService();
   // Produit p =new Produit();
   Connection cnx = MyConnexion.getInstance().getCnx();

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
        afficherDetailsProduit();
    }


        private void afficherDetailsProduit() {
            // Récupérer les détails du produit à partir de l'ID
            Produit produit = ps.getOneById(idProduit);
            // Créer une liste observable pour stocker les détails du produit
            ObservableList<String> detailsProduit = FXCollections.observableArrayList();
            String image_uri = "C:/Users/MSI/IdeaProjects/furiousGain/Furious-Gains/FuriousGains/src/main/java/assets/" + produit.getImage_name();
            Image image = new Image(image_uri);
            // Ajouter les détails du produit à la liste observable
           // detailsProduit.add("ID: " + produit.getId_produit());
            detailsProduit.add("Marque: " + produit.getMarque_produit());
            //detailsProduit.add("Quantité: " + produit.getQuantite());
            detailsProduit.add("Prix: " + produit.getPrix_produit());
            detailsProduit.add("Description: " + produit.getDescription());

            // Afficher la liste observable dans la ListView
            listView.setItems(detailsProduit);
            imgP.setImage(image);
            if (produit.getQuantite() == 0) {
                // Si la quantité est égale à zéro, afficher une notification Windows
                Notifications.create()
                        .title("Quantité de produit épuisée")
                        .text("La quantité du produit est épuisée.")
                        .showInformation();
            }
        }


    @FXML
    void AfficherP(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProduit.fxml"));
        try {
            Parent root = loader.load();
            AfficherProduit controller = loader.getController();
            controller.setData();
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

    @FXML
    void UpdateRedP(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProduit.fxml"));
        try {
            Parent root = loader.load();
            ModifierProduit controller = loader.getController();
            updateFX.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void Onclick(MouseEvent event) {
        int  selectedItem = idProduit;
        idSupp.setText(String.valueOf(selectedItem));
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {
        //int selectedItem = listView.getSelectionModel().getSelectedItem().getId_produit();
        //idSupp.setText(String.valueOf(selectedItem));
        String sql = "DELETE FROM produit WHERE id_produit = ?";
        try {
            // Create a confirmation dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirmation");
            dialog.setContentText("Are you sure you want to delete this product?");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for the admin's response
            Optional<ButtonType> result = dialog.showAndWait();

            // If the admin chooses "Yes", delete the product
            if (result.isPresent() && result.get() == ButtonType.YES) {
                try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                    int idSuppValue = Integer.parseInt(idSupp.getText());
                    preparedStatement.setInt(1, idSuppValue);
                    preparedStatement.executeUpdate();
                    System.out.println("Product Deleted successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setContentText("Product Deleted successfully!");
                    alert.showAndWait();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProduit.fxml"));
                    try {
                        Parent root = loader.load();
                        AfficherProduit controller = loader.getController();
                        controller.setData();
                        labelAfficheP.getScene().setRoot(root);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    //initialize();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

