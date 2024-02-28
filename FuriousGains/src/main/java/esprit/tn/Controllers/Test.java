package esprit.tn.Controllers;

import esprit.tn.Models.Traducteur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Test {
    @FXML
    private Button traduireButton;

    @FXML
    private TextField texteATraduireTF;

    @FXML
    private TextField texteTraduitTF;

    private Traducteur traducteur;

    public void initialize() {
        String apiKey = "YOUR_API_KEY"; // Remplacez YOUR_API_KEY par votre clé d'API
        traducteur = new Traducteur(apiKey);
    }

    @FXML
    void traduireTexte(ActionEvent event) {
        String texteATraduire = texteATraduireTF.getText();
        String langueSource = "fr"; // Remplacez fr par la langue source appropriée
        String langueCible = "en"; // Remplacez en par la langue cible appropriée

        String texteTraduit = traducteur.traduireTexte(texteATraduire, langueSource, langueCible);
        texteTraduitTF.setText(texteTraduit);
    }
}
