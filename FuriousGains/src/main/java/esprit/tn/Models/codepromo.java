package esprit.tn.Models;

public class codepromo {
    private int id_code_promo;
    private int code;
    private int Montant_Réduction;
    private String Statut;
    private int Utilisations_Restantes;

    public codepromo() {
    }

    public codepromo(int code, int montant_Réduction, String statut, int utilisations_Restantes) {
        this.code = code;
        Montant_Réduction = montant_Réduction;
        Statut = statut;
        Utilisations_Restantes = utilisations_Restantes;
    }

    public codepromo(int id_code_promo, int code, int montant_Réduction, String statut, int utilisations_Restantes) {
        this.id_code_promo = id_code_promo;
        this.code = code;
        Montant_Réduction = montant_Réduction;
        Statut = statut;
        Utilisations_Restantes = utilisations_Restantes;
    }

    public int getId_code_promo() {
        return id_code_promo;
    }

    public void setId_code_promo(int id_code_promo) {
        this.id_code_promo = id_code_promo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMontant_Réduction() {
        return Montant_Réduction;
    }

    public void setMontant_Réduction(int montant_Réduction) {
        Montant_Réduction = montant_Réduction;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String statut) {
        Statut = statut;
    }

    public int getUtilisations_Restantes() {
        return Utilisations_Restantes;
    }

    public void setUtilisations_Restantes(int utilisations_Restantes) {
        Utilisations_Restantes = utilisations_Restantes;
    }

    @Override
    public String toString() {
        return "codepromo{" +
                "id_code_promo=" + id_code_promo +
                ", code=" + code +
                ", Montant_Réduction=" + Montant_Réduction +
                ", Statut='" + Statut + '\'' +
                ", Utilisations_Restantes=" + Utilisations_Restantes +
                '}';
    }
}
