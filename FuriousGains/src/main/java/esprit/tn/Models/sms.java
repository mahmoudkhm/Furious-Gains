package esprit.tn.Models;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class sms {
    public static final String ACCOUNT_SID = "ACbe8393ffb730376f711d46226b717e32";
    public static final String AUTH_TOKEN = "dc9686fad1da6e16ad171e71f6d1b8db";

    public static void sms(Livraison l, String messages, String num) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Date date = l.getDate_livraison();

        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        String localDate2 = localDate.toString();

        // Ajouter l'adresse de livraison au message
        String adresseLivraison = l.getAdresse_livraison();
        messages = messages + "\nAdresse de livraison : " + adresseLivraison + "\nDate de livraison : " + localDate2 + "\nMontant : " + l.getMontant_paiement();

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber("+12137722671"),
                messages).create();

        System.out.println(message.getSid());
    }
}
