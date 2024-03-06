package esprit.tn;

import esprit.tn.Models.CodePromo;
import esprit.tn.Models.Commande;
import esprit.tn.Models.User;
import esprit.tn.Services.CodeService;
import esprit.tn.Services.CommandeService;
import esprit.tn.Services.UserService;
import esprit.tn.Utils.MyConnexion;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
         /*MyConnexion db= MyConnexion.getInstance();
        MyConnexion db2= MyConnexion.getInstance();


        System.out.println(db);
        System.out.println(db2);*/
        UserService users =new UserService();
        CodeService codes=new CodeService();
        //users.ajouter2(new User(1919193,"sousou ","nourr",50141688,"kalaatelandalous","n.esprit.tn","nadanada111",1));
        System.out.println(users.afficher());

     // users.modifier(new User(1515155,"rabrouba","rabrouba",50141688,"kalaatelandalous","n.esprit.tn","nadanada111","admin",1));
        //users.delete(2);
        //users.utilisateurCanBeAded(new User(151515,"rabrouba ","rabrouba",50141688,"kalaatelandalous","n.esprit.tn","nadanada111"));
        //System.out.println(users.getOneByCin(151515));
       // codes.ajouter(new CodePromo(125,10, "njhuef",12));
       //codes.modifier(new CodePromo(555,20, "active",15));


      /* System.out.println(codes.afficher());
        codes.modifier2(new CodePromo(1752,555,20, "active",15));
        System.out.println(codes.afficher());*/
       // System.out.println(users.afficher());
        //users.delete2(151515);
        //codes.delete2(5);




    }
}