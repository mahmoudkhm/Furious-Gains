package esprit.tn.Controllers;

import esprit.tn.Models.Regime;
import esprit.tn.services.RatingService;
import esprit.tn.services.RegimeService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;

public class AfficherRegime {

    @FXML
    private TextArea instructionTFup;

    @FXML
    private TableColumn<Regime,String> instructionsCol;

    @FXML
    private TableColumn<Regime,String> nomCol;

    @FXML
    private TextField nomTFup;

    @FXML
    private TableView<Regime> tableview;

    @FXML
    private TableColumn<Regime,String> typeCol;

    @FXML
    private TextField typeTFup;

    private final RegimeService rs = new RegimeService();



    @FXML
    void initialize() {
        // Populate the TableView
        List<Regime> Regimes = rs.affichage();
        tableview.getItems().addAll(Regimes);

        // Set cell value factories for each column
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type_regime"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_regime"));
        instructionsCol.setCellValueFactory(new PropertyValueFactory<>("instruction"));

        // Add listener to handle row selection changes
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Regime>() {
            @Override
            public void changed(ObservableValue<? extends Regime> observable, Regime oldValue, Regime newValue) {
                if (newValue != null) {
                    // Populate text fields with selected row data
                    nomTFup.setText(newValue.getNom_regime());
                    instructionTFup.setText(newValue.getInstruction());
                    typeTFup.setText(newValue.getType_regime());
                }
            }
        });
    }

    // Method to handle update action
    @FXML
    void ajouterRup(ActionEvent event) {
        Regime selectedRegime = tableview.getSelectionModel().getSelectedItem();
        if (selectedRegime != null) {
            // Update the selected regime with the new values
            selectedRegime.setNom_regime(nomTFup.getText());
            selectedRegime.setInstruction(instructionTFup.getText());
            selectedRegime.setType_regime(typeTFup.getText());

            // Call the update method in the service
            rs.modifier(selectedRegime);

            // Optionally, you can refresh the table to reflect the changes
            tableview.refresh();
        }
    }
    @FXML
    void SupprimerR(ActionEvent event) {
        // Get the selected regime from the tableview
        Regime selectedRegime = tableview.getSelectionModel().getSelectedItem();

        if (selectedRegime != null) {
            // Call the delete method in the RegimeService
            rs.supprimer(selectedRegime.getId_regime());

            // Remove the selected regime from the TableView
            tableview.getItems().remove(selectedRegime);

            // Optionally, you can clear the text fields
            nomTFup.clear();
            instructionTFup.clear();
            typeTFup.clear();
        }
    }

}
