package esprit.tn.Controllers;

import esprit.tn.Models.Categorie;
import esprit.tn.Models.Produit;
import esprit.tn.Utils.MyConnexion;
import esprit.tn.services.CategorieService;
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
import javafx.stage.FileChooser;
import javafx.scene.layout.AnchorPane;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class AfficherProduit {

    @FXML
    private Label labelAfficheC;

    @FXML
    private ImageView img;
    @FXML
    private Label labelAfficheP;
private File file;
    @FXML
    private Label labelAjoutC;
    @FXML
    private TextField search;
    @FXML
    private Label labelAjoutP;

    @FXML
    private Label updateFX;
    @FXML
    private TextField IdSupp;
    @FXML
    private ComboBox<String> categoryCombobox;

    @FXML
    private ListView<Produit> listView;


    CategorieService cs =new CategorieService();
    ProduitService ps =new ProduitService();
    private enum TriOrder {
        ASCENDING,
        DESCENDING
    }

    private TriOrder currentTriOrder = TriOrder.ASCENDING;
    public void trierProduitParPrix(ActionEvent actionEvent) {
        ObservableList<Produit> items = listView.getItems();
        if (currentTriOrder == TriOrder.ASCENDING) {
            items.sort(Comparator.comparingDouble(Produit::getPrix_produit));
            currentTriOrder = TriOrder.DESCENDING;
        } else {
            items.sort(Comparator.comparingDouble(Produit::getPrix_produit).reversed());
            currentTriOrder = TriOrder.ASCENDING;
        }

    }
    public void trierProduitParMarque(ActionEvent actionEvent) {
        ObservableList<Produit> items = listView.getItems();
        if (currentTriOrder == TriOrder.ASCENDING) {
            items.sort(Comparator.comparing(Produit::getMarque_produit));
            currentTriOrder = TriOrder.DESCENDING;
        } else {
            items.sort(Comparator.comparing(Produit::getMarque_produit).reversed());
            currentTriOrder = TriOrder.ASCENDING;
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
    Connection cnx = MyConnexion.getInstance().getCnx();

    /*private static class ImageListCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            // Vérifier si la cellule est vide
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Créer une ImageView pour afficher l'image
                ImageView imageView = new ImageView(new Image("file:assets/" + item + ".png"));

                // Définir la taille de l'image
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                // Afficher l'image dans la cellule
                setGraphic(imageView);
            }
        }
    }*/


    /*public void initialize() {
        // Créer une liste d'objets Produit
        ObservableList<Produit> items = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_produit(rs.getInt(1));
                p.setMarque_produit(rs.getString(2));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix_produit(rs.getInt("prix_produit"));
                p.setDescription(rs.getString(5));
                p.setId_categorie(rs.getInt("id_categorie"));
               // p.setImage_produit(rs.getString("image_produit"));
                items.add(p);
            }


            // Lier la liste à la ListView
            listView.setItems(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public void setData() {
        Produit P = new Produit();
        String image_directory_path = "C:/Users/MSI/IdeaProjects/furiousGain/Furious-Gains/FuriousGains/src/main/java/assets/";
        String full_path;


        if (P.getImage_name() != null) {
            full_path = image_directory_path + P.getImage_name();
            img.setImage(new Image(full_path));
        }
    }



    public void OnClick(MouseEvent mouseEvent) {

           // int  selectedItem = listView.getSelectionModel().getSelectedItem().getId_produit();
           // IdSupp.setText(String.valueOf(selectedItem));
        Produit produit = listView.getSelectionModel().getSelectedItem();
        if (produit != null) {
            afficherDetailsProduit(produit.getId_produit());
        }

    }
    private void afficherDetailsProduit(int idProduit) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailsProduit.fxml"));
        try {
            Parent root = loader.load();
            DetailsProduit controller = loader.getController();
            controller.setIdProduit(idProduit);
            labelAfficheP.getScene().setRoot(root);
        } catch (IOException e) {
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
    void initialize() {
        //List<Produit> p = ps.affichage();
       // ObservableList<String> id = FXCollections.observableArrayList();
        List<Categorie> categories = cs.affichage();
        ObservableList<String> nomCategories = FXCollections.observableArrayList();
        for (Categorie categorie : categories) {
            nomCategories.add(categorie.getNom_categorie());
        }

        // Utiliser un HashSet pour obtenir des valeurs uniques
        Set<String> uniqueNomCategories = new HashSet<>(nomCategories);

        // Ajouter les valeurs uniques à la ComboBox
        categoryCombobox.setItems(FXCollections.observableArrayList(uniqueNomCategories));
       /* for (Produit produit : p) {
           // id.add(String.valueOf(produit.getId_categorie()));
            nomCategories.add(produit.getNom_categorie());

        }
        Set<String> uniqueIds = new HashSet<>(id);

        categoryCombobox.setItems(FXCollections.observableArrayList(uniqueIds));*/



        ObservableList<Produit> items = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setId_produit(rs.getInt(1));
                produit.setMarque_produit(rs.getString(2));
                produit.setQuantite(rs.getInt("quantite"));
                produit.setPrix_produit(rs.getInt("prix_produit"));
                produit.setDescription(rs.getString(5));
                produit.setId_categorie(rs.getInt("id_categorie"));
                 produit.setImage_name(rs.getString("image_name"));
                items.add(produit);
            }


            // Lier la liste à la ListView
            listView.setItems(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerProduitsParRecherche(newValue);
        });
    }

    @FXML
    private void filtrerProduitsParRecherche(String recherche) {
        if (recherche.isEmpty()) {
            initialize();
            return;
        }

        ObservableList<Produit> items = listView.getItems();
        ObservableList<Produit> filteredItems = FXCollections.observableArrayList();

        for (Produit produit : items) {
            if (produit.getMarque_produit().toLowerCase().contains(recherche.toLowerCase())) {
                filteredItems.add(produit);
            }
        }

        listView.setItems(filteredItems);
    }


    // Faire quelque chose avec les produits filtrés, par exemple :
        // productTable.setItems(items);

    /*@FXML
    void filter(ActionEvent event) {
        ObservableList<Produit> items = FXCollections.observableArrayList();
        String selectedValue = categoryCombobox.getValue();
        if (selectedValue == null) {
            items.clear();
        } else {
            String req = "SELECT * FROM produit WHERE id_categorie = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
                preparedStatement.setInt(1, Integer.parseInt(selectedValue));
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Produit p = new Produit();
                    p.setId_produit(resultSet.getInt("id_produit"));
                    p.setMarque_produit(resultSet.getString("marque_produit"));
                    p.setQuantite(resultSet.getInt("quantite"));
                    p.setPrix_produit(resultSet.getInt("prix_produit"));
                    p.setDescription(resultSet.getString("description"));
                    // p.setId_categorie(resultSet.getInt("id_categorie"));
                    // p.setImage_produit(rs.getString("image_produit"));

                    items.add(p);
                }
                listView.setItems(items);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Faire quelque chose avec les produits filtrés, par exemple :
        // productTable.setItems(items);
    }*/
    @FXML
    void filter(ActionEvent event) {


        ObservableList<Produit> items = FXCollections.observableArrayList();

        String selectedValue = categoryCombobox.getValue();
        if (selectedValue == null) {
            items.clear();
        } else {
            String req = "SELECT p.* FROM produit p JOIN categorie c ON p.id_categorie = c.id_categorie WHERE c.nom_categorie = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
                preparedStatement.setString(1, selectedValue);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Produit p = new Produit();
                    p.setId_produit(resultSet.getInt("id_produit"));
                    p.setMarque_produit(resultSet.getString("marque_produit"));
                    p.setQuantite(resultSet.getInt("quantite"));
                    p.setPrix_produit(resultSet.getInt("prix_produit"));
                    p.setDescription(resultSet.getString("description"));
                    // p.setId_categorie(resultSet.getInt("id_categorie"));
                    // p.setImage_produit(rs.getString("image_produit"));

                    items.add(p);
                }
                listView.setItems(items);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Faire quelque chose avec les produits filtrés, par exemple :
        // productTable.setItems(items);
    }


    /*@FXML
    private void upload_imageAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterJPEG
                = new FileChooser.ExtensionFilter("JPEG files (*.JPEG)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterjpeg
                = new FileChooser.ExtensionFilter("jpeg files (*.jpeg)", "*.jpeg");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterJPEG, extFilterjpeg);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String image_uri = file.toURI().toString();
            // Display the selected image in an ImageView
            Image image = new Image(image_uri);
            img.setImage(image);
        }
    }*/

    }



