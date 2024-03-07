package esprit.tn.Models;

public class Produit {
    private int id_produit ;
    private String marque_produit;

    private int quantite;
    private float prix_produit;
    private String description;
    private int id_categorie ;
    private Categorie categorie;
   private String image_name;

    public Produit(int id_produit, String marque_produit, int quantite, float prix_produit, String description, int id_categorie,String image_name) {
        this.id_produit = id_produit;
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.id_categorie = id_categorie;

        this.image_name = image_name;
    }

    public Produit(String marque_produit, int quantite, float prix_produit, String description, int id_categorie,String image_name) {
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.id_categorie = id_categorie;

        this.image_name = image_name;
    }

    public Produit() {
    }

    public Produit(int id_produit, String marque_produit, int quantite, float prix_produit) {
        this.id_produit = id_produit;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Produit(int id_produit, String marque_produit, int quantite, float prix_produit, String description) {
        this.id_produit = id_produit;
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
    }

    public Produit(String marque_produit, int quantite, float prix_produit, String image_name) {
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.image_name = image_name;
    }

    public Produit(String marque_produit, int quantite, float prix_produit, String description, String image_name) {
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.image_name = image_name;
    }

    public Produit(String marque_produit, int quantite, float prix_produit, String description, int id_categorie) {
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.id_categorie = id_categorie;
    }

    public Produit(int id_produit, String marque_produit, int quantite, float prix_produit, String description, int id_categorie) {
        this.id_produit = id_produit;
        this.marque_produit = marque_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.description = description;
        this.id_categorie = id_categorie;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Produit(int id_produit, String marque_produit, float prix_produit, String image_name) {
        this.id_produit = id_produit;
        this.marque_produit = marque_produit;
        this.prix_produit = prix_produit;
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return
               // "id_produit=" + id_produit +
                "MARQUE  :" + marque_produit +
              //  ", quantite=" + quantite +
                "   PRIX  :" + prix_produit +
                "    DESCRIPTION    :" + description  ;
               // ", id_categorie=" + id_categorie ;
              //  ", image_produit='" + image_produit + '\'' ;
    }
}
