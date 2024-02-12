package esprit.tn.Models;

public class Produit {
    private int id_produit ;
    private String nom_produit;

    private int quantite;
    private float prix_produit;
    private String description;
    private int id_categorie ;

    public Produit(int id_produit, String nom_produit, int quantite, float prix_produit, String description, int id_categorie) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.id_categorie = id_categorie;
    }

    public Produit(String nom_produit, int quantite, float prix_produit, String description, int id_categorie) {
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.id_categorie = id_categorie;
    }

    public Produit() {}

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", quantite=" + quantite +
                ", prix_produit=" + prix_produit +
                ", description='" + description + '\'' +
                ", id_categorie=" + id_categorie +
                '}';
    }
}
