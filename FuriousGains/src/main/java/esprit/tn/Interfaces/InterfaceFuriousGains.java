package esprit.tn.Interfaces;

import java.util.List;

public interface InterfaceFuriousGains<Produit> {
    //add
   public void ajouter(Produit p);

   public void modifier(Produit p);
   public void supprimer(int id);
   public List<Produit>  affichage();
}
