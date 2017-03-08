package dvt.jeu.simple;

import dvt.devint.SceneDevint;

/**
 * classe abstraite à étendre pour créer le contrôleur du jeu
 * 
 * le controleur du jeu contient les composants graphiques FXML et le modèle
 * 
 * @author helen
 *
 */
public abstract class ControleDevint {
	
    /** la scene devint où s'affiche le jeu. 
     */
    protected SceneDevint scene;
    
    /**
     * le modèle
     */
    protected ModeleDevint model;

    /**
     * 
     * @return : la scene devint
     */
    public SceneDevint getScene() {
    	return scene;
    }
    
    /**
     * 
     * @param : la scene devint
     */
    public void setScene(SceneDevint s) {
    	 scene=s;
    }
    
    /**
     * le modele
     */
    public void setModel(ModeleDevint m){
    	this.model = m;
    }
    
    /**
     * methode appelee avant la boucle de jeu
     * utilisee pour l'itialisation du modèle et de la vue
     */
    protected abstract void init();

    /**
     * methode appelee quand on active la touche pour re-commencer le jeu
     * NOTA : si votre jeu se joue à l'infini, redéfinir cette fonction
     *        en ne faisant rien
     */
    protected abstract void reset();
    
    /**
     * méthode appelée pour associer les actions aux touches dans la scene devint
     * est un ensemble d'instructions de la forme 
     *     scene.mapKeyPressedToConsumer(KeyCode.<touche>, (x) -> <fonction>);
     * où <touche> est une touche du clavier et <fonction> est un appel de fonction du contrôle
     */
    public abstract void mapTouchToActions();
}
