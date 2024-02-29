package esprit.tn.Controllers;

import esprit.tn.Models.Recette;
import esprit.tn.Models.Regime;
import esprit.tn.services.RecetteService;
import esprit.tn.services.RegimeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AjouterRecette {


    @FXML
    private TextArea ingredientsTF;

    @FXML
    private TextField nom2TF;

    @FXML
    private TextField tempsTF;

    private final RecetteService r2s = new RecetteService();

    @FXML
    void ajouterR2(ActionEvent event) {

        try {
            r2s.ajouter(new Recette(nom2TF.getText(), tempsTF.getText(), ingredientsTF.getText()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            throw new RuntimeException(e);

        }
    }
    }

