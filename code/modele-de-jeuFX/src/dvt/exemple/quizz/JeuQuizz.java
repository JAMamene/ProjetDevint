package dvt.exemple.quizz;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import dvt.devint.ConstantesDevint;
import dvt.devint.SceneDevint;
import dvt.jeu.simple.ControleDevint;
import dvt.jeu.simple.JeuDevint;
import dvt.jeu.simple.ModeleDevint;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * un exemple de jeu simple : un quizz de calcul
 * @author helen
 *
 */
public class JeuQuizz extends JeuDevint {
 
	@Override
	public String titre() {
		return "QUIZZ de calcul";
	}

	@Override
	protected ModeleDevint initModel() {
		return new QuizzModel();
	}

	@Override
	protected ControleDevint initControlAndScene() {
		// création de la scene et du controleur
		SceneDevint sc = null;
		FXMLLoader loader = new FXMLLoader();
	    String FXMLfileName = ConstantesDevint.packageFileName("dvt/exemple/quizz/quizzGUI.fxml");
		try {
			loader.setLocation(new URL(FXMLfileName));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			AnchorPane root = (AnchorPane)loader.load();
			sc= new SceneDevint(root,ConstantesDevint.MAX_SCREEN_WIDTH,ConstantesDevint.MAX_SCREEN_HEIGHT);
	        control = loader.getController();
	        control.setScene(sc);
	        	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		return control;
	}
}
