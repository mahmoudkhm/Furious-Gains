package esprit.tn.Services;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
public class SMS {
        public SMS() {
        }


        public static final String ACCOUNT_SID = "AC128f8bff1f1f36ae0846e555ced7ee14";
        public static final String AUTH_TOKEN = "3db224cad73299ce82ef3d31acb7e765";


        public void sendSMS(String phoneNumber,String nom,String mdp) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new PhoneNumber("+216"+phoneNumber),
                            new PhoneNumber("+19126165038"),
                            "Bonjour  \n Suite a votre demande \n votre nouveau mdp est :"+mdp)
                    .create();

            System.out.println(message.getSid());
        }



    }

