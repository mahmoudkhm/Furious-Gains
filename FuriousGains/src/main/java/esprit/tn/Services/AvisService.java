package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Avis;
import esprit.tn.Utils.MyConnexion;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvisService implements InterfaceFuriousGains<Avis> {
    private Connection cnx;

    public AvisService() {
        cnx = MyConnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Avis avis) {
        String req = "INSERT INTO avis ( note, id_user, id_produit) VALUES ('" + avis.getNote() + "', '" + avis.getId_user() + "', '" + avis.getId_produit() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succés");
            alert.setContentText("annonce ajoutée avec succés!");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Echec d'ajout");
            alert.showAndWait();
        }

    }

    @Override
    public void modifier(Avis avis) {
        String req = "UPDATE avis set note = ?, id_user =? , id_produit =?  where id_avis= ?";
        PreparedStatement ass = null;
        try {
            ass = cnx.prepareStatement(req);
            ass.setInt(1,avis.getNote());
            ass.setInt(2, avis.getId_user());
            ass.setInt(3, avis.getId_produit());
            ;
            ass.setInt(4, avis.getId_avis());
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succés");
            alert.setContentText("annonce modifiée avec succés!");
            alert.showAndWait();


            ass.executeUpdate();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("error");
            alert.setContentText("echec de modification!");
            alert.showAndWait();
        }

    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM avis where id_avis = ?";
        PreparedStatement ass = null;
        try {
            ass = cnx.prepareStatement(req);
            ass.setInt(1, id);
            ass.executeUpdate();
        } catch (SQLException e) {
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succés");
            alert.setContentText("annonce supprimée avec succés!");
            alert.showAndWait();
        }
    }

    @Override
    public List<Avis> affichage() {
        List<Avis> listavis = new ArrayList<>();
        String requete = "SELECT avis.*, user.nom FROM avis INNER JOIN user ON avis.Id_user = user.Id_user";;
        try {
            Statement ass = cnx.createStatement();
            ResultSet rs = ass.executeQuery(requete);
            while (rs.next()) {
                Avis avis = new Avis();
                avis.setId_avis(rs.getInt("id_avis"));
                avis.setNote(rs.getInt("note"));
                avis.setId_user(rs.getInt("Id_user"));
                avis.setId_produit(rs.getInt("Id_produit"));
                avis.setNom(rs.getString("nom"));

                listavis.add(avis);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listavis;

    }

        public List<Avis> recherche(String par) {
        List<Avis> listavis = new ArrayList<>();

        try {
           // String requete = "SELECT * FROM avis WHERE `note` like ?";
            String requete = "SELECT avis.*, user.nom FROM avis INNER JOIN user ON avis.Id_user = user.Id_user WHERE avis.note LIKE ?";;

            PreparedStatement statement = this.cnx.prepareStatement(requete);
            statement.setString(1, "%"+par+"%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Avis avis = new Avis();
                    avis.setId_avis(rs.getInt("id_avis"));
                    avis.setNote(rs.getInt("note"));
                    avis.setId_user(rs.getInt("Id_user"));
                    avis.setId_produit(rs.getInt("Id_produit"));
                    avis.setNom(rs.getString("nom"));
                    listavis.add(avis);

                }
            }
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
        }

        return listavis;
    }


}

