package esprit.tn.Models;

public class Avis {
    private int id_avis;
    private int id_user;
    private String titre_avis;
    private String description_avis;

    public Avis(int id_avis, String titre_avis, String description_avis, int id_user) {
        this.id_avis = id_avis;
        this.id_user = id_user;
        this.titre_avis = titre_avis;
        this.description_avis = description_avis;
    }

    public Avis(String titre_avis, String description_avis,int id_user) {
        this.id_user = id_user;
        this.titre_avis = titre_avis;
        this.description_avis = description_avis;
    }

    public Avis() {}

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

    public String getTitre_avis() {
        return titre_avis;
    }

    public void setTitre_avis(String titre_avis) {
        this.titre_avis = titre_avis;
    }

    public String getDescription_avis() {
        return description_avis;
    }

    public void setDescription_avis(String description_avis) {
        this.description_avis = description_avis;
    }


    @Override
    public String toString() {
        return "Avis{" +
                "id_avis=" + id_avis +
                ", id_user=" + id_user +
                ", titre_avis='" + titre_avis + '\'' +
                ", description_avis='" + description_avis + '\'' +
                '}';
    }
}
