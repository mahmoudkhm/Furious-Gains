package esprit.tn.Models;

public class Annonce {
    private int id_annonce;
    private String titre_annonce;
    private String description_annonce;
    private int id_user;

    public Annonce() {
    }

    public Annonce(int id_annonce, String titre_annonce, String description_annonce, int id_user) {
        this.id_annonce = id_annonce;
        this.titre_annonce = titre_annonce;
        this.description_annonce = description_annonce;
        this.id_user = id_user;
    }

    public Annonce(String titre_annonce, String description_annonce, int id_user) {
        this.titre_annonce = titre_annonce;
        this.description_annonce = description_annonce;
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
                ", id_user=" + id_user +
                '}';
    }
}
