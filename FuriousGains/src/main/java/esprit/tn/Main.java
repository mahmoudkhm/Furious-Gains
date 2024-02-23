package esprit.tn;

import esprit.tn.Models.Recette;
import esprit.tn.Models.Regime;
import services.RecetteService;
import services.RegimeService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        RegimeService r1s= new RegimeService();
        RecetteService r2s= new RecetteService();
        //***Product***
//ADD Regime
       // r1s.ajouter(new Regime("1","bulk","instructions.",1));

//affichage Regimes
       //System.out.println(r1s.affichage());
//Delete regime

        // r1s.supprimer(4);
//Update regime
          //Regime r1 =new Regime(5,"2","cut","instruction",1);
          // ps.modifier(p);
//Affichage GetByIdCategorie (jointure)
        //System.out.println(ps.getInnerJoinCategorie(1));




        //***Recette***
//ADD Recette
        //r2s.ajouter(new Recette(01,"NomRecette","xxx","15mins"));

//affichage Recette
        //System.out.println(r2s.affichage());
//Delete Recette

        //r2s.supprimer(2);
//Update Recette
        //Recette r2 =new Recette((int) 01,"NomRecette","myprotein","creatine");
        // cs.modifier(c);



    }
}