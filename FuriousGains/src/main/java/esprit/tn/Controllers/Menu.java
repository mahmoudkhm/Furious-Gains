package esprit.tn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class Menu {
    @FXML
    private void handleMenuItemClick(ActionEvent event) {
        Button menuItem = (Button) event.getSource();
        String menuText = menuItem.getText();
        // Gérer le clic sur l'élément du menu ici
        System.out.println("Menu item clicked: " + menuText);
    }
}
