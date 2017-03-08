package dvt.exemple.chronometre;

import dvt.jeu.animation.ModeleAnimationDevint;

/** le modèle du jeu de chrono
 * l'utilisateur doit appuyer un maximum de fois sur 
 * la touche espace dans un laps de temps.
 * @author helen
 *
 */
public class ChronoModel extends ModeleAnimationDevint{
	
	// les éléments du modèle
    public transient Chronometer chrono;
    private int count;
    // on a besoin de pressed et released 
    // car sinon on peut tricher en laissant space appuyé
    private boolean pressed;
    private boolean released;
    // vrai si c'est la 1ère fois qu'on appuie sur espace 
    // <=> démarrage du jeu
    private boolean firstSpace; 
    // arrêt quand CstJeuChrono.DUREE se sont écoulées
    private boolean stop;

    
    public ChronoModel() {
    	super();
    	reset();
    }
    
    public void reset(){
    	count = 0;
    	pressed = false;
    	released = true;
    	stop = false;
    	firstSpace=true;
    	chrono = new Chronometer();
    }
    
    @Override
    public void update() {
    	// si on n'a pas encore appuyé sur espace
    	if (firstSpace){
        	// si on vient d'appuyer, le jeu commence
    		if (pressed && released){
    			firstSpace=false;
    			chrono.start();
    			pressed=false;
    			released = false;
    		}
    	}
    	// si on a déjà appuyé au moins une fois, on comptabilise
    	else {
    		chrono.stop();
    		int seconds = chrono.getSeconds();
    		if (seconds <= CstJeuChrono.DUREE) {
    			if (pressed && released){
    				count++;
    				System.out.println("count " + count);
    				pressed=false;
    				released = false;
    			}
    		}
    		else {
    			stop=true;
    		}
    	}
    }
    
    public boolean isStopped() {
    	return stop;
    }
    
    public int score() {
    	return count;
    }
    
    public void setPressed() {
    	pressed=true;
    }
    
    public void setReleased() {
    	released = true;
    }
    
    public String getTime() {
    	return chrono.getChrono();
    }
}
