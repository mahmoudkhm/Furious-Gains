package esprit.tn;

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
        // users.ajouter2(new User(151515,"amira ","bha",50141688,"kalaatelandalous","n.esprit.tn","nadanada111"));
        // users.modifier(new User(1,14519193,"nada","bha",50141688,"kalaatelandalous","n.esprit.tn","nadanada111","admin"));
        //users.delete(2);
        //users.utilisateurCanBeAded(new User(14519193,"amira ","bha",50141688,"kalaatelandalous","n.esprit.tn","nadanada111"));
        System.out.println(users.getOneByCin(151515));


    }
}