package services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Categorie;
import esprit.tn.Models.Produit;
import esprit.tn.Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements InterfaceFuriousGains <Categorie> {

    Connection cnx= MyConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Categorie c) {
        try {
            String req = "INSERT INTO categorie (nom_categorie,type_categorie) VALUES ('"+c.getNom_categorie()+"','"+c.getType_categorie()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Categorie c) {
        String sql = "UPDATE categorie SET nom_categorie= ?,type_categorie=? WHERE id_categorie = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {

            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setString(1, c.getNom_categorie());
            ps.setString(2,c.getType_categorie());
            ps.setInt(3,c.getId_categorie());



            ps.executeUpdate();
            System.out.println("Categorie updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM categorie WHERE id_categorie = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Categorie Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categorie> affichage() {
        List<Categorie> categories = new ArrayList<>();
        try {

            String req = "SELECT * FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId_categorie(rs.getInt(1));
                c.setNom_categorie(rs.getString(2));
                c.setType_categorie(rs.getString(3));

                categories.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categories;
    }

    //@Override
    public void supprimerJoin(int id) {
        String sql = "DELETE categorie FROM categorie JOIN produit ON (produit.id_categorie = categorie.id_categorie) WHERE produit.id_categorie = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
