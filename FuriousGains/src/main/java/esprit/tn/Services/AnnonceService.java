package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Annonce;
import esprit.tn.Models.Avis;
import esprit.tn.Utils.MyConnexion;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnonceService implements InterfaceFuriousGains<Annonce> {
    private Connection cnx;

    public AnnonceService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Annonce annonce) {
        String req = "INSERT INTO annonces ( `titre_annonces`, `description_annonces`, `id_user`) VALUES ('" + annonce.getTitre_annonce() + "', '" + annonce.getDescription_annonce() + "', '" + annonce.getId_user() +   "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succés");
            alert.setContentText("avis ajoutée avec succés!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout");
            alert.showAndWait();

        }
    }

    @Override
    public void modifier(Annonce annonce) {
        String req = "UPDATE annonces set titre_annonces = ?, description_annonces =? , id_user =?  where id_annonces= ?";
        PreparedStatement as = null;
        try {
            as = cnx.prepareStatement(req);
            as.setString(1, annonce.getTitre_annonce());
            as.setString(2, annonce.getDescription_annonce());
            as.setFloat(3, annonce.getId_user());
            as.setInt(4, annonce.getId_annonce());
            as.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succés");
            alert.setContentText("annonce modifiée avec succés!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout");
            alert.showAndWait();

        }
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM annonces where id_annonces = ?";
        PreparedStatement as = null;
        try {
            as = cnx.prepareStatement(req);
            as.setInt(1, id);
            as.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succés");
            alert.setContentText("annonce supprimée avec succés!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout");
            alert.showAndWait();
        }
    }

    @Override
    public List<Annonce> affichage() {
        List<Annonce> lista = new ArrayList<>();
        String req = "Select * FROM annonces";
        try {
            Statement as = cnx.createStatement();
            ResultSet rs = as.executeQuery(req);
            while (rs.next()) {
                Annonce annonce = new Annonce();
                annonce.setId_annonce(rs.getInt("id_annonces"));
                annonce.setTitre_annonce(rs.getString("titre_annonces"));
                annonce.setDescription_annonce(rs.getString("description_annonces"));
                annonce.setId_user(rs.getInt("id_user"));
                lista.add(annonce);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;

    }    }

