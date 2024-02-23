package services;


import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Regime;
import esprit.tn.Utils.MyConnexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class RegimeService implements InterfaceFuriousGains<Regime> {
    Connection cnx = MyConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Regime r1) {
        try {
            String req = "INSERT INTO regime (type_regime, nom_regime, instruction, id_recette) VALUES ('"+r1.getType_regime()+"','"+r1.getNom_regime()+"','"+r1.getInstruction()+"' ,"+r1.getId_recette()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);



            System.out.println("Regime Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifier(Regime r1) {
        String sql = "UPDATE regime SET type_regime = ?, nom_regime = ?, instruction = ?, id_recette = ? WHERE id_regime = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, r1.getType_regime());
            preparedStatement.setString(2, r1.getNom_regime());
            preparedStatement.setString(3, r1.getInstruction());
            preparedStatement.setInt(4, r1.getId_recette());
            preparedStatement.setInt(5, r1.getId_regime());

            preparedStatement.executeUpdate();
            System.out.println("Regime updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM regime WHERE id_regime = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Regime Deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Regime> affichage() {
        List<Regime> regimes = new ArrayList<>();
        try {
            String req = "SELECT * FROM regime";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Regime r1 = new Regime();
                r1.setId_regime(rs.getInt("id_regime"));
                r1.setType_regime(rs.getString("type_regime"));
                r1.setNom_regime(rs.getString("nom_regime"));
                r1.setInstruction(rs.getString("instruction"));
                r1.setId_recette(rs.getInt("id_recette"));
                regimes.add(r1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return regimes;
    }
}




