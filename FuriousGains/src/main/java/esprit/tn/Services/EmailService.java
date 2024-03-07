package esprit.tn.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    // Configuration SMTP
    private static final String HOST = "smtp.gmail.com"; // Serveur SMTP
    //private static final String HOST = "smtp.mail.yahoo.com"; // Serveur SMTP
    private static final int PORT = 587;//587; // Port SMTP

    // private JavaMailSender mailSender;
    public void sendEmail(String destinataire, String sujet, String contenu) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        /*props.put("mail.smtp.connectiontimeout","5000");
        props.put("mail.smtp.timeout","5000");
        props.put("mail.smtp.writetimeout","5000");*/
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.socketFactory.port","465");
        //props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        //props.put("mail.default-encoding","UTF-8");
        props.put("mail.smtp.protocol","smtp");
        //   props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        String USERNAME = "ribd1920@gmail.com"; // Nom d'utilisateur SMTP
        String PASSWORDS = "otpd ybir gpwb avzp"; // Mot de passe SMTP
      /*  String USERNAME = "ribd1920@gmail.com"; // Nom d'utilisateur SMTP
        String PASSWORDS = "rima.1999"; // Mot de passe SMTP
       */
     /*   String USERNAME = "adamchemengui@yahoo.com"; // Nom d'utilisateur SMTP
        String PASSWORDS = "RimaChemengui1999"; // Mot de passe SMTP
        */
        //props.put("mail.smtp.username", USERNAME);
        //props.put("mail.smtp.password", PASSWORDS);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("Email PasswordAuthentication 1111111111!");
                return new PasswordAuthentication(USERNAME, PASSWORDS);
            }
        });

        try {
            System.out.println("Email Message debut!");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mahmoudkhm71@gmail.com"));
            message.setSubject(sujet);
            message.setText(contenu);
            System.out.println("Email USERNAME!: "+USERNAME+"  ,  destinztaire:  "+"mahmoudkhm71@gmail.com");
            Transport.send(message);
            System.out.println("Email envoyé avec succès +++++!");
        } catch (MessagingException e) {
            System.out.println("Exeption:"+e.getMessage());
            throw new RuntimeException(e);
        }
    }


    //new function
/*
    public void constructorNewUserMail(String destinataire, String password, String subject) throws Exception{


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        /*props.put("mail.smtp.connectiontimeout","5000");
        props.put("mail.smtp.timeout","5000");
        props.put("mail.smtp.writetimeout","5000");*/
   /*     props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.default-encoding","UTF-8");
        props.put("mail.smtp.protocol","smtp");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        String USERNAME = "ribd1920@gmail.com"; // Nom d'utilisateur SMTP
        String PASSWORDS = "rima.1999"; // Mot de passe SMTP
        props.put("mail.smtp.username", USERNAME);
        props.put("mail.smtp.password", PASSWORDS);


        String message="\nWelcom to ebank \nUse the folowing credentials to login:";
        SimpleMailMessage email= new SimpleMailMessage();
        email.setTo(destinataire);
        email.setSubject("EBank: "+ subject);
        email.setText(message);
        email.setFrom(USERNAME);
        System.out.println("Email EBank aaaaaaaaaaaaaaaaaa!");
        mailSender.send(email);
        //return email;
    }
    //end function
    */
}

