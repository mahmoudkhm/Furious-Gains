package esprit.tn.Models;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
public class sms {
    public static final String ACCOUNT_SID = "ACbe8393ffb730376f711d46226b717e32";
    public static final String AUTH_TOKEN = "e1edad744712ee7b39dbf948940a9e7f";

    public static void sms(Livraison l,String messages,String num) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        messages=messages+l.getDate_livraison()+"id:"+l.getMontant_paiement();
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(num),
                new com.twilio.type.PhoneNumber("+12137722671"),
                messages).create();

        System.out.println(message.getSid());
    }
}