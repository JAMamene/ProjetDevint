package dvt.run;

import dvt.devint.menu.MenuDevint;
import dvt.polybeatmaker.JeuTest;
import javafx.application.Application;

/** cette classe lance les différents exemples de jeux DeViNT.
 * Vous devez obligatoirement étendre la classe menu pour écrire le menu de votre jeu
 * (ex: choix entre différents niveaux de jeu, options, aide ...)
 * 
 * @author helen
 *
 */
public class MainMenu extends MenuDevint {

	public static void main(String[] s){
		Application.launch(MainMenu.class,s);
	}
	
	@Override
	public String titre() {
		return "Jeux DeViNT";
	}
	
	@Override
	public void initMenu() {
        control.addMenuItem("Test", (x) -> new JeuTest());
	}

}
