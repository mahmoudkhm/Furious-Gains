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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class AfficherProduit {

    @FXML
    private Label labelAfficheC;

    @FXML
    private Label labelAfficheP;

    @FXML
    private Label labelAjoutC;

    @FXML
    private Label labelAjoutP;

    @FXML
    private Button updateFX;
    @FXML
    private TextField IdSupp;

    @FXML
    private ListView<Produit> listView;
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
    Connection cnx = MyConnexion.getInstance().getCnx();

    public void initialize() {
        // Créer une liste d'objets Produit
        ObservableList<Produit> items = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_produit(rs.getInt(1));
                p.setNom_produit(rs.getString(2));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix_produit(rs.getInt("prix_produit"));
                p.setDescription(rs.getString(5));
                p.setId_categorie(rs.getInt("id_categorie"));
                items.add(p);
            }

            // Lier la liste à la ListView
            listView.setItems(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void DeleteP(ActionEvent actionEvent) throws SQLException {
        int selectedItem = listView.getSelectionModel().getSelectedItem().getId_produit();
        IdSupp.setText(String.valueOf(selectedItem));
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
                    int idSuppValue = Integer.parseInt(IdSupp.getText());
                    preparedStatement.setInt(1, idSuppValue);
                    preparedStatement.executeUpdate();
                    System.out.println("Product Deleted successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setContentText("Product Deleted successfully!");
                    alert.showAndWait();
                    initialize();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnClick(MouseEvent mouseEvent) {

            int  selectedItem = listView.getSelectionModel().getSelectedItem().getId_produit();
            IdSupp.setText(String.valueOf(selectedItem));

    }



    public void UpdateRedP(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProduit.fxml"));
        try {
            Parent root = loader.load();
            ModifierProduit controller = loader.getController();
            updateFX.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


