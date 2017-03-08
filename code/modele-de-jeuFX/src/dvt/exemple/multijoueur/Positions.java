package dvt.exemple.multijoueur;

import dvt.devint.ConstantesDevint;

/**
 * Permet de gérer les positions et les déplacements des joueurs dans un boîte
 *    - boîte du joueur : point en haut à gauche, largeur, hauteur
 *	  - boîte dans laquelle le joueur peut se déplacer
 * @author Justal Kevin, helen
 */
public class Positions {

    /**
     * on se déplace de 5 pixels (à règler éventuellement)
     */
    private static int SPEED=5;

	/**
	 * boîte du personnage
	 * point en haut à gauche, largeur,hauteur
	 */
    private double positionX,positionY,width,heigth;
    /**
     * boîte dans laquelle a lieu le déplacement
     */
    private double limitLeftX,limitRightX,limitTopY,limitBotY;
        
    
    /**
     * @param positionX : coordonnee X
     * @param positionY : coordonnee Y
     * @param width : largeur 
     * @param height : hauteur
     */
    public Positions(double positionX,double positionY,double width,double height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.heigth = height;
        // par défaut, on peut bouger dans toute la fenêtre
        setBoxLimits(0,ConstantesDevint.MAX_SCREEN_WIDTH,0,ConstantesDevint.MAX_SCREEN_WIDTH);
    }
    
    /**
     * pour affecter les limites de la boîte dans laquelle le joueur bouge
     * @param left
     * @param right
     * @param top
     * @param bot
     */
    public void setBoxLimits(double left,double right,double top,double bot){
    	limitLeftX = left;
    	limitRightX = right;
    	limitBotY = bot;
    	limitTopY = top;
    }
    
    public Positions(Positions p){
    	this(p.positionX,p.positionY,p.width,p.heigth);
    	setBoxLimits(p.limitLeftX, p.limitRightX, p.limitTopY, p.limitBotY);
    }
    
    /**
     * Permet de deplacer a droite en tenant compte de la limite du personnage
     */
    public void moveRight() {
        if(this.positionX+width+SPEED<=limitRightX) {
            this.positionX = this.positionX+SPEED;
        }
    } 
    
    /**
     * Permet de deplacer a gauche en tenant compte de la limite du personnage
     */
    public void moveLeft() {
        if(this.positionX-SPEED>=limitLeftX) {
            this.positionX = this.positionX-SPEED;
        }
    }    

    /**
     * Permet de deplacer en bas en tenant compte de la limite du personnage
     */    
    public void moveBot() {
        if(this.positionY+heigth+SPEED<=limitBotY) {
            this.positionY = this.positionY+SPEED;
        }
    }     

    /**
     * Permet de deplacer en haut en tenant compte de la limite du personnage
     */    
    public void moveTop() {
        if(this.positionY-SPEED>=limitTopY) {
            this.positionY = this.positionY-SPEED;
        }
    }    
    
    /**
     * Permet de gerer la victoire d'un des joueurs
     * le joueur doit être à l'intérieur de la cible
     * @param cible : la position de la cible
     */
    public boolean inside(Positions cible) {
    	return (positionX > cible.positionX)  &&
    			(positionX + width < cible.positionX + cible.width)    	
    			&& (positionY> cible.positionY) &&
    			(positionY + heigth < cible.positionY + cible.heigth);
    }
    
    
    /**
     * Retourne le centre en X du personnage
     * utile si le personnage est une ellipse
     * @return le centre en X du personnage
     */
    public double getCenterX() {
        return positionX + width/2;
    }
    
    /**
     * Retourne le centre en Y du personnage
     * utile si le personnage est une ellipse
     * @return le centre en Y du personnage
     */    
    public double getCenterY() {
        return positionY + heigth/2;
    }
    
    /**
     * Retourne le centre en X du personnage
     * @return le centre en X du personnage
     */
    public double getPositionX() {
        return positionX;
    }
    
    /**
     * Retourne le centre en Y du personnage
     * @return le centre en Y du personnage
     */    
    public double getPositionY() {
        return positionY;
    }
    
    /** 
     * pour mettre à jour la position à partir d'une autre
     */
    public void setPosition(Positions p) {
    	positionX=p.positionX;
    	positionY=p.positionY;
    	width = p.width;
    	heigth = p.heigth;
    }
    
    @Override
	public String toString() {
    	return "X " + positionX + " Y " + positionY + " width " + width + " height " + heigth;
    }
}
