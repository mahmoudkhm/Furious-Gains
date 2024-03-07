package controllers;

import esprit.tn.Models.Categorie;
import esprit.tn.Models.Produit;
import esprit.tn.Models.data;
import esprit.tn.Utils.MyConnexion;
import esprit.tn.services.CategorieService;
import esprit.tn.services.EmailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.sql.SQLException;


import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class GestionP {

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridpane;

    @FXML
    private ScrollPane menu_scrollpane;

    private static PreparedStatement prepare;
    private Statement statement;
    private static ResultSet result;
    @FXML
    private ComboBox<String> categoryCombobox;
    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;



    @FXML
    private Button menu_payBtn;

    @FXML
    private TableColumn<Produit, String> menu_price;

    @FXML
    private TableColumn<Produit, String> menu_productname;

    @FXML
    private TableColumn<Produit, String> menu_qty;

    @FXML
    private Button menu_reciptBtn;

    @FXML
    private Button menu_remobeBtn;

    private EmailService emailService = new EmailService();

    @FXML
    private TableView<Produit> menu_tableview;


    @FXML
    private Label menu_tot;
    CategorieService cs = new CategorieService();
    private ObservableList<Produit> inventoryListData;
    private ObservableList<Produit> cardListData = FXCollections.observableArrayList();
    ObservableList<Produit> listData = FXCollections.observableArrayList();
    Connection cnx = MyConnexion.getInstance().getCnx();
    private int cID;
    private int qty; // Variable pour stocker la quantité
    private double pr; // Variable pour stocker le prix
    private Alert alert;

    public GestionP() {}



    public void customerID() {

        String sql = "SELECT MAX(customer_id) FROM customer";
        Connection cnx = MyConnexion.getInstance().getCnx();

        try {
            prepare = cnx.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = cnx.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }

            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }

            data.cID = cID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double totalP;
    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(prix_produit) FROM customer WHERE customer_id = " + cID;

        Connection cnx = MyConnexion.getInstance().getCnx();


        try {

            prepare = cnx.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(prix_produit)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void menuDisplayTotal() {
        menuGetTotal();
        menu_tot.setText(totalP+" DT");
    }


    private double amount;
    private double change;
    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        menu_tot.setText("0.0 DT");
        menu_amount.setText("");
        menu_change.setText("0.0 DT");
        //menuShowOrderData();
       // menu_tableview.getItems().clear();
    }
    @FXML
    void menu_payBtn(ActionEvent event) {
menu_amount();
        if (totalP == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please choose your order first!");
            alert.showAndWait();
        } else {
            menuGetTotal();
            String insertPay = "INSERT INTO receipt (customer_id,prix_produit) "
                    + "VALUES(?,?)";

            Connection cnx = MyConnexion.getInstance().getCnx();


            try {

                if (amount == 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Messaged");
                    alert.setHeaderText(null);
                    alert.setContentText("Something wrong :3");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        customerID();
                        menuGetTotal();
                        prepare = cnx.prepareStatement(insertPay);
                        prepare.setString(1, String.valueOf(cID));
                        prepare.setString(2, String.valueOf(totalP));
                        //prepare.setString(3, String.valueOf());
                        //prepare.setString(4, String.valueOf(totalP));




                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successful.");
                        alert.showAndWait();

                        menuShowOrderData();
                        //mailiing
                        // Envoi du code par e-mail
                        String destinataire ="mahmoudkhm71@gmail.com"; // Adresse e-mail du destinataire à remplir
                        String sujet = "Code de réinitialisation de mot de passe";
                        String contenu = "Bonjour,Votre code de réinitialisation de mot de passe est : " ;
                        System.out.println("Email try !");
                        try {
                            //   System.out.println("Email envoyé avec succès constructorNewUserMail!");
                            //  emailService.constructorNewUserMail(destinataire,sujet,contenu);

                            System.out.println("Email sendEmail S1!");
                            emailService.sendEmail("mahmoudkhm71@gmail.com", sujet, contenu);
                            System.out.println("Email envoyé avec succès 2222 !");


                        } catch (Exception e) {
                            showAlert(Alert.AlertType.ERROR, "Erreur lors de l'envoi de l'e-mail !");
                        }

                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    void menu_remobeBtn(ActionEvent event) {
       // menuSelectOrder();
        Produit prod = menu_tableview.getSelectionModel().getSelectedItem();
        int num = menu_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        getid = prod.getId_produit();
        System.out.println(getid);
        if (getid == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            String deleteData = "DELETE FROM customer  WHERE customer_id = " + getid;
            Connection cnx = MyConnexion.getInstance().getCnx();
            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this order?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = cnx.prepareStatement(deleteData);
                    prepare.executeUpdate();
                }

                menuShowOrderData();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public ObservableList<Produit> menuGetOrder() {
        customerID();
        ObservableList<Produit> listData = FXCollections.observableArrayList();
        Connection cnx = MyConnexion.getInstance().getCnx();
        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;

        //connect = database.connectDB();

        try {

            prepare = cnx.prepareStatement(sql);
            result = prepare.executeQuery();

            Produit prod;

            while (result.next()) {
                prod = new Produit(result.getInt(1),
                        result.getString("marque_produit"),
                        result.getInt("quantite"),
                        result.getFloat("prix_produit"));
                        //result.getString("description"),
                        //result.getString("image_name"));
                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<Produit> menuOrderListData;

    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();

        menu_productname.setCellValueFactory(new PropertyValueFactory<>("marque_produit"));
        menu_qty.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        menu_price.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));

        menu_tableview.setItems(menuOrderListData);
    }
    private int getid;

    public void menuSelectOrder() {


    }

    public void menuAmount() {

    }
    @FXML
    void menu_btn(ActionEvent event) throws SQLException {


        try {
            menuDisplayCard();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        menuDisplayTotal();
        menuShowOrderData();
    }



    public ObservableList<Produit> menuGetData() throws SQLException {
        String sql = "SELECT * FROM produit";
        ObservableList<Produit> listData = FXCollections.observableArrayList();

        try {
            prepare = cnx.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                Produit prod = new Produit(
                        result.getInt(1),
                        result.getString("marque_produit"),
                         result.getInt("quantite"),
                        result.getFloat("prix_produit"),
                        result.getString("description"),
                        result.getInt("id_categorie"),
                        result.getString("image_name"));


                listData.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    @FXML
    void filter(ActionEvent event) {
        String selectedValue = categoryCombobox.getValue();
        if (selectedValue == null) {
            showAlert(Alert.AlertType.ERROR, "Please select a category.");
            return;
        }

        String req = "SELECT p.* FROM produit p JOIN categorie c ON p.id_categorie = c.id_categorie WHERE c.nom_categorie = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, selectedValue);
            ResultSet resultSet = preparedStatement.executeQuery();

            cardListData.clear(); // Effacer les données précédentes

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setId_produit(resultSet.getInt(1));
                produit.setMarque_produit(resultSet.getString(2));
                produit.setQuantite(resultSet.getInt("quantite"));
                produit.setPrix_produit(resultSet.getInt("prix_produit"));
                produit.setDescription(resultSet.getString(5));
                produit.setId_categorie(resultSet.getInt("id_categorie"));
                produit.setImage_name(resultSet.getString("image_name"));

                cardListData.add(produit);
            }
            menuGetData();
            menuDisplayCard(); // Mettre à jour l'affichage

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while filtering: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void menuDisplayCard() throws SQLException {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_gridpane.getChildren().clear();
        menu_gridpane.getRowConstraints().clear();
        menu_gridpane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/CardProduct.fxml"));
                AnchorPane pane = load.load();
                cardProduct cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menu_gridpane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (menu_tot != null) {
            System.out.println("menu_total is not null");
        } else {
            System.out.println("menu_total is null");
        }

    }
    @FXML
    public void initialize() {
        if (menu_tot != null) {
            System.out.println("menu_total is not null");
        } else {
            System.out.println("menu_total is null");
        }
        List<Categorie> categories = cs.affichage();
        ObservableList<String> nomCategories = FXCollections.observableArrayList();
        for (Categorie categorie : categories) {
            nomCategories.add(categorie.getNom_categorie());
        }

        // Utiliser un HashSet pour obtenir des valeurs uniques
        Set<String> uniqueNomCategories = new HashSet<>(nomCategories);

        // Ajouter les valeurs uniques à la ComboBox
        categoryCombobox.setItems(FXCollections.observableArrayList(uniqueNomCategories));
        try {
            menuRestart();
            menuDisplayCard();
            menuGetOrder();
            menuDisplayTotal();
            menuShowOrderData();

            // customersShowData();


            //customersShowData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void GeneratePdf(String filename, List<Produit> productList) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        document.add(new Paragraph("Liste des Produits :"));
        document.add(new Paragraph("     "));
        for (Produit produit : productList) {
            document.add(new Paragraph("Nom du Produit: " + produit.getMarque_produit()));
            document.add(new Paragraph("Quantité: " + produit.getQuantite()));
            document.add(new Paragraph("Prix: " + produit.getPrix_produit()));
            document.add(new Paragraph("------------------------------------------------------"));
        }

        document.close();
    }

    @FXML
    void menu_reciptBtn(ActionEvent event) {
        if (menu_tableview.getItems().isEmpty() || menu_amount.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please order first and enter the amount");
            alert.showAndWait();
        } else {
            try {
                GeneratePdf("Receipt", menu_tableview.getItems());
                showAlert(Alert.AlertType.INFORMATION, "Receipt generated successfully!");
                menuRestart();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to generate receipt!");
            }
        }
    }

    @FXML
    void menu_amount() {
        menuGetTotal();
        if (menu_amount.getText().isEmpty() || totalP == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            try {
                amount = Double.parseDouble(menu_amount.getText());
                if (amount < totalP) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Insufficient amount. Please enter a higher amount.");
                    alert.showAndWait();
                    menu_amount.setText(""); // Clear the text field if insufficient amount
                } else {
                    change = (amount - totalP);
                    menu_change.setText(change + " DT");
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input. Please enter a valid number.");
                alert.showAndWait();
                menu_amount.setText(""); // Clear the text field if invalid input
            }
        }
    }


}

