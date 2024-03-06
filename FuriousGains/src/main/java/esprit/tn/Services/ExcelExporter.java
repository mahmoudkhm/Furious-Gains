package esprit.tn.Services;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import esprit.tn.Models.Evenement;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExporter {

    public static void exportToExcel(ListView<Evenement> listView, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Events");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("nom");
        headerRow.createCell(2).setCellValue("lieu");
        headerRow.createCell(3).setCellValue("prix");
        headerRow.createCell(4).setCellValue("nb_participants");
        headerRow.createCell(5).setCellValue("date");
        headerRow.createCell(6).setCellValue("heure");
        headerRow.createCell(7).setCellValue("description");


        // Populate data rows
        ObservableList<Evenement> data = listView.getItems();
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Evenement event = data.get(i);
            row.createCell(0).setCellValue(event.getId_event());
            row.createCell(1).setCellValue(event.getNom_event());
            row.createCell(2).setCellValue(event.getLieu_event());
            row.createCell(3).setCellValue(event.getPrix_event());
            row.createCell(4).setCellValue(event.getNb_participation());
            row.createCell(5).setCellValue(event.getDate_event().toString());
            row.createCell(6).setCellValue(event.getHeure_event());
            row.createCell(7).setCellValue(event.getDescription());

        }

        // Write workbook to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
