package esprit.tn.Controllers;
import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.BackgroundProducer;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.backgrounds.TransparentBackgroundProducer;
import cn.apiclub.captcha.gimpy.GimpyRenderer;
import cn.apiclub.captcha.gimpy.RippleGimpyRenderer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.noise.NoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.producer.TextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import cn.apiclub.captcha.text.renderer.WordRenderer;
import esprit.tn.Models.User;
import esprit.tn.Securite.BCrypt;
import esprit.tn.Services.UserService;
import esprit.tn.Utils.MyConnexion;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Login implements Initializable {
    @FXML
    private ImageView cap;

    @FXML
    private TextField adresse;
    @FXML
    private Button login;
    @FXML
    private PasswordField password;
    private final UserService us = new UserService();
    private static String nom;
    private static String prenom;
    private static String role;
    private String pwd;

    private  String image;

    private static String addresse;
    private static String ban;
    private static int numtel;
    private  static int cin;
    private Connection cnx;

    public String getImage() {
        return image;
    }
    @FXML
    private TextField pwdd;
    public void setImage(String image) {
        this.image = image;
    }

    public Login() {cnx= MyConnexion.getInstance().getCnx();
    }

    public static int getCin() {
        return cin;
    }

    public static void setCin(int cin) {
        Login.cin = cin;
    }
    private Preferences preferences;
    boolean loginSuccessful =false;


    /*yasna3 obj captcha w yestaaml feha l biblio JavaSimpleCaptcha. Elle définit les propriétés de l'image Captcha
      puis renvoie l'objet Captcha créé. */
    public Captcha setCaptcha() {
        Captcha captchaV;
        captchaV = new Captcha.Builder(250, 100)
                .addText()
                .addBackground(new GradiatedBackgroundProducer()) // Ajout d'un fond gradient
                .addNoise()
                .addBorder()
                .build();
        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);
        cap.setImage(image);
        return captchaV;
    }
    Captcha captcha;
    @FXML
    void reseting(ActionEvent event) {
        captcha =  setCaptcha();
        code.clear();
    }

    @FXML
    private TextField code;
    @FXML
    void connecter(ActionEvent event) throws IOException {
        preferences = Preferences.userRoot().node(this.getClass().getName());
        Alert alertType;
        String email= adresse.getText();
        String mdp=password.getText();;
        if(pwdd.isVisible()){
             mdp=pwdd.getText();
        }
        if(password.isVisible()){
            mdp=password.getText();

        }
       // Preferences prefs = Preferences.userNodeForPackage(Login.class);
        String emailRegex = "\\w+\\.?\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}";
        if (adresse.getText().equals("") || password.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Tous les champs sont oligatoire", ButtonType.CLOSE).show();
        }else {
            if (!email.matches(emailRegex)) {
                alertType = new Alert(Alert.AlertType.ERROR);
                alertType.setTitle("Error");
                alertType.setHeaderText("L'adresse email est invalide. Veuillez saisir une adresse email valide (ex: nom_utilisateur@domaine.com) !");
                alertType.show();
                return;
            } else {
                if (captcha.isCorrect(code.getText())) {

                    String tilte = "Captcha";
                    String message = "Vous avez saisi le code avec succés!";
                    System.out.println(message);

                String req = "SELECT * from user";
                PreparedStatement pst = null;
                try {
                    pst = this.cnx.prepareStatement(req);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        if (rs.getString("email").equals(email) && BCrypt.checkpw(mdp, rs.getString("password"))) {

                            pwd = rs.getString("password");
                            role = rs.getString("role");
                            image = rs.getString("image");
                            ban = rs.getString("ban");
                            break;
                        } else {
                            System.err.println("Verifier vos donneés !");
                        }
                    }
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
                User user = us.verifierEmailMdp(email, password.getText());
                if (role != null) {
                    nom = user.getNom();
                    prenom = user.getPrenom();
                    cin = user.getCin();
                    if (ban.equals("0")) {
                        if (role.equals("Admin")) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Erreur");
                            alert.setHeaderText("bonjour " + nom + " " + prenom);
                            alert.setContentText("Connecter en tant qu'admin!.");
                            alert.show();
                            //prefs.putInt("iduser", user.getId_user());
                            Parent root = null;
                            try {
                                loginSuccessful = true;
                                root = FXMLLoader.load(getClass().getResource("/Admin.fxml"));
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                if (loginSuccessful) {
                                    // Save username and password
                                    preferences.put("username", adresse.getText());
                                    preferences.put("password", password.getText());
                                    preferences.put("cin", Integer.toString(cin));

                                    System.out.println("sayeeeeeee");
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (role.equals("Client")) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("bienvenue sur FuriousGains");
                            alert.setHeaderText("bonjour " + nom + " " + prenom);
                            alert.setContentText("Connecter en tant que Client!.");
                            alert.show();
                            // prefs.putInt("iduser", user.getId_user());
                            User u = new User();
                            u.getId_user();
                            Parent root = null;
                            try {
                                loginSuccessful = true;
                                root = FXMLLoader.load(getClass().getResource("/Front.fxml"));
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                if (loginSuccessful) {
                                    // Save username and password
                                    preferences.put("username", adresse.getText());
                                    preferences.put("password", password.getText());
                                    preferences.put("cin", Integer.toString(cin));

                                    System.out.println("sayeeeeeee");
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setContentText("votre compte est bloqué !.");
                        alert.show();
                        password.clear();
                        adresse.clear();
                    }
                }
                }

                else {

                    String tilte = "Captcha";
                    String message = "Vous avez saisi un faux code !";
                    System.out.println(message);
                    captcha =  setCaptcha();
                    code.clear();
                }
            }
        }
    }





    @FXML
    void inscrireUser(ActionEvent event) {
        Parent root = null;


        try {
            root = FXMLLoader.load(getClass().getResource("/AjouterUser.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private CheckBox select;
    @FXML
    void selected(ActionEvent event) {
        if (select.isSelected()){
            pwdd.setText(password.getText());
            pwdd.setVisible(true);
            password.setVisible(false);
        }else {
            password.setText(pwdd.getText());
            pwdd.setVisible(false);
            password.setVisible(true);
        }
    }
    @FXML
    private Button reset;
    @FXML
    void resetP(ActionEvent event) {
        /*Parent page2 = null;
        try {
            page2 = (Parent) FXMLLoader.load(this.getClass().getResource("/ReinitialiserPwd.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReinitialiserPwd.fxml"));
        Parent signInRoot = null;
        try {
            signInRoot = loader.load();
            Scene signInScene = new Scene(signInRoot);

            // Create a new stage for the forget password window
            Stage forgetPwdStage = new Stage();
            forgetPwdStage.initModality(Modality.APPLICATION_MODAL); // Set modality to APPLICATION_MODAL
            forgetPwdStage.setScene(signInScene);
            forgetPwdStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    private TextField test;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        captcha =  setCaptcha();

        preferences = Preferences.userRoot().node(this.getClass().getName());

        // Load saved username and password if available
        String savedUsername = preferences.get("username", "");
        String savedPassword = preferences.get("password", "");
        String cin = preferences.get("cin", "");

        // Display saved username in the username field
        adresse.setText(savedUsername);
        password.setText(savedPassword);
        //test.setText(cin);// Always set the saved password
    }

    @FXML
    void logout(DisconnectEvent event) {
        // Remove saved username and password
        preferences.remove("username");
        preferences.remove("password");

        // Clear the username and password fields in the UI
        adresse.clear();
        password.clear();
    }

}
