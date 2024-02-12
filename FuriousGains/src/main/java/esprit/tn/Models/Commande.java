package esprit.tn.Models;

public class Commande {
    private int id_command;
    private int id_client;
    private String statut_commande;
    private float montant_total;
    private int id_produit;

    public Commande() {}

    public Commande(int id_command, int id_client, String statut_commande, float montant_total, int id_produit) {
        this.id_command = id_command;
        this.id_client = id_client;
        this.statut_commande = statut_commande;
        this.montant_total = montant_total;
        this.id_produit = id_produit;
    }


    public Commande(int id_client, String statut_commande, float montant_total, int id_produit) {
        this.id_client = id_client;
        this.statut_commande = statut_commande;
        this.montant_total = montant_total;
        this.id_produit = id_produit;
    }

    public int getId_command() {
        return id_command;
    }

    public void setId_command(int id_command) {
        this.id_command = id_command;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getStatut_commande() {
        return statut_commande;
    }

    public void setStatut_commande(String statut_commande) {
        this.statut_commande = statut_commande;
    }

    public float getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(float montant_total) {
        this.montant_total = montant_total;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_command=" + id_command +
                ", id_client=" + id_client +
                ", statut_commande='" + statut_commande + '\'' +
                ", montant_total=" + montant_total +
                ", id_produit=" + id_produit +
                '}';
    }
}
