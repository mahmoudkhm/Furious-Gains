package esprit.tn.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;

import java.util.Properties;
public class Smail {
   /* private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String USERNAME = "nadabha8@gmail.com";
    private static final String PASSWORD = "wkqe peai lmbx qdvx";

    public void sendMail(String recipientEmail, String message) {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        recipientEmail = recipientEmail.trim();

        // Vérifiez si la variable recipientEmail est vide après la suppression des espaces
        if (recipientEmail.isEmpty()) {
            System.out.println("Adresse e-mail du destinataire invalide : " + recipientEmail);
            return;
        }

        try {
            Message msg = new MimeMessage(session);

            // Définir les champs FROM et TO
            msg.setFrom(new InternetAddress(USERNAME));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
            msg.setSubject("config");
            msg.setText(message);
            msg.setSentDate(new Date());

            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi du courrier électronique");
        }
    }
}*/

    public  void sendEmail(String recipientEmail, String subject, String messageBody) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        String username = "nadabha135@gmail.com";
        String password = "..............";

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);

            System.out.println("Message envoyé avec succès à : " + recipientEmail);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi du message : " + e.getMessage());
        }
    }



}
