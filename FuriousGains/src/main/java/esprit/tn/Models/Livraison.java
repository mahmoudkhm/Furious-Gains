package esprit.tn.Models;

import java.util.Date;

public class Livraison {
    private int id_livraison;
    private int id_commande;
    private String date_livraison;
    private String statut_livraison;
    private String adresse_livraison;
    private float montant_paiement;
    private String mode_livraison;
    private int id_client ;

    public Livraison() {}

    public Livraison(int id_livraison, int id_commande, String date_livraison, String statut_livraison, String adresse_livraison, float montant_paiement, String mode_livraison, int id_client) {
        this.id_livraison = id_livraison;
        this.id_commande = id_commande;
        this.date_livraison = date_livraison;
        this.statut_livraison = statut_livraison;
        this.adresse_livraison = adresse_livraison;
        this.montant_paiement = montant_paiement;
        this.mode_livraison = mode_livraison;
        this.id_client = id_client;
    }

    public Livraison(int id_commande, String date_livraison, String statut_livraison, String adresse_livraison,float montant_paiement, String mode_livraison, int id_client) {
        this.id_commande = id_commande;
        this.date_livraison = date_livraison;
        this.statut_livraison = statut_livraison;
        this.adresse_livraison = adresse_livraison;
        this.montant_paiement = montant_paiement;
        this.mode_livraison = mode_livraison;
        this.id_client = id_client;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getStatut_livraison() {
        return statut_livraison;
    }

    public void setStatut_livraison(String statut_livraison) {
        this.statut_livraison = statut_livraison;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public float getMontant_paiement() {
        return montant_paiement;
    }

    public void setMontant_paiement(float montant_paiement) {
        this.montant_paiement = montant_paiement;
    }

    public String getMode_livraison() {
        return mode_livraison;
    }

    public void setMode_livraison(String mode_livraison) {
        this.mode_livraison = mode_livraison;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "id_livraison=" + id_livraison +
                ", id_commande=" + id_commande +
                ", date_livraison=" + date_livraison +
                ", statut_livraison='" + statut_livraison + '\'' +
                ", adresse_livraison='" + adresse_livraison + '\'' +
                ", montant_paiement=" + montant_paiement +
                ", mode_livraison='" + mode_livraison + '\'' +
                ", id_client=" + id_client +
                '}';
    }
}
