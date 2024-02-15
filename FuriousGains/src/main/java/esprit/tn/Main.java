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
        CommandeService commandes =new CommandeService();
       // users.ajouter(new User(1919193,"sousou ","nourr",50141688,"kalaatelandalous","n.esprit.tn","nadanada111"));

       // users.modifier(new User(1,1919,"nada","bha",50141688,"kalaatelandalous","n.esprit.tn","nadanada111","admin"));
        //users.delete(2);
        //users.utilisateurCanBeAded(new User(151515,"rabrouba ","rabrouba",50141688,"kalaatelandalous","n.esprit.tn","nadanada111"));
        //System.out.println(users.getOneByCin(151515));
       // codes.ajouter(new CodePromo(125,10, "njhuef",12));
       // codes.modifier(new CodePromo(555,20, "active",15));
        //commandes.ajouter(new Commande(1,1,"en cours",2,1));
        //commandes.supprimer(1);
       // commandes.ajouter(new Commande(1,1,"weslet",2,1));

      /*  System.out.println(codes.afficher());
        codes.modifier(new CodePromo(1,555,20, "active",15));

        codes.delete(125);
        System.out.println(codes.afficher());*/
        System.out.println(users.afficher());
        //users.delete2(151515);
        codes.delete2(5);




    }
}