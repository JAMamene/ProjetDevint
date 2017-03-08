package dvt.exemple.multijoueur;


/**
 * Permet de rassembler l'ensemble des constanstes du jeu dans une classe
 * @author Justal Kevin, helen
 */
public final class CstJeuMulti {
 
	// différentes consignes affichées dans le jeu
    public static final String CONSIGNE_JEU = "ATTRAPE LE CARRE. "
            + "Rose : flèches."
            + "Bleu : touches Z. Q. S. et D."
            + "ESPACE pour commencer.";
    public static final String CONSIGNE_JEU_VOIX = "Attrape le carré. "
            + "joueur rose : utilise les flèches."
            + "joueur bleu : utilise les touches Z. Q. esse. et D."
            + "Appuie sur ESPACE pour commencer.";
    public static final String WIN = "A GAGNE.";
    public static final String RECOMMENCE =  "Appuie sur espace pour recommencer.";
     
    // enum pour les sens de déplacement
    public static enum SENS {haut,bas,gauche,droite};
    
    // constantes de position et de taille
    public static final double W_ELLIPSE = 90;
    public static final double H_ELLIPSE = 40;
    public static final double W_CIBLE = 250;
    public static final double H_CIBLE = 150;
    public static final double W_INFO = 1000;
    public static final double H_INFO = 500;
    public static final double MARGE = 10; // marge pour ne pas écrire sur le bord de la fenêtre
       
    
    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private CstJeuMulti() {
        throw new AssertionError();
    }
}
