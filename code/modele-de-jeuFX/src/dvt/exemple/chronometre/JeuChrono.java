package dvt.exemple.chronometre;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import dvt.devint.ConstantesDevint;
import dvt.devint.SceneDevint;
import dvt.jeu.animation.ControleAnimationDevint;
import dvt.jeu.animation.JeuAnimationDevint;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Permet de gerer le jeu et la fenetre qui contient le jeu
 * @author Justal Kevin, helen
 */
public class JeuChrono extends JeuAnimationDevint {
	
    @Override
    public String titre() {
    	return "Chronometre";
    }
	
	@Override
	protected ControleAnimationDevint initControlAndScene() {
		// création de la scene à partir du FXML
		SceneDevint sc = null;
		FXMLLoader loader = new FXMLLoader();
	    String FXMLfileName = ConstantesDevint.packageFileName("dvt/exemple/chronometre/chronoGUI.fxml");
	    try {
			loader.setLocation(new URL(FXMLfileName));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BorderPane root;
		try {
			root = (BorderPane)loader.load();
			sc= new SceneDevint(root,ConstantesDevint.MAX_SCREEN_WIDTH,ConstantesDevint.MAX_SCREEN_HEIGHT);
			// on récupère le controleur créé par le FXML loader
	        this.control = loader.getController();
	        // on met à jour les infos du controleur (scene et actionMap)
			this.control.setScene(sc);	        	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return control;
	}

	@Override
	protected ChronoModel initModel() {
        return new ChronoModel();
  	}


}
