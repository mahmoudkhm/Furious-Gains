package esprit.tn.Models;

public class Regime {
    private int id_regime;
    private String type_regime;
    private String nom_regime;
    private String instruction;
    private int id_recette;

    public Regime() {
    }

    public Regime(int id_regime, String type_regime, String nom_regime, String instruction, int id_recette) {
        this.id_regime = id_regime;
        this.type_regime = type_regime;
        this.nom_regime = nom_regime;
        this.instruction = instruction;
        this.id_recette = id_recette;
    }

    public Regime(String type_regime, String nom_regime, String instruction, int id_recette) {
        this.type_regime = type_regime;
        this.nom_regime = nom_regime;
        this.instruction = instruction;
        this.id_recette = id_recette;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public String getType_regime() {
        return type_regime;
    }

    public void setType_regime(String type_regime) {
        this.type_regime = type_regime;
    }

    public String getNom_regime() {
        return nom_regime;
    }

    public void setNom_regime(String nom_regime) {
        this.nom_regime = nom_regime;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getId_recette() {
        return id_recette;
    }

    public void setId_recette(int id_recette) {
        this.id_recette = id_recette;
    }

    @Override
    public String toString() {
        return "Regime{" +
                "id_regime=" + id_regime +
                ", type_regime='" + type_regime + '\'' +
                ", nom_regime='" + nom_regime + '\'' +
                ", instruction='" + instruction + '\'' +
                ", id_recette=" + id_recette +
                '}';
    }
}
