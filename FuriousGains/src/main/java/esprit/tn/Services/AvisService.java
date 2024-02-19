package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Avis;
import esprit.tn.Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvisService implements InterfaceFuriousGains <Avis> {
    private Connection cnx;

    public AvisService() {
        cnx = MyConnexion.getInstance().getCnx();
}

    @Override
    public void ajouter(Avis avis) {
        String req = "INSERT INTO avis ( titre_avis, description_avis, id_user) VALUES ('" + avis.getTitre_avis() + "', '" + avis.getDescription_avis() + "', '" + avis.getId_user() +   "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void modifier(Avis avis) {
        String req = "UPDATE avis set titre_avis = ?, description_avis =? , id_user =?  where id_avis= ?";
        PreparedStatement as = null;
        try {
           as = cnx.prepareStatement(req);
            as.setString(1, avis.getTitre_avis());
            as.setString(2, avis.getDescription_avis());
            as.setFloat(3, avis.getId_user());
           ;
            as.setInt(4, avis.getId_avis());

            as.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void supprimer(int id) {


        String req = "DELETE FROM avis where id_avis = ?";
        PreparedStatement as = null;
        try {
            as = cnx.prepareStatement(req);
            as.setInt(1, id);
            as.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Avis> affichage() {

            List<Avis> listavis = new ArrayList<>();
            String req = "Select * FROM avis";
            try {
                Statement as = cnx.createStatement();
                ResultSet rs = as.executeQuery(req);
                while (rs.next()) {
                    Avis avis = new Avis();
                    avis.setId_avis(rs.getInt("id_avis"));
                   avis.setTitre_avis(rs.getString("Titre_avis"));
                    avis.setDescription_avis(rs.getString("Description_avis"));
                    avis.setId_user(rs.getInt("Id_user"));

listavis.add(avis);



                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return listavis;

        }

    }


