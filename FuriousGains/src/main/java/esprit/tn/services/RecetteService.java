package esprit.tn.services;

import esprit.tn.Interfaces.InterfaceFuriousGains;
import esprit.tn.Models.Recette;
import esprit.tn.Utils.MyConnexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class RecetteService implements InterfaceFuriousGains<Recette> {
    Connection cnx = MyConnexion.getInstance().getCnx();

    @Override
    public void ajouter(Recette r2) {
        try {
            String req = "INSERT INTO recette (nom_Recette, ingredients, temps_preparation,id_regime) VALUES (?, ?, ?,?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, r2.getNom_Recette());
            preparedStatement.setString(2, r2.getIngredients());
            preparedStatement.setString(3, r2.getTemps_preparation());
            preparedStatement.setInt(4, 8);

            preparedStatement.executeUpdate();
            System.out.println("Recette Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifier(Recette r2) {
        String sql = "UPDATE recette SET nom_Recette = ?, ingredients = ?, temps_preparation = ?   WHERE id_Recette = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, r2.getNom_Recette());
            preparedStatement.setString(2, r2.getIngredients());
            preparedStatement.setString(3, r2.getTemps_preparation());

            preparedStatement.setInt(4, r2.getId_Recette());


            preparedStatement.executeUpdate();
            System.out.println("Recette updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            // First, delete associated ratings from the Rating table
            String deleteRatingsQuery = "DELETE FROM ratings WHERE id_Recette = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteRatingsQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Ratings associated with the recipe deleted successfully!");
            }

            // Then, delete the recipe from the Recette table
            String deleteRecetteQuery = "DELETE FROM recette WHERE id_Recette = ?";
            try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteRecetteQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Recette Deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recette> affichage() {
        List<Recette> recettes = new ArrayList<>();
        try {
            String req = "SELECT * FROM recette";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Recette r2 = new Recette();
                r2.setId_Recette(rs.getInt("id_Recette"));
                r2.setNom_Recette(rs.getString("nom_Recette"));
                r2.setIngredients(rs.getString("ingredients"));
                r2.setTemps_preparation(rs.getString("temps_preparation"));
                r2.setId_regime(rs.getInt("id_regime"));
                recettes.add(r2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recettes;
    }
}