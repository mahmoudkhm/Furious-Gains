package esprit.tn.Interfaces;
import esprit.tn.Models.Livraison;

import java.sql.SQLException;
import java.util.List;

public interface Ilivraison<T> {
    public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(int id);
    public List<T> affichage();
    List<Livraison> rechercher(String keyword) throws SQLException;
}
