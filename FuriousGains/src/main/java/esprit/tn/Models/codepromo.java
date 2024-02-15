package esprit.tn.Models;

public class CodePromo {
        private int id_code_promo;
        private int code;
        private int montant_Reduction;
        private String Statut;
        private int Utilisations_Restantes;

        public CodePromo() {
        }

        public CodePromo(int code, int montant_Reduction, String statut, int utilisations_Restantes) {
            this.code = code;
            montant_Reduction = montant_Reduction;
            Statut = statut;
            Utilisations_Restantes = utilisations_Restantes;
        }

        public CodePromo(int id_code_promo, int code, int montant_Reduction, String statut, int utilisations_Restantes) {
            this.id_code_promo = id_code_promo;
            this.code = code;
            montant_Reduction = montant_Reduction;
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

        public int getMontant_Reduction() {
            return montant_Reduction;
        }

        public void setmontant_Reduction(int montant_Reduction) {
            montant_Reduction = montant_Reduction;
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
            return "CodePromo{" +
                    "id_code_promo=" + id_code_promo +
                    ", code=" + code +
                    ", montant_Reduction=" + montant_Reduction +
                    ", Statut='" + Statut + '\'' +
                    ", Utilisations_Restantes=" + Utilisations_Restantes +
                    '}';
        }
    

}
