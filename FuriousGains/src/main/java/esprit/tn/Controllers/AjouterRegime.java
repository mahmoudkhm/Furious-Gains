package esprit.tn.Controllers;

import esprit.tn.Models.Regime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import esprit.tn.services.RegimeService;

public class AjouterRegime {
    @FXML
    private TextArea instructionTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField typeTF;

    private final RegimeService rs = new RegimeService();
    @FXML
    void ajouterR(ActionEvent event) {
        try {
            rs.ajouter(new Regime(typeTF.getText(), nomTF.getText(), instructionTF.getText()));
        } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
