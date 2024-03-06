package esprit.tn.Controllers;

import esprit.tn.Models.ForgetPwd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VerifCode {
    @FXML
    private TextField code;

    String code1=ReinitialiserPwd.getCode();

    @FXML
    private Label ver;
    @FXML
    void versChanferpwd(ActionEvent event) {
        if (code1==null){
            code1=ReinitialiserPwdSMS.getCode();
        }


        //String hashedPassword = BCrypt.hashpw(nouveauMotDePasse, BCrypt.gensalt());
        if (!code1.equals(code)){
           // ver.setText( ReinitialiserPwd.getCode());
                // Create a new stage for the forget password window
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/ModifierPWD.fxml"));
                    ver.getScene().setRoot(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        }else {
            Alert alertType = new Alert(Alert.AlertType.WARNING);
            alertType.setTitle("Code incorrect");
            alertType.setContentText("Veuillez saisir le code .");
            code.clear();
            alertType.show();
        }

    }
    @FXML
    void initialize() {
         code1= ReinitialiserPwd.getCode();
        ver.setText( ReinitialiserPwd.getCode());

    }
}

