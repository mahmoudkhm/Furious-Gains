package controllers;


import esprit.tn.Models.Produit;
import esprit.tn.Utils.MyConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifierCategorie {
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
    private TextField typeC;


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

    @FXML
    void UpdateP(ActionEvent event) {

    }
}
