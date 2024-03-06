package esprit.tn;

import esprit.tn.Models.Evenement;
import esprit.tn.Models.Reservation;
import esprit.tn.Services.EvenementService;
import esprit.tn.Services.ReservationService;

import java.sql.SQLException;



public class Main {
    public static void main(String[] args) {
        EvenementService es = new EvenementService();
        //  es.ajouter(new Evenement("roube", "ariana", 400, 55,"29/02/2002", "4h", "kk"));
       // es.supprimer(1);
      // System.out.println( es.affichage());
       ReservationService rs = new ReservationService();
        //rs.ajouter(new Reservation(22,"ttt",2,1));
      //  rs.modifier(new Reservation(5,2222,"nadou",2,1));
       // rs.supprimer(5);

        System.out.println( rs.affichage());



    }
}
