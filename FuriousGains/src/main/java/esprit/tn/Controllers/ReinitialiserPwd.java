package esprit.tn.Controllers;

import esprit.tn.Models.ForgetPwd;
import esprit.tn.Models.User;
import esprit.tn.Securite.BCrypt;
import esprit.tn.Services.SMS;
import esprit.tn.Services.UserService;
import esprit.tn.Utils.MyConnexion;
import esprit.tn.mail.Smail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ReinitialiserPwd {

private Connection cnx;
private static String code;

    public static String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    Smail mail =new Smail();


    public ReinitialiserPwd(Connection cnx) {
        this.cnx =  MyConnexion.getInstance().getCnx();
    }

    Random rnd = new Random();
    int number;
    long start;
    String sTime;
    String Object;
    String Subject;
    private  static int cin;

    public static int getCin() {
        return cin;
    }

    public static void setCin(int cin) {
        ReinitialiserPwd.cin = cin;
    }

    public static String sEmail;

    @FXML
    private TextField adresse;
    UserService us=new UserService();

    public ReinitialiserPwd() {
        this.number = this.rnd.nextInt(999999);
        this.start = System.currentTimeMillis();
        this.sTime = Long.toString(this.start);
        this.Object = "Réinitialiser Votre mot de passe";
        this.Subject = "Votre Code est :  " + this.number + "\n S'il te plait ne passe pas 5 min De maintenant";
    }

    @FXML
    void Send(ActionEvent event) throws SQLException {
        UserService userService = new UserService();
        String req = "SELECT user.*, codepromo.code FROM user JOIN codepromo ON user.id_code_promo = codepromo.id_code_promo  WHERE email = ?";
        cnx= MyConnexion.getInstance().getCnx();
        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setString(1, adresse.getText());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            User u = new User(
                    rs.getInt("id_user" ),
                    rs.getInt("cin" ),
                    rs.getString("nom" ),
                    rs.getString("prenom" ),
                    rs.getDate("dateuser" ),
                    rs.getInt("num_tel" ),
                    rs.getString("adresse" ),
                    rs.getString("email" ),
                    rs.getString("password" ),
                    rs.getString("image" ),
                    rs.getString("role" ),
                    rs.getInt("code" )

            );
            cin =u.getCin();
            u.setCin(cin);
        }

        if (!adresse.getText().isEmpty()) {
            String nouveauMotDePasse = genererMotDePasseAleatoire(8);
            System.out.println("le nouveaux mdp est : "+nouveauMotDePasse);
            //SMS send=new SMS();
            //send.sendSMS(adresse.getText(),utilisateur.getNom(),nouveauMotDePasse);

            mail.sendEmail(adresse.getText(),"Réinitialiser Votre mot de passe","Votre Code est :  " + nouveauMotDePasse + "\n S'il te plait ne passe pas 5 min De maintenant");
            System.out.println("mdp envoyer");
            setCode(nouveauMotDePasse);
            code=nouveauMotDePasse;
            ForgetPwd f=new ForgetPwd(nouveauMotDePasse);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VerifCode.fxml"));
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

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);

        }
        }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private String genererMotDePasseAleatoire(int longueur) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(longueur);
        for (int i = 0; i < longueur; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
    @FXML
    void envoyerParSMS(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ReinitialiserPwdSMS.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    }
