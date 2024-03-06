package esprit.tn.Services;

import esprit.tn.Utils.MyConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingService {
    Connection cnx = MyConnexion.getInstance().getCnx();

    public void addRating( int recipeId, double rating) {
        try {
            String query = "INSERT INTO ratings (id_user, id_recette, rating) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, recipeId);
            preparedStatement.setDouble(3, rating);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateRating(int userId, int recipeId, int newRating) {
        try {
            String query = "UPDATE ratings SET rating = ? WHERE id_user = ? AND id_Recette = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, newRating);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, recipeId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public double getAverageRating(int recipeId) {
        double averageRating = 0;
        try {
            String query = "SELECT AVG(rating) AS average_rating FROM ratings WHERE id_Recette = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                averageRating = resultSet.getDouble("average_rating");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return averageRating;
    }

    public int getNumberOfVotes(int recipeId) {
        int numberOfVotes = 0;
        try {
            String query = "SELECT COUNT(*) AS vote_count FROM ratings WHERE id_recette = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numberOfVotes = resultSet.getInt("vote_count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return numberOfVotes;
    }
}
