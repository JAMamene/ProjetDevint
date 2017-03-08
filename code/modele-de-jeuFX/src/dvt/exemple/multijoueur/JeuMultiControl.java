package dvt.exemple.multijoueur;

import static dvt.exemple.multijoueur.CstJeuMulti.CONSIGNE_JEU;
import static dvt.exemple.multijoueur.CstJeuMulti.RECOMMENCE;
import static dvt.exemple.multijoueur.CstJeuMulti.WIN;
import static dvt.exemple.multijoueur.CstJeuMulti.SENS.bas;
import static dvt.exemple.multijoueur.CstJeuMulti.SENS.droite;
import static dvt.exemple.multijoueur.CstJeuMulti.SENS.gauche;
import static dvt.exemple.multijoueur.CstJeuMulti.SENS.haut;

import dvt.devint.ConstantesDevint;
import dvt.devint.SceneDevint;
import dvt.jeu.animation.ControleAnimationDevint;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/** le controleur du jeu multi-joueur
 * 
 * @author Justal Kevin, helen
 *
 */
public class JeuMultiControl extends ControleAnimationDevint{
	
	// les composants graphiques
	private Rectangle cible;
	
	private Ellipse joueur1;

	private Ellipse joueur2;
	
	private Label info;
	
	// les éléments de contrôle
	//////////////////////////
	/**
	 * pour savoir si le jeu a commencé
	 */
	private boolean play;
	
	/**
	 * pour savoir qui a gagné
	 * 0 : personne, 1 le joueur 1, 2 le joueur2
	 */
	private int win;
	
	// le controleur qui prend la scene construite dans
	// la classe de jeu
	public JeuMultiControl(SceneDevint scene) {
		super();
		this.scene = scene;
		// la cible
		cible = new Rectangle(CstJeuMulti.W_CIBLE,CstJeuMulti.H_CIBLE);
		cible.setArcHeight(5);
		cible.setArcWidth(5);
		cible.setFill(Color.BLACK);
		cible.setStroke(Color.GRAY);
		cible.setStrokeWidth(5);
		// joueur 1
		joueur1 = new Ellipse(CstJeuMulti.W_ELLIPSE,CstJeuMulti.H_ELLIPSE);
		joueur1.setFill(Color.PALEVIOLETRED);
		joueur1.setStroke(Color.BLACK);
		// joueur 2
		joueur2 = new Ellipse(CstJeuMulti.W_ELLIPSE,CstJeuMulti.H_ELLIPSE);
		joueur2.setFill(Color.CORNFLOWERBLUE);
		joueur2.setStroke(Color.BLACK);
		// label d'information
		info = new Label("info");
		info.setPrefWidth(CstJeuMulti.W_INFO);
		info.setPrefHeight(CstJeuMulti.H_INFO);
		
		// le fond, pour pouvoir utiliser les préférences de couleur
		Rectangle fond = new Rectangle(ConstantesDevint.MAX_SCREEN_WIDTH,ConstantesDevint.MAX_SCREEN_HEIGHT);
		fond.getStyleClass().add("fond");
		
		// ajout des composants graphiques à la scene
		Group root = (Group)scene.getRoot();
		root.getChildren().add(fond);
		root.getChildren().add(cible);
		root.getChildren().add(joueur1);
		root.getChildren().add(joueur2);
		root.getChildren().add(info);
	}
	
	// actions
	/**
	 * méthode qui est associée aux touches qui permettent de faire bouger les joueurs
	 * @param sens : direction bas, haut, droite, gauche
	 */
	public void bougeJoueur1(CstJeuMulti.SENS sens,boolean bouge) {
		// pour éviter qu'on triche, on prend en compte le mouvement seulement
		// si on joue (sinon on peut commencer à appuyer avant le départ du jeu)
		if (play)
			((JeuMultiModel)model).notifieMouvementJoueur1(sens,bouge);
	}
	
	/**
	 * méthode qui est associée aux touches qui permettent de faire bouger les joueurs
	 * @param sens : direction bas, haut, droite, gauche
	 */
	public void bougeJoueur2(CstJeuMulti.SENS sens,boolean bouge) {
		if (play)
			((JeuMultiModel)model).notifieMouvementJoueur2(sens, bouge);
	}
	
	/**
	 * méthode associée à ESPACE pour commencer / recommencer le jeu
	 */
	public void launch() {
		if (!play) {
			this.play = true;
			info.setVisible(false);
		} else {
			this.win = 0;
			reset();
		}
	}

    /**
     * Le coeur du jeu - Le decisionnaire ! Pour les SI3 : Cette methode
     * effectue les changements graphiques et verifie que le joueur a gagne ou
     * pas
     */
    @Override
    public void render() {
    	JeuMultiModel m = (JeuMultiModel)this.model;
        if (win!=0){
            info.setText("LE JOUEUR " + win + " " + WIN + " " + RECOMMENCE);
            info.setVisible(true);
        } else if (play) {
            // affiche le joueur1
            joueur1.setLayoutX(m.centerX(0));
            joueur1.setLayoutY(m.centerY(0));
            // affiche le joueur2
            joueur2.setLayoutX(m.centerX(1));
            joueur2.setLayoutY(m.centerY(1));
            // affichage
            cible.toFront();
            joueur1.toFront();
            joueur2.toFront();
            // mise à jour de win
            win=m.winer();
         } else {
            info.setVisible(true);
        	info.toFront();
        } 
    }
	
    @Override
    public void init() {
    	// on lit les consignes
    	scene.getSIVox().stop();
    	scene.getSIVox().playText(CstJeuMulti.CONSIGNE_JEU_VOIX);
    	// le label d'informations
        info.setText(CstJeuMulti.CONSIGNE_JEU);
        info.setVisible(true);
        info.setCenterShape(true);
        info.setWrapText(true);
        // on place le label d'information en haut au milieu
        info.setLayoutX(ConstantesDevint.MAX_SCREEN_WIDTH/2 -CstJeuMulti.W_INFO/2);
        info.setLayoutY(CstJeuMulti.MARGE);
        // les joueurs
        // setLayout place en fonction du centre de l'ellipse
        joueur1.setLayoutX(CstJeuMulti.MARGE + CstJeuMulti.W_ELLIPSE);
        joueur1.setLayoutY(CstJeuMulti.MARGE + CstJeuMulti.H_ELLIPSE);
        joueur1.toFront();
        joueur2.setLayoutX(ConstantesDevint.MAX_SCREEN_WIDTH-CstJeuMulti.MARGE-CstJeuMulti.W_ELLIPSE);
        joueur2.setLayoutY(ConstantesDevint.MAX_SCREEN_HEIGHT-CstJeuMulti.MARGE-CstJeuMulti.H_ELLIPSE);
        joueur2.toFront();
        // la cible 
        cible.toFront();
        // setLayout positionne en fonction du point en bas à droite
        cible.setLayoutX(ConstantesDevint.MAX_SCREEN_WIDTH/2 -CstJeuMulti.W_CIBLE/2);
        cible.setLayoutY(ConstantesDevint.MAX_SCREEN_HEIGHT/2);
      
        // initialise le modèle en fonction des positions
        // des composants FXML
		((JeuMultiModel)model).initPositions(joueur1,joueur2, cible);
   }
    
    @Override
    public void reset() {
    	win=0;
    	play=true;
        ((JeuMultiModel)this.model).resetPositionsJoueurs();
        info.setVisible(false);
    }

	@Override
	protected void mapTouchToActions() {
		// NOTA : il faut penser à associer l'action qui annule le mouvement 
		//        quand on relâche la touche car sinon on appuie une fois et le joueur 
		//        avance tout le temps
        // Player 1
		scene.mapKeyPressedToConsumer(KeyCode.DOWN, (x) -> {bougeJoueur1(bas,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.DOWN, (x) -> {bougeJoueur1(bas,false);});
		scene.mapKeyPressedToConsumer(KeyCode.UP, (x) -> {bougeJoueur1(haut,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.UP, (x) -> {bougeJoueur1(haut,false);});
		scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> {bougeJoueur1(gauche,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.LEFT, (x) -> {bougeJoueur1(gauche,false);});
		scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> {bougeJoueur1(droite,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.RIGHT, (x) -> {bougeJoueur1(droite,false);});
		 
        // Player2
		scene.mapKeyPressedToConsumer(KeyCode.S, (x) -> {bougeJoueur2(bas,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.S, (x) -> {bougeJoueur2(bas,false);});
		scene.mapKeyPressedToConsumer(KeyCode.Z, (x) -> {bougeJoueur2(haut,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.Z, (x) -> {bougeJoueur2(haut,false);});
		scene.mapKeyPressedToConsumer(KeyCode.D, (x) -> {bougeJoueur2(droite,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.D, (x) -> {bougeJoueur2(droite,false);});
		scene.mapKeyPressedToConsumer(KeyCode.Q, (x) -> {bougeJoueur2(gauche,true);});
		scene.mapKeyReleasedToConsumer(KeyCode.Q, (x) -> {bougeJoueur2(gauche,false);});
		
		// espace permet de recommencer
		scene.mapKeyPressedToConsumer(KeyCode.SPACE, (x) -> {launch();});
	}
	
}
