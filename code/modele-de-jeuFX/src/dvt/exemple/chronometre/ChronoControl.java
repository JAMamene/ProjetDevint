package dvt.exemple.chronometre;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;
import static dvt.exemple.chronometre.CstJeuChrono.CONSIGNE_JEU;

import java.util.function.Consumer;

import dvt.exemple.chronometre.CstJeuChrono.TAILLE;
import dvt.jeu.animation.ControleAnimationDevint;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/** le contrôleur du jeu chrono
 * 
 * @author helen
 *
 */
public class ChronoControl extends ControleAnimationDevint{
	
	// la taille des polices
	private TAILLE taille;
	
	@FXML
    private Label score;
	
	@FXML
    private Label chrono;
	
	@FXML
    private Label info;
	
	@FXML
    private ImageView imageFin;
	
	/** indispensable pour FXML. est appelé lors de l'association
	du contrôleur à la vue FXML
	*/
	public ChronoControl() {
		super();
		taille=TAILLE.moyen;
	}
	
	public void spacePressed() {
		((ChronoModel)model).setPressed();
	}
	
	public void spaceReleased() {
		((ChronoModel)model).setReleased();
	}
	
    @Override
    public void init() {
    	info.setText(CONSIGNE_JEU);
        this.scene.getSIVox().stop();
        this.scene.getSIVox().playText(CONSIGNE_JEU,SYNTHESE_MAXIMALE);
        // on met en inverse video
        info.getStyleClass().remove("label");
        info.getStyleClass().add("unselectedbutton");
    }
    
    @Override
    public void reset() {
        ((ChronoModel) model).reset();
 		score.setVisible(false);
		imageFin.setVisible(false);
        this.scene.getSIVox().stop();
        this.scene.getSIVox().playText(CONSIGNE_JEU,SYNTHESE_MAXIMALE);
    }

	
	@Override
	public void render() {
		ChronoModel cm = (ChronoModel)model;
		if (cm.isStopped() && (!score.isVisible())){
			// si le score n'est pas visible, on vient juste
			// de terminer, on dit ce qu'il faut faire
			// pour recommencer et on affiche le score
			scene.getSIVox().stop();
			scene.getSIVox().playText("appuie sur enter pour recommencer");
			score.setVisible(true);
			score.setText("Score : " + ((ChronoModel)model).score());
			imageFin.setVisible(true);
		}	
		// si le chrono n'est pas stoppé, on affiche le temps
		else if (! cm.isStopped()){
			chrono.setText(((ChronoModel)model).getTime());
		}
	}

	/**
	 * cette méthode permet de changer la taille du label info en fonction
	 * de la fonte (moyen,grand ou petit)
	 */
	private void changeSize(){
		switch (taille) {
			case moyen : taille=TAILLE.grand; info.setPrefWidth(1500);info.setPrefHeight(600);break;
			case petit : taille=TAILLE.moyen; info.setPrefWidth(800);info.setPrefHeight(500);break;
			case grand : taille=TAILLE.petit; info.setPrefWidth(500);info.setPrefHeight(200);break;
		}
	}
	
	@Override
	protected void mapTouchToActions() {
		// association des actions aux touches dans la scene
        // on gere a la fois le Pressed et le Released pour qu'on ne puisse pas tricher
        // on ne compte pas si on laisse la touche espace appuyee
        // si on appuie sur espace, value est true (cf classe Action)
        scene.mapKeyPressedToConsumer(KeyCode.SPACE, (x) -> {spacePressed();System.out.println("space pressed");});
        // si on relache sur espace, value est false (cf classe Action)
        scene.mapKeyReleasedToConsumer(KeyCode.SPACE, (x) -> {spaceReleased();System.out.println("space released");});
        // si on appuie sur enter, on relance le jeu 
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) ->  {reset();System.out.println("restart");;});		
   	    
        // illustre l'utilisation de plusieurs actions associées à une touche : par défaut de
   	    //   l'API devint, F4 change le style de police. en ajoutant la ligne ci-dessous
        // cela applique aussi la méthode changeSize()
        scene.mapKeyPressedToConsumer(KeyCode.F4,(x) -> changeSize());
	}
}
