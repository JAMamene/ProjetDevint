package dvt.devint.menu;


import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/** calsse à étendre pour définir un menu de lancement 
 * des jeux ou des options du jeu
 * @author helen
 *
 */
public abstract class MenuDevint extends Application{        
    
	/**
	 * le contrôle qui fait tout le travail
	 */
	protected MenuDevintControl control; 
	
    /** le titre du menu
     * 
     * @return le titre
     */
    public abstract String titre();
    
	/**
     * Permet d'initialiser le menu 
     * doit faire des appels à 
     * control.addmenuItems(label,action)
     */
     public abstract void initMenu();
     
	@Override
	/** re-définit la fonction start appelée au lancement de l'application
	 * cette méthode initialise la scene et les composants graphiques
	 * et lance la boucle de jeu
	 */
	public void start(Stage primaryStage) throws Exception {
		control = new MenuDevintControl();
		control.setTitre(titre());
		control.mapTouchToActions();
		initMenu();
    	control.render();
		// on ajoute ensuite le bouton quit pour qu'il soit en bas
    	control.addMenuItem("Quit",(x) -> ((Stage)control.scene.getWindow()).close());
		primaryStage.setScene(control.scene);
		primaryStage.setTitle(" ");
		primaryStage.show();
	}

}
