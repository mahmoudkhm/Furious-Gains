package esprit.tn.Controllers;
import esprit.tn.Utils.MyConnexion;

import esprit.tn.Models.Evenement;
import esprit.tn.Services.EvenementService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import com.mysql.cj.xdevapi.Session;
import java.util.Properties;


import java.io.IOException;
connction
public class AjouterEvenement {
    @FXML
    private TextField Date_eventTF;

    @FXML
    private TextField DescriptionTF;

    @FXML
    private TextField Heure_eventTF;

    @FXML
    private TextField Lieu_eventTF;

    @FXML
    private TextField Nb_participantsTF;

    @FXML
    private TextField Prix_EventTF;

    @FXML
    private TextField nom_eventTF;
    private final EvenementService es = new EvenementService();

    @FXML
    void AjouterTF(ActionEvent event) {
        String nom = nom_eventTF.getText();
        String lieu = Lieu_eventTF.getText();
        String description = DescriptionTF.getText();

        if (nom.isEmpty() || lieu.isEmpty() || description.isEmpty()) {
            afficherAlerte("Veuillez remplir tous les champs !");
            return;
        }

        if (!nom.matches("[a-zA-Z]+")) {
            afficherAlerte("Le nom doit contenir uniquement des lettres !");
            return;
        }
        if (!lieu.matches("[a-zA-Z]+")) {
            afficherAlerte("Le lieu doit contenir uniquement des lettres !");
            return;
        }
        if (!description.matches("[a-zA-Z]+")) {
            afficherAlerte("la description  doit contenir uniquement des lettres !");
            return;
        }



              Evenement e =new Evenement(nom, lieu, Float.parseFloat(Prix_EventTF.getText()), Integer.parseInt(Nb_participantsTF.getText()), Date_eventTF.getText(), Heure_eventTF.getText(), description);
            es.ajouter(e);
            sendNotificationEmailToAllUsers(e);
           // afficherAlerte("Événement ajouté avec succès !");



    }

    @FXML
    void afficherevenement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEvenement.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
       // alert.setTitle("ajout valide");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void sendNotificationEmailToAllUsers(Evenement event) {
        try {

            List<String> userEmails = getUserEmailsFromDatabase();

            // SMTP server properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Sender's email credentials
            String username = "mellouli165@gmail.com";
            String password = "jppu jtkb nqfe muxn";
            javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            // Create session


            // Iterate through each user's email address and send email
            for (String recipient : userEmails) {
                sendEmail(session, username, recipient, "New event added: " + event.getNom_event());
            }

            System.out.println("Notification emails sent successfully.");

        } catch (SQLException | MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private void sendEmail(javax.mail.Session session, String from, String to, String content) throws MessagingException {
       Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("New Event Notification");
        message.setText(content);
        Transport.send(message);
    }
    public List<String> getUserEmailsFromDatabase() throws SQLException {
        List<String> userEmails = new ArrayList<>();

        // Establish database connection

        try {

            Statement st = cnx.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT email FROM user");

            // Fetch user emails from the result set
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                userEmails.add(email);

            }
            st.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userEmails;
    }
}
