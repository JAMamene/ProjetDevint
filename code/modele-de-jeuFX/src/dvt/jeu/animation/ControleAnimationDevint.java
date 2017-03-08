package dvt.jeu.animation;

import dvt.devint.SceneDevint;

/**
 * classe abstraite à étendre pour créer le contrôleur du jeu
 * <p>
 * le controleur du jeu hérite de ControleDevint et contient les composants graphiques FXML
 *
 * @author helen
 */
public abstract class ControleAnimationDevint {

    /**
     * la scene devint où s'affiche le jeu.
     */
    protected SceneDevint scene;

    /**
     * le modèle
     */
    protected ModeleAnimationDevint model;

    /**
     * @return : la scene devint
     */
    public SceneDevint getScene() {
        return scene;
    }

    /**
     * @param : la scene devint
     */
    public void setScene(SceneDevint s) {
        scene = s;
    }

    /**
     * le modele
     */
    protected void setModel(ModeleAnimationDevint m) {
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
     * en ne faisant rien
     */
    protected abstract void reset();


    /**
     * methode appelee a chaque tour de boucle
     * mise a jour des composants graphiques
     * à définir en fonction du modèles et des composants FXML
     */
    protected abstract void render();


    /**
     * méthode appelée pour associer les actions aux touches dans la scene devint
     * est un ensemble d'instructions de la forme
     * scene.mapKeyPressedToConsumer(KeyCode.<touche>, (x) -> <fonction>);
     * où <touche> est une touche du clavier et <fonction> est un appel de fonction du contrôle
     */
    protected abstract void mapTouchToActions();
}
