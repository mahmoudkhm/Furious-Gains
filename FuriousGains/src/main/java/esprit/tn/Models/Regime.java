package esprit.tn.Models;

public class Regime {
    private int id_regime;
    private String type_regime;
    private String nom_regime;
    private String instruction;

    public Regime() {
    }

    public Regime(int id_regime, String type_regime, String nom_regime, String instruction) {
        this.id_regime = id_regime;
        this.type_regime = type_regime;
        this.nom_regime = nom_regime;
        this.instruction = instruction;
    }

    public Regime(String type_regime, String nom_regime, String instruction) {
        this.type_regime = type_regime;
        this.nom_regime = nom_regime;
        this.instruction = instruction;
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



    @Override
    public String toString() {
        return "Regime{" +
                "id_regime=" + id_regime +
                ", type_regime='" + type_regime + '\'' +
                ", nom_regime='" + nom_regime + '\'' +
                ", instruction='" + instruction + '\'' +

                '}';
    }
}
