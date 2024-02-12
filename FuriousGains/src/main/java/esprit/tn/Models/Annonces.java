package esprit.tn.Models;

public class Annonces {

    private int id_annonces;
    private int id_user;
    private int id_produit;
    private int note;

    public Annonces() {}

    public Annonces(int id_annonces, int id_user, int id_produit, int note) {
        this.id_annonces = id_annonces;
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.note = note;
    }

    public Annonces(int id_user, int id_produit, int note) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.note = note;
    }

    public int getId_annonces() {
        return id_annonces;
    }

    public void setId_annonces(int id_annonces) {
        this.id_annonces = id_annonces;
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
        return "Annonce{" +
                "id_annonces=" + id_annonces +
                ", id_user=" + id_user +
                ", id_produit=" + id_produit +
                ", note=" + note +
                '}';
    }
}
