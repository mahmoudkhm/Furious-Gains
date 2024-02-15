package esprit.tn;

import esprit.tn.Models.Commande;
import esprit.tn.Models.Livraison;
import esprit.tn.Services.CommandeService;
import esprit.tn.Services.LivraisonService;
import esprit.tn.Utils.MyConnexion;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        /*LivraisonService livraisons = new LivraisonService();
        livraisons.ajouter(new Livraison(1, "", "", "", 3, "", 2));
*/
        CommandeService commandeService =new CommandeService();
        commandeService.ajouter(new Commande(1,"",5,1));
}}