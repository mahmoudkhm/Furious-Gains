package esprit.tn.Controllers;

import esprit.tn.Models.Evenement;
import esprit.tn.Services.EvenementService;
import esprit.tn.Utils.MyConnexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.time.LocalDate;


public class AjouterEvenement {
    Connection cnx= MyConnexion.getInstance().getCnx();


    @FXML
    private DatePicker mydatepicker;

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
    @FXML
    private TextField emailField;




    private final EvenementService es = new EvenementService();

    @FXML
    void AjouterTF(ActionEvent event) {
        String nom = nom_eventTF.getText();
        String lieu = Lieu_eventTF.getText();
        String description = DescriptionTF.getText();
        LocalDate localDate = (LocalDate)this.mydatepicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date_event =  Date.from(instant);

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
            afficherAlerte("La description doit contenir uniquement des lettres !");
            return;
        }

        try {


            Evenement e = new Evenement(nom, lieu, Float.parseFloat(Prix_EventTF.getText()), Integer.parseInt(Nb_participantsTF.getText()), date_event, Heure_eventTF.getText(), description);
            es.ajouter(e);
            Evenement newEvent = new Evenement(nom_eventTF.getText(),Lieu_eventTF.getText(),Float.parseFloat(Prix_EventTF.getText()),Integer.parseInt(Nb_participantsTF.getText()),date_event,Heure_eventTF.getText(),DescriptionTF.getText());
            //sendNotificationEmailToAllUsers(e);
            sendNotificationEmailToAllUsers(newEvent);
            // afficherAlerte("Événement ajouté avec succès !");
        } catch (NumberFormatException e) {
            afficherAlerte("Le prix doit être un nombre valide !");

        }
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
            String username = "wjbwastake@gmail.com";
            String password = "xxop vupk etcd ukmg";
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

    private List<String> getUserEmailsFromDatabase() throws SQLException {
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

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // alert.setTitle("ajout valide");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void mail(ActionEvent actionEvent) {
        if (emailField.getText().isEmpty()) {
            // Show a warning message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your email address.");
            alert.showAndWait();
            return; // Exit the method
        }
        SendEmail.send(emailField.getText());
    }
}
