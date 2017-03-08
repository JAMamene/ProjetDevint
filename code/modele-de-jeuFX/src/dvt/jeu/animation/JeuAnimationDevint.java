package dvt.jeu.animation;


import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

/**
 * classe à étendre pour créer un jeu Devint avec une boucle de jeu. 
 * A chaque étape de la boucle, le modèle est mis à jour et la vue est rafraichie
 * @author helen
 *
 */
public abstract class JeuAnimationDevint extends Stage{
    
    /**
     * le controle du jeu
     */
    protected ControleAnimationDevint control;
    
    /**
     * le modele du jeu
     */
    protected ModeleAnimationDevint model;

        
    /** le titre du jeu
     * 
     * @return le titre
     */
    public abstract String titre();
    
    /**
     * initialisation du modèle
     * méthode appelée dans le constructeur
     */
    protected abstract ModeleAnimationDevint initModel();
    
    /**
     * La boucle du jeu qui permet de garder un FPS (frame per seconds) constant 
     * peu importe le PC
     */
    private void loop() {
    	
        // initialisation des valeurs
        control.init();

        // le timer qui permet l'animation dans un thread indépendant
        // appelle update et render à chaque instant
		new AnimationTimer()
		{
			@Override
			public void handle(long currentNanoTime)
			{
				model.update();
				control.render();
			}

		}.start();

    }
    
    /**
     * initialisation du contrôle devint et de la scene devint
     * méthode appelée dans le constructeur
	 * la scene peut être construite à partir du FXML via un FXMLLoader()
     *   
     * on peut aussi tout créer à la main si on ne veut pas passer par FXML
     * 
     * 

     */
    protected abstract ControleAnimationDevint initControlAndScene();
    
    /**
     * constructeur du jeu
     * initialise le controle et le modèle et lance la boucle de jeu
     * 
     */
	public JeuAnimationDevint(){
		// créé le modèle
		this.model = initModel();
		// créé le contrôle et la scene
		// quand on passe par FXML on récupère le contrôle et la
		// scene via le FXMLloader
		this.control = initControlAndScene();
		// ajoute le lien touches/actions
		this.control.mapTouchToActions();
		// le contrôle a accès au modèle
		this.control.setModel(model);
		// initialise le controle => affichage de la vue de départ
		this.control.init();
		this.setScene(control.getScene());
		this.setTitle(titre());
		this.show();
		// lance la boucle de jeu : update du modèle et render du controle 
		// à chaque tour.
		this.loop();
	}
}
