package esprit.tn.Controllers;

import esprit.tn.Models.CodePromo;
import esprit.tn.Services.CodeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class AfficherCode {
    @FXML
    private ListView<CodePromo> listeCode;

    @FXML
    private TextField supCode;
    private final CodeService cs=new CodeService();

    @FXML
    void on_click(MouseEvent event) {
        int  selectedItem = listeCode.getSelectionModel().getSelectedItem().getCode();
        supCode.setText(String.valueOf(selectedItem));
    }

    @FXML
    void supprimerCode(ActionEvent event) {
        cs.delete2(Integer.parseInt(supCode.getText()));
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/AfficherCode.fxml"));
            supCode.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void initialize(){
        List<CodePromo> codes= null;
        codes = cs.afficher();
        ObservableList<CodePromo> observableList = FXCollections.observableList(codes);
        listeCode.setItems(observableList);
    }
}
