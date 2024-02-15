package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Livraison;
import esprit.tn.Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LivraisonService implements InterfaceFuriousGains <Livraison> {
    private Connection cnx;

    public LivraisonService() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Livraison livraison) {
        String req = "INSERT INTO `livraison`(`id_commande`, `date_livraison`, `statut_livraison`, `adresse_livraison`, `montant_paiement`, `mode_livraison`, `id_client`)" +
                " VALUES (???????)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, livraison.getId_commande());
            ps.setString(2, livraison.getDate_livraison());
            ps.setString(3, livraison.getStatut_livraison());
            ps.setString(4, livraison.getAdresse_livraison());
            ps.setFloat(5, livraison.getMontant_paiement());
            ps.setString(6, livraison.getMode_livraison());
            ps.setInt(7, livraison.getId_client());
            ps.executeUpdate();
            System.out.println("livraison Added Successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void modifier(Livraison livraison) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public List<Livraison> affichage() {
        return null;
    }
}
