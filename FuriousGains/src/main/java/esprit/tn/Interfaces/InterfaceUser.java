package esprit.tn.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceUser <T> {
    void ajouter(T t);
    void utilisateurCanBeAded(T t);
    void modifier(T t);
    void delete(int id);
    List<T> afficher();
    void delete2(int id);
    T getOneByCin(int cin);
}
