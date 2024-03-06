package esprit.tn.Models;

public class Annonce {
    private int id_annonce;
    private String titre_annonce;
    private String description_annonce;
    private String image;
    private int id_user;
    private  String nom;

    public Annonce(int id_annonce, String titre_annonce, String description_annonce, String image, String nom) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description_annonce = description_annonce;
        this.image = image;
        this.nom = nom;
    }

    public Annonce() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Annonce(int id_annonce, String titre_annonce, String description_annonce, String image, int id_user) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description_annonce = description_annonce;
        this.image = image;
        this.id_user = id_user;
    }

    public Annonce(String titre_annonce, String description_annonce, String image, int id_user) {
        this.titre_annonce = titre_annonce;
        this.description_annonce = description_annonce;
        this.image = image;
        this.id_user = id_user;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getTitre_annonce() {
        return titre_annonce;
    }

    public void setTitre_annonce(String titre_annonce) {
        this.titre_annonce = titre_annonce;
    }

    public String getDescription_annonce() {
        return description_annonce;
    }

    public void setDescription_annonce(String description_annonce) {
        this.description_annonce = description_annonce;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id_annonce=" + id_annonce +
                ", titre_annonce='" + titre_annonce + '\'' +
                ", description_annonce='" + description_annonce + '\'' +
                ", image='" + image + '\'' +
                ", id_user=" + id_user +
                '}';
    }
}
