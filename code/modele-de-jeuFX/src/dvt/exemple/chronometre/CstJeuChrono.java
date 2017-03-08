package dvt.exemple.chronometre;

/**
 * Permet de rassembler l'ensemble des constanstes du jeu dans une classe
 * @author Justal Kevin
 */
public final class CstJeuChrono {
	// durée du jeu en s
	public static final int DUREE = 5;
	// consigne
    public static final String CONSIGNE_JEU = "APPUYER LE PLUS DE FOIS SUR ESPACE."
            + " Vous avez " + DUREE + " secondes pour faire le meilleur score. "
            + "Le jeu commencera dès l'appuie sur espace";
    // enum pour les tailles de police
    public static enum TAILLE {petit,moyen,grand};
    
    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private CstJeuChrono() {
        throw new AssertionError();
    }
}
