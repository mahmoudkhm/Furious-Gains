package esprit.tn.Services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Annonces;
import esprit.tn.Models.Avis;
import esprit.tn.Utils.MyConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnonceService  implements InterfaceFuriousGains <Annonces> {

    private Connection cnx;

    public AnnonceService() {
        cnx = MyConnexion.getInstance().getCnx();
    }


    @Override
    public void ajouter(Annonces annonce) {
        String req = "INSERT INTO annonces ( note, id_user, id_produit) VALUES ('" + annonce.getNote() + "', '" + annonce.getId_user() + "', '" + annonce.getId_produit() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(Annonces annonces) {
        String req = "UPDATE annonces set note = ?, id_user =? , id_produit =?  where id_annonces= ?";
        PreparedStatement ass = null;
        try {
            ass = cnx.prepareStatement(req);
            ass.setInt(1,annonces.getNote());
            ass.setInt(2, annonces.getId_user());
            ass.setInt(3, annonces.getId_produit());
            ;
            ass.setInt(4, annonces.getId_annonces());

            ass.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM annonces where id_annonces = ?";
        PreparedStatement ass = null;
        try {
            ass = cnx.prepareStatement(req);
            ass.setInt(1, id);
            ass.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Annonces> affichage() {
        List<Annonces> listannonces = new ArrayList<>();
        String req = "Select * FROM annonces";
        try {
            Statement ass = cnx.createStatement();
            ResultSet rs = ass.executeQuery(req);
            while (rs.next()) {
                Annonces annonce = new Annonces();
               annonce.setId_annonces(rs.getInt("id_annonces"));
                annonce.setNote(rs.getInt("note"));
                annonce.setId_user(rs.getInt("Id_user"));
                annonce.setId_produit(rs.getInt("Id_produit"));

                listannonces.add(annonce);



            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listannonces;

    }
    }


