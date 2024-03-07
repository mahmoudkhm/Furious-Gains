package esprit.tn.Models;

public class Categorie {
    private int id_categorie;
    private String nom_categorie;
    private String descriptionC;


    public Categorie() {}

    public Categorie(int id_categorie, String nom_categorie, String descriptionC) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        this.descriptionC = descriptionC;
    }

    public Categorie(String nom_categorie, String descriptionC) {
        this.nom_categorie = nom_categorie;
        this.descriptionC = descriptionC;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id_categorie=" + id_categorie +
                ", nom_categorie='" + nom_categorie + '\'' +
                ", descriptionC='" + descriptionC + '\'' +
                '}';
    }
}
