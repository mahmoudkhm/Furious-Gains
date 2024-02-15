package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Commande;
import esprit.tn.Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommandeService implements InterfaceFuriousGains<Commande> {
    private Connection cnx;

    public CommandeService() {cnx = MyConnexion.getInstance().getCnx();}

    @Override
    public void ajouter(Commande commande) {
        String req="INSERT INTO `commande`(`id_client`, `statut_commande`, `montant_total`, `id_produit`) VALUES (????)";
        try {
            PreparedStatement ps=cnx.prepareStatement(req)  ;
            ps.setInt(1,commande.getId_client());
            ps.setString(2,commande.getStatut_commande());
            ps.setFloat(3,commande.getMontant_total());
            ps.setInt(4,commande.getId_produit());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void modifier(Commande commande) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public List<Commande> affichage() {
        return null;
    }
}
