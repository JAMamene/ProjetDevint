package dvt.devint;

import java.io.File;

import javafx.stage.Screen;

/**
 * Les constantes communes pour le projet devint
 * @author Justal Kevin - SI5
 */
public final class ConstantesDevint {
	
	// constantes des chemins vers les ressources
	
	// constantes pour la taille de la fenêtre
	// à utiliser si vous souhaitez que votre jeu prenne tout l'écran
	public static final double MAX_SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	public static final double MAX_SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	
	// constantes des fichiers son
    public static final String F1_SON = "../ressources/sons/aideF1.wav"; // aide
    public static final String F2_SON = "../ressources/sons/aideF2.wav"; // aide détaillée (raccourcis)
    public static final String ACCUEIL_SON = "../ressources/sons/accueil.wav"; // accueil du jeu

    // constantes sur les fontes
    public static final String FONT_TYPE_DEFAULT = "Arial";
    public static final int TAILLE_DEFAULT = 50;

    // constantes pour la synthèse vocale
    public static final int NBR_SYNTHESE_NIVEAU = 3;
    public static final int SYNTHESE_MAXIMALE = 2;
    public static final int SYNTHESE_MOYENNE = 1;
    public static final int SYNTHESE_MINIMALE = 0;
    

    /**
     * méthode à utiliser pour accéder à un fichier du répertoire ressources
     * @param : name le chemin vers le fichierà partir de ressources
     *          Ex : images/lea.jpg 
     * @return : le chemin absolu utilisable
     */
    public static String ressourcesFileName(String name){
		File FXMLFile = new File("../ressources/"+name);
	    return "file:///" + FXMLFile.getAbsolutePath().replace("\\", "/");
    }
    
    /**
     * méthode à utiliser pour accéder à un fichier d'un package
     * @param : name le chemin vers le fichier à partir de src
     *          Ex : dvt/exemple/chronometre/chronoGUI.fxml
     * @return : le chemin absolu utilisable
     */
    public static String packageFileName(String name){
		File FXMLFile = new File("../src/"+name);
	    return "file:///" + FXMLFile.getAbsolutePath().replace("\\", "/");
    }
    
    /**
     * Constructeur prive qui ne doit jamais etre utilise.
     */
    private ConstantesDevint() {
        throw new AssertionError();
    }
}
