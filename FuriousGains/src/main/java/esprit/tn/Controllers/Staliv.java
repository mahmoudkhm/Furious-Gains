package esprit.tn.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.LivraisonService;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.Comparator;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Staliv implements Initializable {
    LivraisonService ls =new LivraisonService();
    public PieChart transportPieChart;
    @FXML
    private PieChart Orgpiechart;
int x,y;
    @FXML
    private BarChart<Number,String> chart;

    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LivraisonService livraisonService = new LivraisonService();
        List<Livraison> livraisons = livraisonService.affichage(); // Corrected method call

        for (Livraison l : livraisons) {
            Orgpiechart.getData().add(new PieChart.Data("livraison " + l.getId_livraison(), livraisons.size()));
        }
        Orgpiechart.setTitle("Transportation Data");
        Orgpiechart.setLegendVisible(true);
        Orgpiechart.setLabelsVisible(false);
        XYChart.Series series1=new XYChart.Series();
        series1.setName("1");
        series1.getData().add(new XYChart.Data("Ben Arous",ls.countAdresse("Ben Arous")));
        series1.getData().add(new XYChart.Data("Tunis",ls.countAdresse("Tunis")));
        series1.getData().add(new XYChart.Data("Ariana",ls.countAdresse("Ariana")));
        series1.getData().add(new XYChart.Data("Zaghouan",ls.countAdresse("Zaghouan")));
        series1.getData().add(new XYChart.Data("Nebil",ls.countAdresse("Nebil")));
        series1.getData().add(new XYChart.Data("Manouba",ls.countAdresse("Manouba")));


        XYChart.Series series2=new XYChart.Series();
        series2.setName(" 2");
        series1.getData().add(new XYChart.Data("Bizerte",ls.countAdresse("Bizerte")));
        series2.getData().add(new XYChart.Data("Beja",ls.countAdresse("Beja")));
        series2.getData().add(new XYChart.Data("Jandouba",ls.countAdresse("Jandouba")));
        series2.getData().add(new XYChart.Data("Kef",ls.countAdresse("Kef")));

        XYChart.Series series3=new XYChart.Series();
        series3.setName(" 3");
        series3.getData().add(new XYChart.Data("Seliena",ls.countAdresse("Seliena")));
        series3.getData().add(new XYChart.Data("Sousse",ls.countAdresse("Sousse")));
        series3.getData().add(new XYChart.Data("Gasrine",ls.countAdresse("Gasrine")));
        series3.getData().add(new XYChart.Data("Mestir",ls.countAdresse("Mestir")));
        series3.getData().add(new XYChart.Data("Mahdia",ls.countAdresse("Mahdia")));
        XYChart.Series series4=new XYChart.Series();
        series4.setName("4");
        series4.getData().add(new XYChart.Data("tataouin",ls.countAdresse("Touzer")));
        series4.getData().add(new XYChart.Data("Gabes",ls.countAdresse("Sidi Bouzid")));
        series4.getData().add(new XYChart.Data("Mednine",ls.countAdresse("Sfax")));
        series4.getData().add(new XYChart.Data("Mednine",ls.countAdresse("Gafsa")));
        XYChart.Series series5=new XYChart.Series();
        series5.setName("5");
        series5.getData().add(new XYChart.Data("tataouin",ls.countAdresse("tataouin")));
        series5.getData().add(new XYChart.Data("Gabes",ls.countAdresse("Gabes")));
        series5.getData().add(new XYChart.Data("Mednine",ls.countAdresse("Mednine")));
        series5.getData().add(new XYChart.Data("Mednine",ls.countAdresse("Mednine")));


        chart.getData().addAll(series1,series2,series3,series4,series5);
    }

    @FXML
    void retour(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherLivraison.fxml"));
            javafx.scene.Parent root = loader.load();


            Stage stage = (Stage) back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
