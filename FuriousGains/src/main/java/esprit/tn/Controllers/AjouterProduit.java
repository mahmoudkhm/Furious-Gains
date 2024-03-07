package esprit.tn.Controllers;

import esprit.tn.Models.Categorie;
import esprit.tn.Utils.MyConnexion;
import esprit.tn.services.CategorieService;
import esprit.tn.services.ProduitService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import esprit.tn.Models.data;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.controlsfx.control.Notifications;
import java.sql.*;
import java.util.List;

public class AjouterProduit {
    @FXML
    private Button upload;
    @FXML
    private ImageView img;
   // @FXML
    //private TextField categorieP;

    @FXML
    private ComboBox<Integer> categorieP;

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
    private Label updateFX;
    @FXML
    private Label labelAfficheP;

    @FXML
    private Label labelAjoutC;

    @FXML
    private Label labelAjoutP;
    private File file;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;
    Connection cnx= MyConnexion.getInstance().getCnx();
CategorieService cs = new CategorieService();
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
    private final ProduitService ps = new ProduitService();
    @FXML
    private void upload_imageAction(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));
        //Show open file dialog
        file = openFile.showOpenDialog(null);
        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            img.setImage(image);
        }
    }
    @FXML
    void category(ActionEvent event) {
        //ObservableList<Categorie> items = FXCollections.observableArrayList();
       Integer selectedValue = categorieP.getValue();
    }
    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z\\s]+");
    }

    @FXML
    void AjouterP(ActionEvent event) {
        try {
            System.out.println("Début de la méthode AjouterP");

            String nom = nomP.getText();
            String quantiteStr = quantiteP.getText();
            String prixStr = prixP.getText();
            String description = descriptionP.getText();
            String categorieStr = String.valueOf(categorieP.getValue());

            // Récupérer le chemin de l'image sélectionnée
            String path = data.path;
            if (path == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez sélectionner une image.");
                alert.showAndWait();
                return; // Sortir de la méthode si aucun chemin d'image n'est disponible
            }
            path = path.replace("\\", "\\\\");

            System.out.println("Chemin de l'image : " + path);

            // Vérifier si les champs obligatoires ne sont pas vides
            if (nom.isEmpty() || quantiteStr.isEmpty() || prixStr.isEmpty() || description.isEmpty() || categorieStr.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return; // Sortir de la méthode si les champs obligatoires sont vides
            }


            System.out.println("Tous les champs sont remplis");

            // Vérifier si les valeurs numériques sont valides
            int quantite = Integer.parseInt(quantiteStr);
            float prix = Float.parseFloat(prixStr);
            //int categorie = Integer.parseInt(categorieStr);
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
            if (!isAlpha(nom) || !isAlpha(description)) {
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
           // System.out.println("Valeurs numériques validées");

            // Préparer la requête d'insertion
            String insertData = "INSERT INTO produit "
                    + "(marque_produit, quantite, prix_produit, description, id_categorie, image_name) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            prepare = cnx.prepareStatement(insertData);
            prepare.setString(1, nom);
            prepare.setInt(2, quantite);
            prepare.setFloat(3, prix);
            prepare.setString(4, description);
            prepare.setInt(5, Integer.parseInt(categorieStr)); // Changer l'index en 5
            prepare.setString(6, path); // Changer l'index en 6

            // Exécuter la requête d'insertion
            prepare.executeUpdate();

            System.out.println("Produit ajouté avec succès");

            // Afficher une alerte d'information en cas de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Le produit a été ajouté avec succès.");
            alert.showAndWait();

            System.out.println("Fin de la méthode AjouterP");
        } catch (NumberFormatException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void initialize() {
        ObservableList<Integer> id = FXCollections.observableArrayList();
        List<Categorie> categories = cs.affichage();

        for (Categorie categorie : categories) {
            id.add(categorie.getId_categorie());
        }

// Ajouter les valeurs de l'id directement à la ComboBox
        categorieP.setItems(id);


    }




   /* public void upload_imageAction() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(MainFX.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            inventory_imageView.setImage(image);
        }
    }*/
     /*

     //affichage produits

     public void menuDisplayCard() {

        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                cardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     public ObservableList<productData> menuGetData() {

        String sql = "SELECT * FROM product";

        ObservableList<productData> listData = FXCollections.observableArrayList();
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            productData prod;

            while (result.next()) {
                prod = new productData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    */


       /* private String generateUniqueName (File file) {
            if (file != null) {
                String name = String.valueOf(file.toPath());
                String extension = name.substring(name.lastIndexOf(".")).toLowerCase(); // Get the file extension
                String imageName = UUID.randomUUID().toString() + extension; // Generate a unique name with the same extension
                return imageName;
            }
            else {
                return null;
            }
        }*/

}
