package esprit.tn.Models;

import com.google.cloud.translate.Translate;
        import com.google.cloud.translate.TranslateOptions;
        import com.google.cloud.translate.Translation;

public class Traducteur {

    private Translate translate;

    public Traducteur(String apiKey) {
        translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();
    }

    public String traduireTexte(String texte, String langueSource, String langueCible) {
        Translation translation = translate.translate(texte, Translate.TranslateOption.sourceLanguage(langueSource), Translate.TranslateOption.targetLanguage(langueCible));
        return translation.getTranslatedText();
    }
}