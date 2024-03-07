package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import esprit.tn.Models.Produit;
import esprit.tn.Models.data;
import esprit.tn.Utils.MyConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

/**
 *
 * @author WINDOWS 10
 */
public class cardProduct implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Spinner<Integer> prod_spinner;

    @FXML
    private Button prod_addBtn;

    private Produit prodData;
    private Image image;

    private String prodID;
    private String type;

    private String prod_image;

    private SpinnerValueFactory<Integer> spin;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;
    Connection cnx = MyConnexion.getInstance().getCnx();

    public void setData(Produit prodData) {
        this.prodData = prodData;

        prod_image = prodData.getImage_name();

        prodID = String.valueOf(prodData.getId_produit());
        prod_name.setText(prodData.getMarque_produit());
        prod_price.setText( String.valueOf(prodData.getPrix_produit())+"DT");
        String path = "File:" + prodData.getImage_name();
        image = new Image(path, 100, 100, false, true);
        prod_imageView.setImage(image);
        pr = prodData.getPrix_produit();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

         setQuantity();
    }
    private int qty;

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }

    private double totalP;
    private double pr;

    public void addBtn(ActionEvent actionEvent) {
        qty = prod_spinner.getValue();
        GestionP gp = new GestionP();
        gp.customerID();
        String check = "";
System.out.println(qty);
        System.out.println(pr);
        System.out.println(qty*pr);
        Connection cnx = MyConnexion.getInstance().getCnx();

        try {
            int checkStck = 0;
            String checkStock = "SELECT quantite FROM produit WHERE id_produit = ?";

            // Utilisation d'une PreparedStatement pour éviter les problèmes de sécurité et d'injection SQL
            prepare = cnx.prepareStatement(checkStock);
            prepare.setString(1, prodID); // Utilisation de l'ID du produit
            result = prepare.executeQuery();

            if (result.next()) {
                checkStck = result.getInt("quantite");
            }

            if(checkStck == 0){
                String updateStock = "UPDATE produit SET marque_produit = ?, quantite = ?, prix_produit = ?,image_name = ? WHERE id_produit = ?";

                prepare = cnx.prepareStatement(updateStock);
                prepare.setString(1, prod_name.getText());
                prepare.setInt(2, 0);
                prepare.setDouble(3, pr);
                prepare.setString(4,prod_image);
                prepare.setString(5, prodID); // Utilisation de l'ID du produit

                prepare.executeUpdate();
            }

            if (qty == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong :3");
                alert.showAndWait();
            } else {
                if (checkStck < qty) {
                    Notifications.create()
                            .title("Error")
                            .text("This product is Out of stock")
                            .showInformation();
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This product is Out of stock");
                    alert.showAndWait();
                } else {
                    prod_image = prod_image.replace("\\", "\\\\");
                    //totalP = (qty * pr);
                    int upStock = checkStck - qty;

                    String insertData = "INSERT INTO customer "
                            + "(customer_id, prod_id, marque_produit,prix_produit, quantite) "
                            + "VALUES(?,?,?,?,?)";
                    prepare = cnx.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(data.cID));
                    prepare.setString(2, prodID);
                    prepare.setString(3, prod_name.getText());

                    prepare.setString(5, String.valueOf(qty));

                    totalP = (qty * pr);
                    prepare.setString(4, String.valueOf(totalP));
                    prepare.executeUpdate();
                    String updateStock = "UPDATE produit SET marque_produit = ?, quantite = ?, prix_produit = ? WHERE id_produit = ?";

                    prepare = cnx.prepareStatement(updateStock);
                    prepare.setString(1, prod_name.getText());
                    prepare.setInt(2, upStock);
                    prepare.setDouble(3, totalP);
                    prepare.setString(4, prodID); // Utilisation de l'ID du produit
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Appel de la méthode menuGetTotal avec l'ID du produit et la quantité
                  gp.menuGetTotal();


                   //gp.menuDisplayTotal();
                   // gp.menuShowOrderData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








    public void addBtn() {


    }





}
