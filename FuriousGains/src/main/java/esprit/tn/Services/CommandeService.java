package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Commande;
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
            alert.setTitle("succes");
            alert.setContentText("User deleted Successfully!");
            alert.showAndWait();

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            System.out.println("Commande Updated Successfully!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
