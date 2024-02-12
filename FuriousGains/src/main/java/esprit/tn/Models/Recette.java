package tn.esprit.Models;

public class Recette {
    private int id_Recette;
    private String nom_Recette;
    private String ingredients;
    private String temps_preparation;

    public Recette(int id_Recette, String nom_Recette, String ingredients, String temps_preparation) {
        this.id_Recette = id_Recette;
        this.nom_Recette = nom_Recette;
        this.ingredients = ingredients;
        this.temps_preparation = temps_preparation;
    }

    public Recette(String nom_Recette, String ingredients, String temps_preparation) {
        this.nom_Recette = nom_Recette;
        this.ingredients = ingredients;
        this.temps_preparation = temps_preparation;
    }

    public Recette() {}

    public int getId_Recette() {
        return id_Recette;
    }

    public void setId_Recette(int id_Recette) {
        this.id_Recette = id_Recette;
    }

    public String getNom_Recette() {
        return nom_Recette;
    }

    public void setNom_Recette(String nom_Recette) {
        this.nom_Recette = nom_Recette;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTemps_preparation() {
        return temps_preparation;
    }

    public void setTemps_preparation(String temps_preparation) {
        this.temps_preparation = temps_preparation;
    }

    @Override
    public String toString() {
        return "Recette{" +
                "id_Recette=" + id_Recette +
                ", nom_Recette='" + nom_Recette + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", temps_preparation='" + temps_preparation + '\'' +
                '}';
    }
}
