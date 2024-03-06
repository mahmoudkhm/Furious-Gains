package esprit.tn.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.LivraisonService;
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
