package esprit.tn.Models;

public class receipt {

    private int id_produit ;
    private String marque_produit;

    private int quantite;
    private float prix_produit;

    public receipt(int id_produit, String marque_produit, int quantite, float prix_produit) {
        this.id_produit = id_produit;
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
    }

    public receipt(String marque_produit, int quantite, float prix_produit) {
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getMarque_produit() {
        return marque_produit;
    }

    public void setMarque_produit(String marque_produit) {
        this.marque_produit = marque_produit;
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
}
