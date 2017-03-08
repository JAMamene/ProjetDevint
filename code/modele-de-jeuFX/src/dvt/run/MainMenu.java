package dvt.run;

import dvt.devint.menu.MenuDevint;
import dvt.exemple.chronometre.JeuChrono;
import dvt.exemple.multijoueur.JeuMulti;
import dvt.exemple.quizz.JeuQuizz;
import javafx.application.Application;

/** cette classe lance les différents exemples de jeux DeViNT.
 * Vous devez obligatoirement étendre la classe menu pour écrire le menu de votre jeu
 * (ex: choix entre différents niveaux de jeu, options, aide ...)
 * 
 * @author helen
 *
 */
public class MainMenu extends MenuDevint {

	@Override
	public String titre() {
		return "Jeux DeViNT";
	}
	
	@Override
	public void initMenu() {
		control.addMenuItem("chrono", (x) -> new JeuChrono());
		control.addMenuItem("multi", (x) -> new JeuMulti());
		control.addMenuItem("quizz", (x) -> new JeuQuizz());
	}
	
	public static void main(String[] s){
		Application.launch(MainMenu.class,s);
	}

}
