package dvt.exemple.multijoueur;

import dvt.exemple.multijoueur.CstJeuMulti.SENS;
import dvt.jeu.animation.ModeleAnimationDevint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * Permet de gerer le jeu 
 * on a 2 joueurs, on pourrait utiliser un tableau de joueurs ...
 * @author Justal Kevin, helen
 */
public class JeuMultiModel extends ModeleAnimationDevint {
	/**
	 * les mouvements à effectuer par les joueurs
	 * cela est notifié par le contrôle selon l'action sur une touche
	 * 
	 * mouvementJoueur1[i]=true signifie qu'il faut effectuer un déplacement dans le 
	 *                       sens i où i est dans {haut,bas,gauche,droite}
	 */
	private boolean[] mouvementJoueur1;
	private boolean[] mouvementJoueur2;

    /** les positions des joueurs
     */
    private Positions joueur1,joueur2;
    
	/** les positions initiales des joueurs 
	 */
	private Positions positionInitialeJoueur1,positionInitialeJoueur2;
	
	/**
	 * les informations sur la cible
	 */
	private Positions positionCible;

    public JeuMultiModel() {
    	mouvementJoueur1= new boolean[SENS.values().length];
    	mouvementJoueur2= new boolean[SENS.values().length];
    }
    
    public void initPositions(Ellipse joueur1,Ellipse joueur2,Rectangle cible ) {
		// les joueurs sont placés en haut à gauche et en
		// bas à droite et on ne peut pas sortir du rectangle qu'ils
		// délimitent
        // cela définit les positions initiales des joueurs
		double gauche = joueur1.getLayoutX()-joueur1.getRadiusX();
		double haut = joueur1.getLayoutY()-joueur1.getRadiusY();
		double droite = joueur2.getLayoutX() + joueur2.getRadiusX();
		double bas = joueur2.getLayoutY() + joueur2.getRadiusY();
    	positionInitialeJoueur1 = new Positions(gauche,haut,2*joueur1.getRadiusX(),2*joueur1.getRadiusY());
		positionInitialeJoueur1.setBoxLimits(gauche, droite, haut, bas);
		positionInitialeJoueur2 = new Positions(joueur2.getLayoutX() - joueur2.getRadiusX(),joueur2.getLayoutY() - joueur2.getRadiusY(),2*joueur2.getRadiusX(),2*joueur2.getRadiusY());
		positionInitialeJoueur2.setBoxLimits(gauche, droite, haut, bas);
		this.joueur1 = new Positions(positionInitialeJoueur1);
		this.joueur2 = new Positions(positionInitialeJoueur2);
		// la cible
		positionCible = new Positions(cible.getLayoutX(), cible.getLayoutY(), cible.getWidth(),cible.getHeight());
    }
    
    // pour connaitre le centre pour effectuer le mouvement du joueur qui est une ellipse
    public double centerX(int i) {
    	if (i==0)
    		return joueur1.getCenterX() ;
    	return joueur2.getCenterX();
    }
    public double centerY(int i) {
    	if (i==0)
    		return joueur1.getCenterY();
    	return joueur2.getCenterY();
    }

    /**
     * notifie un déplacement au joueur1
     * @param sens : le sens du déplacement
     * @param bouge : vrai s'il s'agit de bouger, faux s'il faut s'arrêter
     */
    public void notifieMouvementJoueur1(CstJeuMulti.SENS sens,boolean bouge){
    		mouvementJoueur1[sens.ordinal()]=bouge;
    }
    
    /**
     * notifie un déplacement au joueur2
     * @param sens : le sens du déplacement
     */
    public void notifieMouvementJoueur2(CstJeuMulti.SENS sens,boolean bouge){
    		mouvementJoueur2[sens.ordinal()]=bouge;
    }
    
    /**
     * pour faire bouger les positions
     * @param joueur : la position du joueur
     * @param notification : la notification à prendre en compte
     *        contient le nombre de fois où le joueur a activé la direction
     */
    private void bouge(Positions joueur,boolean[] notification){
    	if (notification[SENS.bas.ordinal()])
    		joueur.moveBot();
    	if (notification[SENS.haut.ordinal()])
    		joueur.moveTop();
    	if (notification[SENS.gauche.ordinal()])
    		joueur.moveLeft();
    	if (notification[SENS.droite.ordinal()])
    		joueur.moveRight();
    }
	
    
    @Override
    public void update(){
    	bouge(joueur1,mouvementJoueur1);
     	bouge(joueur2,mouvementJoueur2);
    }

    /**
     * renvoie le gagnant
     * @return : 0 si pas de gagnant, 1 si joueur1, 2 si joueur2
     * NOTA : on ne gère pas le exaequo. si les 2 arrivent dans la cible en même temps, 
     *        le joueur 2 gagne
     */
    public int winer(){
    	if (joueur1.inside(positionCible))
    		return 1;
    	if (joueur2.inside(positionCible))
    		return 2;
    	return 0;
    }
    
    
    /** pour mettre à jour les positions
     * 
     */
    public void resetPositionsJoueurs() {
    	joueur1.setPosition(positionInitialeJoueur1);
       	joueur2.setPosition(positionInitialeJoueur2);
    }


}
