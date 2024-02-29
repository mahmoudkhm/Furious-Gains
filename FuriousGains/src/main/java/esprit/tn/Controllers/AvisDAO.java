package esprit.tn.Controllers;

import java.sql.*;

public class AvisDAO {
    public  int getTotalReviews() {
        int totalReviews = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FuriousGains", "root", "");


            statement = connection.createStatement();


            resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM avis");


            if (resultSet.next()) {
                totalReviews = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalReviews;
    }
}
