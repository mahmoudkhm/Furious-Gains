package esprit.tn;

import esprit.tn.Utils.MyConnexion;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MyConnexion db= MyConnexion.getInstance();
        MyConnexion db2= MyConnexion.getInstance();


        System.out.println(db);
        System.out.println(db2);

    }
}