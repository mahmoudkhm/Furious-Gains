package esprit.tn.Models;

public class Avis {

    private int id_avis;
    private int id_user;
    private int id_produit;
    private int note;

    public Avis() {}

    public Avis(int id_avis, int id_user, int id_produit, int note) {
        this.id_avis = id_avis;
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.note = note;
    }

    public Avis(int id_user, int id_produit, int note) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.note = note;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id_avis=" + id_avis +
                ", id_user=" + id_user +
                ", id_produit=" + id_produit +
                ", note=" + note +
                '}';
    }
}
