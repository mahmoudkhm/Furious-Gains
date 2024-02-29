package esprit.tn.services;


import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Recette;
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
    private List<Regime> regimes = new ArrayList<>();
    private List<Recette> recettes = new ArrayList<>();


    @Override
    public void ajouter(Regime r1) {
        try {
            String req = "INSERT INTO regime (type_regime, nom_regime, instruction) VALUES ('"+r1.getType_regime()+"','"+r1.getNom_regime()+"','"+r1.getInstruction()+"' )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);



            System.out.println("Regime Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifier(Regime r1) {
        String sql = "UPDATE regime SET type_regime = ?, nom_regime = ?, instruction = ? WHERE id_regime = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, r1.getType_regime());
            preparedStatement.setString(2, r1.getNom_regime());
            preparedStatement.setString(3, r1.getInstruction());
            preparedStatement.setInt(4, r1.getId_regime());

            preparedStatement.executeUpdate();
            System.out.println("Regime updated successfully!");

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
                regimes.add(r1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return regimes;
    }
    @Override
    public void supprimer(int id) {
        try {
            // First, delete associated ratings from the Rating table for the recipes of the regime
            String deleteRatingsQuery = "DELETE FROM ratings WHERE id_Recette IN (SELECT id_Recette FROM recette WHERE id_regime = ?)";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteRatingsQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }

            // Then, delete associated recipes from the Recette table
            String deleteRecipesQuery = "DELETE FROM recette WHERE id_regime = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteRecipesQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }

            // Finally, delete the regime from the Regime table
            String deleteRegimeQuery = "DELETE FROM regime WHERE id_regime = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteRegimeQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Regime, associated recipes, and ratings deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recette> getRecipesForRegime(int regimeId) {
        List<Recette> associatedRecipes = new ArrayList<>();
        try {
            String query = "SELECT * FROM recette WHERE id_regime = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, regimeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recette recette = new Recette();
                recette.setId_Recette(resultSet.getInt("id_Recette"));
                recette.setNom_Recette(resultSet.getString("nom_Recette"));
                recette.setIngredients(resultSet.getString("ingredients"));
                recette.setTemps_preparation(resultSet.getString("temps_preparation"));
                recette.setId_Recette(resultSet.getInt("id_regime"));
                associatedRecipes.add(recette);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return associatedRecipes;
    }
    public Regime getRegimeById(int id) {
        Regime regime = null;
        try {
            String query = "SELECT * FROM regime WHERE id_regime = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                regime = new Regime();
                regime.setId_regime(resultSet.getInt("id_regime"));
                regime.setType_regime(resultSet.getString("type_regime"));
                regime.setNom_regime(resultSet.getString("nom_regime"));
                regime.setInstruction(resultSet.getString("instruction"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return regime;
    }


}

// join




