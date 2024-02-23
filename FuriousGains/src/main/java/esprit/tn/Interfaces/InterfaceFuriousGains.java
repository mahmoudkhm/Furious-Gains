package esprit.tn.Interfaces;

import esprit.tn.Models.Regime;

import java.util.List;

public interface InterfaceFuriousGains<Regime> {
    //add
   public void ajouter(Regime r1);

   public void modifier(Regime r1);
   public void supprimer(int id);
   public List<Regime>  affichage();
}
