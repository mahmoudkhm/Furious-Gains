package esprit.tn.Models;

import java.time.LocalDate;
import java.util.Date;

public class
Evenement {
    private int id_event;
    private String nom_event;

    private String lieu_event;
    private float prix_event;
    private int nb_participation;
    private String date_event;
    private String heure_event;
    //private String image_event;
    private String description;

    public Evenement() {}

    public Evenement(int id_event, String nom_event, String lieu_event, float prix_event, int nb_participation, String date_event, String heure_event, String description) {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.lieu_event = lieu_event;
        this.prix_event = prix_event;
        this.nb_participation = nb_participation;
        this.date_event = date_event;
        this.heure_event = heure_event;

        this.description = description;
    }

    public Evenement(String nom_event, String lieu_event, float prix_event, int nb_participation, String date_event, String heure_event, String description) {
        this.nom_event = nom_event;
        this.lieu_event = lieu_event;
        this.prix_event = prix_event;
        this.nb_participation = nb_participation;
        this.date_event = date_event;
        this.heure_event = heure_event;

        this.description = description;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public float getPrix_event() {
        return prix_event;
    }

    public void setPrix_event(float prix_event) {
        this.prix_event = prix_event;
    }


    public int getNb_participation() {
        return nb_participation;
    }

    public void setNb_participation(int nb_participation) {
        this.nb_participation = nb_participation;
    }


    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }


    public String getHeure_event() {
        return heure_event;
    }

    public void setHeure_event(String heure_event) {
        this.heure_event = heure_event;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id_event=" + id_event +
                ", nom_event='" + nom_event + '\'' +
                ", lieu_event=" + lieu_event +
                ", prix_event=" + prix_event +
                ", nb_participation=" + nb_participation +
                ", date_event=" + date_event +
                ", heure_event='" + heure_event + '\'' +

                ", description='" + description + '\'' +
                '}';
    }

}
