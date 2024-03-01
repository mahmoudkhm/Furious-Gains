package esprit.tn;

import esprit.tn.Models.Produit;
import esprit.tn.services.CategorieService;
import esprit.tn.services.ProduitService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ProduitService ps= new ProduitService();
        CategorieService cs= new CategorieService();
        //***Product***
//ADD Poduct
        //ps.ajouter(new Produit("Bum",10,150,"CBUM",3));

//affichage Poducts
      // System.out.println(ps.affichage());
//Delete Poduct

         //ps.supprimer(2);
//Update Poduct
          Produit p =new Produit("creatine",3,100,"behi",2);
           ps.modifier(p);
//Affichage GetByIdCategorie (jointure)
        //System.out.println(ps.getInnerJoinCategorie(1));




        //***Categorie***
//ADD Categorie
       // cs.ajouter(new Categorie("wawa","mohamedali"));

//affichage Categorie
       // System.out.println(cs.affichage());
//Delete Categorie

        //cs.supprimer(2);
//Update Categorie
      //  Categorie c =new Categorie(3,"myprotein","creatine");
        // cs.modifier(c);
// deleteJoin
        //cs.supprimerJoin(2);


    }
}