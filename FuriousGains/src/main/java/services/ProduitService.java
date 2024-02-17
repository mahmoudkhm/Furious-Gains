package services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Categorie;
import esprit.tn.Models.Produit;
import esprit.tn.Utils.MyConnexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class ProduitService implements InterfaceFuriousGains <Produit> {
 Connection cnx= MyConnexion.getInstance().getCnx();


    @Override
    public void ajouter(Produit p) {

        try {
            String req = "INSERT INTO produit (nom_produit,quantite,prix_produit,description,id_categorie) VALUES ('"+p.getNom_produit()+"',"+p.getQuantite()+","+p.getPrix_produit()+",'"+p.getDescription()+"' ,"+p.getId_categorie()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Product Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void modifier(Produit p) {
        String sql = "UPDATE produit SET nom_produit= ?,quantite=?,prix_produit=?,description=?,id_categorie=? WHERE id_produit = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {

            PreparedStatement ps = cnx.prepareStatement(sql);
            //ps.setString(1, p.getNom_produit());
            ps.setString(1, p.getNom_produit());
            ps.setInt(2,p.getQuantite());
            ps.setFloat(3,p.getPrix_produit());
            ps.setString(4,p.getDescription());
            ps.setInt(5,p.getId_categorie());
            ps.setInt(6,p.getId_produit());


            ps.executeUpdate();
            System.out.println("Product updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM produit WHERE id_produit = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Product Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Produit> affichage() {

        List<Produit> produits = new ArrayList<>();
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
                produits.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produits;
    }
    public List<Produit> getInnerJoinCategorie(int id) {
        List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM produit INNER JOIN categorie ON (produit.id_categorie = categorie.id_categorie) WHERE produit.id_categorie=?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_produit(rs.getInt("id_produit"));
                p.setNom_produit(rs.getString("nom_produit"));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix_produit(rs.getInt("prix_produit"));
                p.setDescription(rs.getString("description"));
                p.setId_categorie(rs.getInt("id_categorie"));
                produits.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;
    }


}
