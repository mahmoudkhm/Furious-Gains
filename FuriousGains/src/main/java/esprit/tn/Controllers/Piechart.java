package esprit.tn.Controllers;

import esprit.tn.Utils.MyConnexion;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Piechart extends Application {

    @FXML
    private PieChart piechart;

    static Connection connection = null;
    PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    public Piechart() {
        connection = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void start(Stage stage) {
        // Create the PieChart
        PieChart pieChart = new PieChart();

        // Fetch data from the database and add it to the PieChart
        fetchDataAndPopulatePieChart(pieChart);

        // Create the Scene
        Scene scene = new Scene(pieChart, 600, 400);

        // Set the Scene to the Stage
        stage.setScene(scene);
        stage.setTitle("Pie Chart Test");
        stage.show();
    }

    private void fetchDataAndPopulatePieChart(PieChart pieChart) {
        try {
            String query = "SELECT note, COUNT(*) AS count FROM avis GROUP BY note";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Map to store product ID and its corresponding count
            Map<Integer, Integer> AvisCountMap = new HashMap<>();

            // Total count of all products
            int totalCount = 0;

            // Iterate through the result set and populate the map
            while (resultSet.next()) {
                int note = resultSet.getInt("note");
                int count = resultSet.getInt("count");
                AvisCountMap.put(note, count);
                totalCount += count;
            }

            // Add data to the PieChart
            for (Map.Entry<Integer, Integer> entry : AvisCountMap.entrySet()) {
                int note = entry.getKey();
                int count = entry.getValue();
                double percentage = ((double) count / totalCount) * 100; // Calculate percentage
                PieChart.Data data = new PieChart.Data("note " + note + " (" + String.format("%.2f", percentage) + "%)", count);

                // Apply animation
                applyFadeInAnimation(data);

                pieChart.getData().add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Apply fade-in animation to pie chart data
    private void applyFadeInAnimation(PieChart.Data data) {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), data.getNode());
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    public static void main(String[] args) {

        launch(args);
    }

}