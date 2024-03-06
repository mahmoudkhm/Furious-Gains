package esprit.tn.Interfaces;

import java.util.List;

public interface InterfaceFuriousGains<T> {
    //add
   void ajouter(T t);
   void modifier(T t);
   void supprimer(int id);
   List<T> affichage();
}
