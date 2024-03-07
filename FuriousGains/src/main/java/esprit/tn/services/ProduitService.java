package esprit.tn.services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
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
            String req = "INSERT INTO produit (marque_produit,quantite,prix_produit,description,id_categorie,image_name) VALUES ('"+p.getMarque_produit()+"',"+p.getQuantite()+","+p.getPrix_produit()+",'"+p.getDescription()+"' ,"+p.getId_categorie()+",'"+p.getImage_name()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Product Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void modifier(Produit p) {
        String sql = "UPDATE produit SET marque_produit= ?,quantite=?,prix_produit=?,description=?WHERE id_produit = ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, p.getMarque_produit());
            ps.setInt(2,p.getQuantite());
            ps.setFloat(3,p.getPrix_produit());
            ps.setString(4,p.getDescription());
           // ps.setInt(5,p.getId_categorie());
           // ps.setString(6,p.getImage_name());
            ps.setInt(5,p.getId_produit());

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
                p.setMarque_produit(rs.getString(2));
                p.setQuantite(rs.getInt("quantite"));
                p.setPrix_produit(rs.getFloat("prix_produit"));
                p.setDescription(rs.getString(5));
                p.setId_categorie(rs.getInt("id_categorie"));
                p.setImage_name(rs.getString("image_name"));

                produits.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return produits;
    }
   /* public List<Produit> getInnerJoinCategorie(int id) {
        List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM produit INNER JOIN categorie ON (produit.id_categorie = categorie.id_categorie) WHERE produit.id_categorie=?";
        try (PreparedStatement st = cnx.prepareStatement(req)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_produit(rs.getInt("id_produit"));
                p.setMarque_produit(rs.getString("nom_produit"));
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
    }*/

    public Produit getOneById(int id) {
        Produit p = null;
        String req = "SELECT * FROM produit WHERE id_produit = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                     p = new Produit(rs.getString("marque_produit"),
                    rs.getInt("quantite"),
                    rs.getFloat("prix_produit"),
                    rs.getString("description"),
                    rs.getInt("id_categorie"),
                            rs.getString("image_name"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
    public Produit getOneByIdC(int id) {
        Produit p = null;
        String req = "SELECT * FROM produit WHERE id_categorie = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Produit( rs.getInt("id_produit"),

                            rs.getString("marque_produit"),
                            rs.getInt("quantite"),
                            rs.getFloat("prix_produit"),
                            rs.getString("description"),
                            rs.getInt("id_categorie"),
                     rs.getString("image_name"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
  /*  public User getOneByCin(int cin) {
        User u = null;
        String req = "SELECT * FROM user WHERE cin = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setInt(1, cin);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = new User(
                            rs.getInt("id_user"),
                            rs.getInt("cin"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getInt("num_tel"),
                            rs.getString("adresse"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }*/
}
