package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Commande;
import esprit.tn.Models.Livraison;
import esprit.tn.Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonService implements InterfaceFuriousGains <Livraison> {
    private Connection cnx;

    public LivraisonService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Livraison livraison) {
        String req = "INSERT INTO `livraison` (id_commande, date_livraison, statut_livraison, adresse_livraison, montant_paiement, mode_livraison, id_client) " +
                "VALUES ('" + livraison.getId_commande() + "', '" + livraison.getDate_livraison() + "', '" + livraison.getStatut_livraison() + "', '" + livraison.getAdresse_livraison() + "', '" + livraison.getMontant_paiement() + "', '" + livraison.getMode_livraison() + "', '" + livraison.getId_client() + "')";

        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

            System.out.println("Livraison ajoutée avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(Livraison livraison) {
        String req="UPDATE `livraison` SET `id_commande`=?,`date_livraison`=?,`statut_livraison`=?,`adresse_livraison`=?,`montant_paiement`=?,`mode_livraison`=?,`id_client`=? WHERE id_livraison=?";
        PreparedStatement ps = null;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, livraison.getId_commande());
            ps.setString(2, livraison.getDate_livraison());
            ps.setString(3, livraison.getStatut_livraison());
            ps.setString(4, livraison.getAdresse_livraison());
            ps.setFloat(5, livraison.getMontant_paiement());
            ps.setString(6, livraison.getMode_livraison());
            ps.setInt(7, livraison.getId_client());
            ps.setInt(8, livraison.getId_livraison());
            ps.executeUpdate();
            System.out.println("Livraison mise à jour avec succès!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void supprimer(int id) {
        String req="DELETE FROM `livraison` WHERE id_livraison="+id;
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
    public List<Livraison> affichage() {

        List<Livraison> livraisonList= new ArrayList<>();
        String req="SELECT * FROM `livraison`";
        try {
            Statement st =cnx.createStatement();
            ResultSet result =st.executeQuery(req);
            while (result.next()){
                Livraison livraison =new Livraison();
                livraison.setId_livraison(result.getInt("id_livraison"));
                livraison.setId_commande(result.getInt("id_commande"));
                livraison.setDate_livraison(result.getString("date_livraison"));
                livraison.setStatut_livraison(result.getString("statut_livraison"));
                livraison.setAdresse_livraison(result.getString("adresse_livraison"));
                livraison.setMontant_paiement(result.getFloat("montant_paiement"));
                livraison.setMode_livraison(result.getString("mode_livraison"));
                livraison.setId_client(result.getInt("id_client"));
                livraisonList.add(livraison);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return livraisonList;
    }


}
