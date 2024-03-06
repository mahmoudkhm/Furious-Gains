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
        String req = "INSERT INTO annonces ( `titre_annonces`, `description_annonces`, `imag`, `id_user`) VALUES ('" + annonce.getTitre_annonce() + "', '" + annonce.getDescription_annonce() + "', '" +annonce.getImage()+ "', '" + annonce.getId_user() +   "')";
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
    public List<Annonce> showComments(int id) throws SQLException {
        String sql = "Select * From annonces Where id_annonces = ?";
        try (
                PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


            List<Annonce> personnes = new ArrayList<Annonce>();
            while (rs.next()) {
                Annonce annonce = new Annonce();
                annonce.setId_annonce(rs.getInt("id_annonces"));
                annonce.setTitre_annonce(rs.getString("titre_annonces"));
                annonce.setDescription_annonce(rs.getString("description_annonces"));
                annonce.setImage(rs.getString("imag"));
                annonce.setId_user(rs.getInt("id_user"));
                personnes.add(annonce);
            }

            return personnes;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Annonce> showAnnonces(int id) throws SQLException {
        String sql = "Select * From annonces Where id_annonces = ?";
        try (
                PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


            List<Annonce> personnes = new ArrayList<Annonce>();
            while (rs.next()) {
                Annonce annonce = new Annonce();
                annonce.setId_annonce(rs.getInt("id_annonces"));
                annonce.setTitre_annonce(rs.getString("titre_annonces"));
                annonce.setDescription_annonce(rs.getString("description_annonces"));
                annonce.setImage(rs.getString("imag"));
                annonce.setId_user(rs.getInt("id_user"));
                personnes.add(annonce);
            }

            return personnes;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Annonce getOneById(int id) throws SQLException {
        String sql = "SELECT * FROM annonces WHERE id_annonces = ?";
        try (
                PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Annonce annonce = new Annonce();
                annonce.setId_annonce(rs.getInt("id_annonces"));
                annonce.setTitre_annonce(rs.getString("titre_annonces"));
                annonce.setDescription_annonce(rs.getString("description_annonces"));
                annonce.setImage(rs.getString("imag"));
                annonce.setId_user(rs.getInt("id_user"));
                return annonce;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public void modifier(Annonce annonce) {
        String req = "UPDATE annonces set titre_annonces = ?, description_annonces =? ,imag =?, id_user =?  where id_annonces= ?";
        PreparedStatement as = null;
        try {
            as = cnx.prepareStatement(req);
            as.setString(1, annonce.getTitre_annonce());
            as.setString(2, annonce.getDescription_annonce());
            as.setString(3, annonce.getImage());
            as.setFloat(4, annonce.getId_user());
            as.setInt(5, annonce.getId_annonce());
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
                annonce.setImage(rs.getString("imag"));
                annonce.setId_user(rs.getInt("id_user"));
                lista.add(annonce);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;

    }    }

