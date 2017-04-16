package dvt.devint;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import t2s.SIVOXDevint;

import java.util.function.Consumer;

/**
 * Scene à utiliser obligatoirement pour un projet DeViNT
 * contient les raccourcis clavier obligatoires, la synthèse vocale et la possibilité d'associer 
 * des actions à des touches
 * @author helen
 *
 */
public class SceneDevint extends Scene {
    
	/** table des actions associées aux touches clavier
	 */
	private KeyActionMapDevint keysMap;
	
	/** la synthèse vocale pour faire lire les actions
	 */
    private transient SIVOXDevint sivox;
        	
    /* constructeurs hérités de Scene. Ils initialisent en plus les propriétés Devint*/
	public SceneDevint(Parent root) {
		super(root);
		initDevintProperties();
	}
	public SceneDevint(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
		super(root, width, height, depthBuffer, antiAliasing);
		initDevintProperties();
	}
	public SceneDevint(Parent root, double width, double height) {
		super(root,width,height);
		initDevintProperties();
	}
	public SceneDevint(Parent root, double width, double height, boolean depthBuffer){
		super(root,width,height,depthBuffer);
		initDevintProperties();
	}
	public SceneDevint(Parent root, double width, double height, Paint fill){
		super(root,width,height,fill);
		initDevintProperties();
	}
	public SceneDevint(Parent root, Paint fill){
		super(root,fill);
		initDevintProperties();
	}
	
	/* initialisations propres à l'API Devint */
	private void initDevintProperties() {
		// le style initial
		this.getStylesheets().add(Preferences.currentColorStyle());
		this.getStylesheets().add(Preferences.currentFontStyle());
		
		// pour associer les actions quand on presse une touche
 		keysMap = new KeyActionMapDevint(this);
		this.addEventFilter(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
			 @Override
			public void handle(final KeyEvent keyEvent) {
				KeyCode k = keyEvent.getCode();
				System.err.println("key pressed dans scene " + k);
				keysMap.applyPressedAction(k);
			    keyEvent.consume();
			   }
			 }
			);
		// pour associer les actions quand on relache une touche		
		this.addEventFilter(KeyEvent.KEY_RELEASED,new EventHandler<KeyEvent>() {
			 @Override
			public void handle(final KeyEvent keyEvent) {
				KeyCode k = keyEvent.getCode();
				System.err.println("key released dans scene " + k);
				keysMap.applyReleasedAction(k);
			    keyEvent.consume();
			   }
			 }
			);
		// la voix
		this.sivox = new SIVOXDevint(2);
        //this.sivox.playWav(ACCUEIL_SON);
	}
	
	/** la voix devint
	 * 
	 * @return : la voix
	 */
	public SIVOXDevint getSIVox() {
		return sivox;
	}

	/** pour changer les préférences de couleurs
	 * 
	 */
	public void changeColor() {
		this.getStylesheets().remove(Preferences.currentColorStyle());
		Preferences.nextColorStyle();
		this.getStylesheets().add(Preferences.currentColorStyle());
	}

    /**
     * Permet de changer le font par defaut de la fenetre
      */
    public void changeFont() {
 		this.getStylesheets().remove(Preferences.currentFontStyle());
		Preferences.nextFontStyle();
		this.getStylesheets().add(Preferences.currentFontStyle());
    }
    
    /**
     * pour changer la souris
     */
    public void changeCursor() {
		Preferences.nextCursor();
		this.setCursor(Preferences.currentCursor());
    }

	/** associe l'action du Consumer c à la touche k quand elle vient d'être pressée
	 * @param k : le code de la touche
	 * @param c : le consumer (i.e; lambda de la forme () -> <action>
	 */
	public void mapKeyPressedToConsumer(KeyCode k, Consumer<Void> c) {
		keysMap.mapKeyPressedToConsumer(k,c);
	}
	
	/** associe l'action du Consumer c à la touche k quand elle vient d'être pressée
	 * @param k : le code de la touche
	 * @param c : le consumer (i.e; lambda de la forme () -> <action>
	 */
	public void mapKeyReleasedToConsumer(KeyCode k, Consumer<Void> c) {
		keysMap.mapKeyreleasedToConsumer(k,c);
	}
	
	/** associe l'action du Consumer c à la touche k quand elle vient d'être pressée
	 * @param k : le code de la touche
	 * @param c : le consumer (i.e; lambda de la forme () -> <action>
	 */
	public void removeMappingPressed(KeyCode k, Consumer<Void> c) {
		keysMap.removeMappingPressed(k,c);
	}
	
	/** associe l'action du Consumer c à la touche k quand elle vient d'être pressée
	 * @param k : le code de la touche
	 * @param c : le consumer (i.e; lambda de la forme () -> <action>
	 */
	public void removeMappingReleased(KeyCode k, Consumer<Void> c) {
		keysMap.removeMappingReleased(k,c);
	}
}
