package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Commande;
import esprit.tn.Models.Livraison;
import esprit.tn.Models.User;
import esprit.tn.Utils.MyConnexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements InterfaceFuriousGains<Commande> {
    private Connection cnx;

    public CommandeService() {cnx= MyConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commande commande) {
        String req="INSERT INTO `commande`(id_client, statut_commande,montant_total,id_produit)" +
                " VALUES ('"+commande.getId_client()+"','"+commande.getStatut_commande()+"', '"+commande.getMontant_total()+"','"+commande.getId_produit()+"')";

        PreparedStatement ps= null;
        try {
            Statement st= cnx.createStatement();
            st.executeUpdate(req);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succés");
            alert.setContentText("Commande ajouter avec succés!");
            alert.showAndWait();

            System.out.println("Commande  ajouter avec succés!");


        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout!");
            alert.showAndWait();
        }


    }

    @Override
    public void modifier(Commande commande) {
        String req="UPDATE `commande` SET `id_client`=?,`statut_commande`=?,`montant_total`=?,`id_produit`=? WHERE id_command =?";
        PreparedStatement ps = null;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1,commande.getId_client());
            ps.setString(2,commande.getStatut_commande());
            ps.setFloat(3,commande.getMontant_total());
            ps.setInt(4,commande.getId_produit());
            ps.setInt(5,commande.getId_command());

            ps.executeUpdate();
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succés");
            alert.setContentText("Commande Modifier avec succés!");
            alert.showAndWait();
            System.out.println("Commande Updated Successfully!");


        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec de modifier!");
            alert.showAndWait();
        }


    }

    @Override
    public void supprimer(int id) {
        String req="DELETE FROM `commande` WHERE id_command="+id;
        PreparedStatement ps= null;
        try {
            ps = cnx.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("User deleted Successfully!");
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succés");
            alert.setContentText("Commande supprimer avec succés!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec de suppression!");
            alert.showAndWait();
        }
    }

    @Override
    public List<Commande> affichage() {
        List<Commande> commondes= new ArrayList<>();
        String req="SELECT * FROM `commande`";
        try {
            Statement st =cnx.createStatement();
            ResultSet result =st.executeQuery(req);
            while (result.next()){
                Commande commande =new Commande();
                commande.setId_command(result.getInt("id_command"));
                commande.setId_client(result.getInt("id_client"));
                commande.setStatut_commande(result.getString("statut_commande"));
                commande.setMontant_total(result.getFloat("montant_total"));
                commande.setId_produit(result.getInt("id_produit"));
                commondes.add(commande);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return commondes;
    }
    public Commande getOneByiD(int id) {
        Commande c = null;
        String req = "SELECT * FROM commande WHERE id_command LIKE  ? ";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setString(1, "%" + id + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Commande(
                            rs.getInt("id_command"),
                            rs.getInt("id_client"),
                            rs.getString("statut_commande"),
                            rs.getFloat("montant_total"),
                            rs.getInt("id_produit")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
    public Commande getOneByiD(String statut) {
        Commande c = null;
        String req = "SELECT * FROM `commande`  WHERE statut_commande like ? ";
        try (PreparedStatement stmt = cnx.prepareStatement(req)) {
            stmt.setString(1, "%"+statut+"%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Commande(
                            rs.getInt("id_command"),
                            rs.getInt("id_client"),
                            rs.getString("statut_commande"),
                            rs.getFloat("montant_total"),
                            rs.getInt("id_produit")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
}
