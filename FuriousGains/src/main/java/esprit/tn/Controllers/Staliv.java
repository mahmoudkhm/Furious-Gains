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
            Orgpiechart.getData().add(new PieChart.Data("livraison " + l.getId_livraison(), l.getMontant_paiement()));
        }

        Orgpiechart.setTitle("Transportation Data");
        Orgpiechart.setLegendVisible(true);
        Orgpiechart.setLabelsVisible(false);
        XYChart.Series series1=new XYChart.Series();
        series1.setName("Hiver");
        series1.getData().add(new XYChart.Data("Décembre",ser.moyenneTempsdereponse(12)));
        series1.getData().add(new XYChart.Data("Janvier",ser.moyenneTempsdereponse(1)));
        series1.getData().add(new XYChart.Data("Février",ser.moyenneTempsdereponse(2)));
        System.out.println(ser.moyenneTempsdereponse(3));
        System.out.println(sr.CountType("probléme produit"));
        System.out.println(sr.CountType("probléme livraison"));
        System.out.println(sr.CountType("service client insatisfaisant"));
        System.out.println(sr.CountType("autres"));

        XYChart.Series series2=new XYChart.Series();
        series2.setName("Printemps");
        series2.getData().add(new XYChart.Data("Mars",ser.moyenneTempsdereponse(3)));
        series2.getData().add(new XYChart.Data("Avril",ser.moyenneTempsdereponse(4)));
        series2.getData().add(new XYChart.Data("Mai",ser.moyenneTempsdereponse(5)));

        XYChart.Series series3=new XYChart.Series();
        series3.setName("étè");
        series3.getData().add(new XYChart.Data("Juin",ser.moyenneTempsdereponse(6)));
        series3.getData().add(new XYChart.Data("Juillet",ser.moyenneTempsdereponse(7)));
        series3.getData().add(new XYChart.Data("Aout",ser.moyenneTempsdereponse(8)));

        XYChart.Series series4=new XYChart.Series();
        series4.setName("Autumn");
        series4.getData().add(new XYChart.Data("Septembre",ser.moyenneTempsdereponse(9)));
        series4.getData().add(new XYChart.Data("Novembre",ser.moyenneTempsdereponse(10)));
        series4.getData().add(new XYChart.Data("Octobre",ser.moyenneTempsdereponse(11)));

        chart.getData().addAll(series1,series2,series3,series4);
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
