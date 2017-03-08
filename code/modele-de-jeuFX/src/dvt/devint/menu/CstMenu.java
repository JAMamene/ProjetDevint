package dvt.devint.menu;

import dvt.devint.ConstantesDevint;

/**
 * Permet de rassembler l'ensemble des constanstes du jeu dans une classe
 * @author Justal Kevin
 */
public final class CstMenu {
	// le nombre d'items préféré. sert à fixer la hauteur des boutons
	public static int PREF_NUMBER_OF_ITEMS = 5; 
	
	// la marge
	public static int MARGE = 5;
	
	// hauteur des boutons
	public static double PREF_BUTTON_HEIGHT=ConstantesDevint.MAX_SCREEN_HEIGHT/PREF_NUMBER_OF_ITEMS;

	// largeur des boutons
	public static double PREF_BUTTON_WIDTH=ConstantesDevint.MAX_SCREEN_WIDTH-MARGE;

    /**
     * Le constructeur ne devra jamais etre utilise !
     */
    private CstMenu() {
        throw new AssertionError();
    }
}
