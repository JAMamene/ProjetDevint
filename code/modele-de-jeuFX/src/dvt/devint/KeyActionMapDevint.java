package dvt.devint;

import static dvt.devint.ConstantesDevint.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/** classe pour lier des actions aux touches du clavier
 * par défaut 
 * 		ESCAPE sort de la fenêtre, 
 * 		F1 lit l'objectif du jeu,
 * 		F2 lit l'aide
 * 		F3 change la couleur
 * 		F4 change la police
 * on utilise les Consumer cf http://www.java2s.com/Tutorials/Java/java.util.function/Consumer/index.htm
 * et API https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html
 * 
 * @author helen
 *
 */
public class KeyActionMapDevint {
	/** la fenetre devint dont les propriétés sont changées par l'action clavier
	 * 
	 */
	private SceneDevint fenetre;

	/** la map qui associe des lambda aux touches clavier pressées
	 * ArrayList car on peut associer plusieurs actions à une touche, en particulier si on 
	 * veut ajouter une action à un des raccourcis prévus par l'API devint (voir exemple 
	 * du chronomètre)
	 * 
	 */
	private HashMap<KeyCode,ArrayList<Consumer<Void>>> keyPressedActionMap;

	/** la map qui associe des lambda aux touches clavier relâchées
	 * 
	 */
	private HashMap<KeyCode,ArrayList<Consumer<Void>>> keyReleasedActionMap;

	/** constructeur par défaut. associe les actions de base de devint 
	 * 
	 */
	public KeyActionMapDevint(SceneDevint a) {
		this.fenetre = a;
		this.keyPressedActionMap = new HashMap<KeyCode,ArrayList<Consumer<Void>>>();
		this.keyReleasedActionMap = new HashMap<KeyCode,ArrayList<Consumer<Void>>>();
		initMap();
	}
	
	/** initialise la map avec les actions de l'api devint qui sont obligatoires*/
	public void initMap() {
		// ESCAPE ferme la fenêtre
		mapKeyPressedToConsumer(KeyCode.ESCAPE,(x) -> {((Stage)fenetre.getWindow()).close(); fenetre.getSIVox().stop();});
		// F1 lit l'accueil du jeu
		mapKeyPressedToConsumer(KeyCode.F1,(x) -> fenetre.getSIVox().playWav(F1_SON));
		// F2 lit l'aide plus détaillée
		mapKeyPressedToConsumer(KeyCode.F2,(x) -> fenetre.getSIVox().playWav(F2_SON));
		// F3 change le style CSS (couleur)
		mapKeyPressedToConsumer(KeyCode.F3,(x) -> fenetre.changeColor());
		// F4 change le style CSS (fonte)
		mapKeyPressedToConsumer(KeyCode.F4,(x) -> fenetre.changeFont());
		// F5 change la souris
		mapKeyPressedToConsumer(KeyCode.F5,(x) -> fenetre.changeCursor());

	}
	
	/** invoque l'action associée quand on presse la touche de code k
	 *  si cette touche n'a pas d'action associée, cela ne fait rien
	 * 
	 * @param k : le code de la touche
	 */
	public void applyPressedAction(KeyCode k) {
		if (keyPressedActionMap.containsKey(k)) {
			ArrayList<Consumer<Void>> actions = keyPressedActionMap.get(k);
			for (Consumer<Void> c: actions){
				c.accept(null);
				//System.out.println("action " + k + c);
			}
		}
	}
	
	/** associe l'action du Consumer c à la touche k quand elle vient d'être pressée
	 * @param k : le code de la touche
	 * @param c : le consumer (i.e; lambda de la forme () -> <action>
	 */
	public void mapKeyPressedToConsumer(KeyCode k, Consumer<Void> c) {
		if (! keyPressedActionMap.containsKey(k)) {
			keyPressedActionMap.put(k,new ArrayList<Consumer<Void>>());
		}
		keyPressedActionMap.get(k).add(c);
	}
	
	/** invoque l'action associée quand on presse la touche de code k
	 *  si cette touche n'a pas d'action associée, cela ne fait rien
	 * 
	 * @param k : le code de la touche
	 */
	public void applyReleasedAction(KeyCode k) {
		if (keyReleasedActionMap.containsKey(k)) {
			for (Consumer<Void> c: keyReleasedActionMap.get(k))
				c.accept(null);
		}
	}
	
	/** associe l'action du Consumer c à la touche k quand elle vient d'être pressée
	 * @param k : le code de la touche
	 * @param c : le consumer (i.e; lambda de la forme () -> <action>
	 */
	public void mapKeyreleasedToConsumer(KeyCode k, Consumer<Void> c) {
		if (! keyReleasedActionMap.containsKey(k)) {
			keyReleasedActionMap.put(k,new ArrayList<Consumer<Void>>());
		}
		keyReleasedActionMap.get(k).add(c);
	}
	
	/**
	 * pour supprimer une action
	 */
	public void removeMappingPressed(KeyCode k,Consumer<Void> c){
		if (keyPressedActionMap.containsKey(k)) {
			ArrayList<Consumer<Void>> actions = keyPressedActionMap.get(k);
			actions.remove(c);
			if (actions.isEmpty())
				keyPressedActionMap.remove(k);
		}
	}
	
	/**
	 * pour supprimer une action
	 */
	public void removeMappingReleased(KeyCode k,Consumer<Void> c){
		if (keyReleasedActionMap.containsKey(k)) {
			ArrayList<Consumer<Void>> actions = keyReleasedActionMap.get(k);
			actions.remove(c);
			if (actions.isEmpty())
				keyReleasedActionMap.remove(k);
		}
	}
}
