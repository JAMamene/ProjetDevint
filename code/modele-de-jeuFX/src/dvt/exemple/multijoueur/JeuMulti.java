package dvt.exemple.multijoueur;

import dvt.devint.ConstantesDevint;
import dvt.devint.SceneDevint;
import dvt.jeu.animation.ControleAnimationDevint;
import dvt.jeu.animation.JeuAnimationDevint;
import dvt.jeu.animation.ModeleAnimationDevint;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;


/**
 * une exemple de jeu d'animation.
 * comme il s'agit d'une animation, il n'y a pas de layout mais on travaille dans le canvas
 * au démarrage, les composants sont placés "à la main", on n'utilise pas FXML
 * 
 * @author helen
 *
 */
public class JeuMulti extends JeuAnimationDevint{
	
	@Override
	public String titre() {
		return "multi joueur";
	}    
	
	@Override
	protected ControleAnimationDevint initControlAndScene() {
		Canvas c = new Canvas(ConstantesDevint.MAX_SCREEN_WIDTH,ConstantesDevint.MAX_SCREEN_HEIGHT);
		Group root = new Group(c);
		SceneDevint sc = new SceneDevint(root);
		control = new JeuMultiControl(sc);
		return control;
	}

	@Override
	protected ModeleAnimationDevint initModel() {
		return new JeuMultiModel();
	}

}
