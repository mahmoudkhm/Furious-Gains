package esprit.tn.Controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import esprit.tn.Models.Recette;
import esprit.tn.Models.Regime;
import esprit.tn.services.RatingService;
import esprit.tn.services.RecetteService;
import esprit.tn.services.RegimeService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AfficherRecette {

    @FXML
    private TableColumn<Recette, String> IngredientsCol;

    @FXML
    private TableColumn<Recette, String> RatingCol;

    @FXML
    private TableColumn<Recette, String> RegimeCol;

    @FXML
    private TextField ingredientsRecetteUpdate;

    @FXML
    private TableColumn<Recette, String> nomCol;

    @FXML
    private TextField nomRecetteUpdate;

    @FXML
    private TableColumn<Recette, String> prepCol;

    @FXML
    private TextField prepRecetteUpdate;

    @FXML
    private TableColumn<Recette, String> ratersCol;

    @FXML
    private TextField ratingRate;
    @FXML
    private TableView<Recette> tableview;
    private final RecetteService recetteService = new RecetteService();
    private final RegimeService regimeService = new RegimeService();
    private final RatingService ratingService = new RatingService();

    @FXML
    void initialize() {
        // Populate the TableView
        List<Recette> recettes = recetteService.affichage();
        tableview.getItems().addAll(recettes);

        // Set cell value factories for each column
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_Recette"));
        prepCol.setCellValueFactory(new PropertyValueFactory<>("temps_preparation"));
        IngredientsCol.setCellValueFactory(new PropertyValueFactory<>("ingredients"));


        // Add a new TableColumn for displaying regime name
        RegimeCol.setCellValueFactory(cellData -> {
            Recette recette = cellData.getValue();
            int regimeId = recette.getId_regime();
            Regime regime = regimeService.getRegimeById(regimeId);
            if (regime != null) {
                return new SimpleStringProperty(regime.getNom_regime());
            } else {
                return new SimpleStringProperty("Unknown"); // Or any default value
            }
        });

        // Add a new TableColumn for displaying ratings
        RatingCol.setCellValueFactory(cellData -> {
            Recette recette = cellData.getValue();
            int recipeId = recette.getId_Recette();
            double averageRating = ratingService.getAverageRating(recipeId);
            return new SimpleObjectProperty<>(averageRating).asString();
        });

        ratersCol.setCellValueFactory(cellData -> {
            Recette recette = cellData.getValue();
            int recipeId = recette.getId_Recette();
            int numberOfVotes = ratingService.getNumberOfVotes(recipeId);
            return new SimpleStringProperty(String.valueOf(numberOfVotes));
        });


        // Format the ratings to display two decimal places


        // Add the columns to the TableView
       // tableview.getColumns().addAll(nomCol, prepCol, IngredientsCol, RegimeCol, RatingCol);
        // Add the columns to the TableView only if they are not already added
        if (!tableview.getColumns().contains(nomCol)) {
            tableview.getColumns().add(nomCol);
        }
        if (!tableview.getColumns().contains(prepCol)) {
            tableview.getColumns().add(prepCol);
        }
        if (!tableview.getColumns().contains(IngredientsCol)) {
            tableview.getColumns().add(IngredientsCol);
        }
        if (!tableview.getColumns().contains(RegimeCol)) {
            tableview.getColumns().add(RegimeCol);
        }
        if (!tableview.getColumns().contains(RatingCol)) {
            tableview.getColumns().add(RatingCol);
        }

        // Add listener to handle row selection changes
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Recette>() {
            @Override
            public void changed(ObservableValue<? extends Recette> observable, Recette oldValue, Recette newValue) {
                if (newValue != null) {
                    // Populate text fields with selected row data
                    nomRecetteUpdate.setText(newValue.getNom_Recette());
                    prepRecetteUpdate.setText(newValue.getTemps_preparation());
                    ingredientsRecetteUpdate.setText(newValue.getIngredients());
                    // You can also fetch and display the regime name here if needed
                }
            }
        });
    }

    @FXML
    void ModifierRecette(ActionEvent event) {
        // Get the selected item from the TableView
        Recette selectedRecette = tableview.getSelectionModel().getSelectedItem();

        if (selectedRecette != null) {
            // Validate input
            String nomRecette = nomRecetteUpdate.getText().trim();
            String prepTime = prepRecetteUpdate.getText().trim();
            String ingredients = ingredientsRecetteUpdate.getText().trim();

            if (!nomRecette.isEmpty() && !prepTime.isEmpty() && !ingredients.isEmpty()) {
                // Display confirmation dialog
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("Modify Recipe");
                confirmationAlert.setContentText("Are you sure you want to modify this recipe?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Update the selected recipe with the new values
                    selectedRecette.setNom_Recette(nomRecette);
                    selectedRecette.setTemps_preparation(prepTime);
                    selectedRecette.setIngredients(ingredients);

                    // Call the update method in the service
                    recetteService.modifier(selectedRecette);

                    // Optionally, you can refresh the table to reflect the changes
                    tableview.refresh();
                }
            } else {
                // Show an error message if any of the fields are empty
                showAlert("Error", "Please fill in all fields.");
            }
        }
    }

    @FXML
    void SupprimerRecette(ActionEvent event) {
        // Get the selected item from the TableView
        Recette selectedRecette = tableview.getSelectionModel().getSelectedItem();

        if (selectedRecette != null) {
            // Display confirmation dialog
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Delete Recipe");
            confirmationAlert.setContentText("Are you sure you want to delete this recipe?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove the selected recipe from the TableView
                tableview.getItems().remove(selectedRecette);

                // Also, delete the recipe from the database using your service
                recetteService.supprimer(selectedRecette.getId_Recette());
            }
        } else {
            // No recipe selected, display an error message
            showAlert("Error", "Please select a recipe to delete.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void RateRecette(ActionEvent event) {
        Recette selectedRecette = tableview.getSelectionModel().getSelectedItem();
        if (selectedRecette != null) {
            String ratingText = ratingRate.getText();
            try {
                double ratingValue = Double.parseDouble(ratingText);
                // Ensure the rating value is within the valid range (0-10)
                if (ratingValue >= 0 && ratingValue <= 10) {
                    // Save or update the rating in the database using your RatingService
                    ratingService.addRating(selectedRecette.getId_Recette(), ratingValue);
                    tableview.refresh();
                    // Optionally, update the displayed rating in the TableView if needed
                } else {
                    // Display an error message indicating that the rating value should be between 0 and 10
                }
            } catch (NumberFormatException e) {
                // Display an error message indicating that the entered value is not a valid number
            }
        } else {
            // No recipe selected, display a message or handle it accordingly
        }
    }
    @FXML
    void generateExcel(ActionEvent event) {
        // Get data from your tableview or any other source
        List<Recette> recettes = tableview.getItems();

        // Create a new Excel workbook (HSSFWorkbook for .xls format)
        Workbook workbook = new HSSFWorkbook();

        // Create a sheet
        Sheet sheet = workbook.createSheet("Recettes");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nom Recette");
        headerRow.createCell(1).setCellValue("Temps de Préparation");
        headerRow.createCell(2).setCellValue("Ingrédients");
        // Add more columns if needed

        // Populate data rows
        int rowNum = 1;
        for (Recette recette : recettes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(recette.getNom_Recette());
            row.createCell(1).setCellValue(recette.getTemps_preparation());
            row.createCell(2).setCellValue(recette.getIngredients());
            // Add more cell data if needed
        }

        // Write the workbook content to a file
        try (FileOutputStream fileOut = new FileOutputStream("Recettes.xls")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void generatePdf(ActionEvent event) {
        // Get data from your tableview or any other source
        List<Recette> recettes = tableview.getItems();

        // Create a new PDF document
        Document document = new Document();

        try {
            // Write PDF content to a file
            try {
                PdfWriter.getInstance(document, new FileOutputStream("Recettes.pdf"));
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            document.open();

            // Add content to the PDF document
            for (Recette recette : recettes) {
                document.add(new Paragraph("Nom Recette: " + recette.getNom_Recette()));
                document.add(new Paragraph("Temps de Préparation: " + recette.getTemps_preparation()));
                document.add(new Paragraph("Ingrédients: " + recette.getIngredients()));
                // Add more content if needed
                document.add(new Paragraph("\n")); // Add space between recettes
            }

            document.close();
            System.out.println("PDF file generated successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }



}
