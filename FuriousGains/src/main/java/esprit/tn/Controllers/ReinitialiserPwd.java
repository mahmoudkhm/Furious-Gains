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

       /* Smail s = new Smail();
        if (this.adresse.getText().equals("" )) {
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error" );
            alertType.setHeaderText("feregh." );
            alertType.show();
        } else {
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
                if (u.getEmail().equals(adresse.getText()))
                {
                String reqs = "INSERT INTO forgetpwd( code,time, email) VALUES (?, ?, ?)";
                PreparedStatement psts = cnx.prepareStatement(reqs);
                psts.setString(3, adresse.getText());
                psts.setInt(1, number);
                psts.setString(2, sTime);
                psts.executeUpdate();}
                sEmail = this.adresse.getText();
                s.sendMail(this.Subject, this.adresse.getText());
                Parent page2 = null;
                try {
                    page2 = (Parent) FXMLLoader.load(this.getClass().getResource("/Profil.fxml" ));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                this.adresse.setText("Compte N'existe Pas !!" );
            }
        }

         */
        UserService userService = new UserService();
        User utilisateur = userService.readByPhoneNumber(adresse.getText());
        if (!adresse.getText().isEmpty() && Integer.parseInt(adresse.getText())==(utilisateur.getNum_tel())) {
            String nouveauMotDePasse = genererMotDePasseAleatoire(8);
            System.out.println("le nouveaux mdp est : "+nouveauMotDePasse);
            //String hashedPassword = BCrypt.hashpw(nouveauMotDePasse, BCrypt.gensalt());
            //User utilisateur = userService.readByPhoneNumber(adresse.getText());
            //userService.setMotDePasse(utilisateur.getId_user(), hashedPassword);
            /*SMS send=new SMS();
            send.sendSMS(adresse.getText(),utilisateur.getNom(),nouveauMotDePasse);*/
            System.out.println("mdp envoyer");
            setCode(nouveauMotDePasse);
            code=nouveauMotDePasse;
            cin =utilisateur.getCin();
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
       /* if (forgetnum.getText().isEmpty()){
        }*/

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

    }
