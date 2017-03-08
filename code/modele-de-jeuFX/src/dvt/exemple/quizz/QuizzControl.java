package dvt.exemple.quizz;

import static dvt.devint.ConstantesDevint.SYNTHESE_MOYENNE;
import dvt.jeu.simple.ControleDevint;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

/** contrôle pour le quizz
 * 
 * @author helen, audrey
 * 
 * TODO : avoir un tableau de Button pour les réponses
 *
 */

public class QuizzControl extends ControleDevint{
	
	// composants graphiques issus du FXML
	@FXML
	private Label question;
	
	@FXML
	private Button reponse1;

	@FXML
	private Button reponse2;

	@FXML
	private Button reponse3;
	
	@FXML
	private Label info;
	
	// le n° du bouton actif
	private int choix;

	@Override
	public void init() {
		question.setText("Combien font 4 * 4 ?");
		readText(question.getText());

		reponse1.setText("16");
		reponse2.setText("25");
		reponse3.setText("10");
		
		info.setVisible(false);
		choix = 0;
		selectButton(reponse1);
		unSelectButton(reponse2);
		unSelectButton(reponse3);
		
		// on peut activer les boutons par la touche ENTER (cf mapTouchToActions)
		// mais on peut aussi les activer par clic
		subscribeMouseEvt();
	}

	@Override
	public void reset() {
	   	// temporisation pour avoir le temps de lire la réponse
        try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		info.setVisible(false);
		QuizzModel m = (QuizzModel)model;
		m.reset();
		question.setText(m.question());
		readText(question.getText());
	}
	
	@Override
	public void mapTouchToActions() {
		// pour les déplacements
		scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> {left();});
		scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> {right();});
		// pour afficher si la réponse est valide ou non dans le label info
		scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> {valide();});
		// pour passer à la question suivante
		// on fait le reset quand on lache ENTER pour qu'on ait le temps de voir
		// si on a gagné avant de passer à la question suivante
		// (si on fait le reset à la fin de valide, cela va trop vite, on voit pas 
		// l'affichage)
		scene.mapKeyReleasedToConsumer(KeyCode.ENTER, (x) -> {reset();});
	}

	// les fonctions associées aux actions clavier
	/////////////////////////
	
	// on se déplace en faisant une permutation circulaire
    public void left() {
        if(choix == 0) {
            unSelectButton(reponse1);
            choix=2;
            selectButton(reponse3);
        } else if(choix == 1) {
            unSelectButton(reponse2);
            choix=0;
            selectButton(reponse1);
        } else { // choix = 2
            unSelectButton(reponse3);
            choix=1;
            selectButton(reponse2);
        }
        readText(((QuizzModel)model).reponse(choix));
    }
    
    public void right() {
         if(choix == 1) {
            unSelectButton(reponse2);
            choix=2;
            selectButton(reponse3);
        } else if(choix == 2) {
            unSelectButton(reponse3);
            choix=0;
            selectButton(reponse1);
        } else { // choix = 0
            unSelectButton(reponse1);
            choix=1;
            selectButton(reponse2);
        }
        readText(((QuizzModel)model).reponse(choix));       
     }
	
    /** change l'apparence du bouton sélectionné
     * 
     * @param b : le bouton
     */
    private void selectButton(Button b){
    	b.getStyleClass().clear();
    	b.getStyleClass().add("selectedbutton");
    }
    
    /** change l'apparence du bouton remet la couleur par défaut
     * 
     * @param b : le bouton
     */
    private void unSelectButton(Button b){
       	b.getStyleClass().clear();
    	b.getStyleClass().add("unselectedbutton");    	
    }
    
    private void valide() {
    	if (((QuizzModel)model).valide(choix)) {
             info.setText("BONNE REPONSE !");          
    	}
    	else {
             info.setText("MAUVAISE REPONSE !");   		
    	}
    	info.setVisible(true);
    }
    
	
	/**
	 * pour avoir les 2 modalités de fonctionnement des boutons : par clic et par enter
	 */
	private void subscribeMouseEvt() {
		// bouton 1
		// quand on presse on sélectionne le bouton et on valide => affiche "bonne"
		// ou "mauvaise" réponse
		reponse1.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				choix = 0;
				selectButton(reponse1);
				unSelectButton(reponse2);
				unSelectButton(reponse3);
				valide();
			}
		   });
		// quand on lâche on reset (efface le message de succès)
		reponse1.setOnMouseReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				reset();
			}
		   });
		// quand on passe sur le bouton, on lit la consigne à l'oral
		reponse1.setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				readText(reponse1.getText());
			}
		   });
		
		// bouton 2
		reponse2.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				choix = 1;
				unSelectButton(reponse1);
				selectButton(reponse2);
				unSelectButton(reponse3);
				valide();
			}
		   });
		reponse2.setOnMouseReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				reset();
			}
		   });
		reponse2.setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				readText(reponse2.getText());
			}
		   });
		
		// bouton 3
		reponse3.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				choix = 2;
				unSelectButton(reponse1);
				unSelectButton(reponse2);
				selectButton(reponse3);
				valide();
			}
		   });
		reponse3.setOnMouseReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				reset();
			}
		   });
		reponse3.setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				readText(reponse3.getText());
			}
		   });
	}

	// pour la synthèse vocale
	private void readText(String t){
		scene.getSIVox().stop();
	    scene.getSIVox().playText(t);
	}
	

}
