package dvt.devint.menu;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;
import static dvt.devint.ConstantesDevint.SYNTHESE_MINIMALE;

import java.util.HashMap;
import java.util.function.Consumer;

import dvt.devint.ConstantesDevint;
import dvt.devint.SceneDevint;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MenuDevintControl  {
	
    /** la scene devint où s'affiche le jeu. 
     */
    protected SceneDevint scene;
   
    /** le titre
     */
    private Label menuTitle;

    /**
     * les boutons du menu
     */
    private HashMap<Integer,Button> menuItems;
    
    /**
     * la boîte verticale qui contient les boutons
     */
    private GridPane menuLayout;

    /**
     * la colonne dans le menuLayout
     */
    private int colIndex;
    
    /**
     * la colonne dans le menuLayout
     */
    private int lineIndex;
 
    /**
     * l'indice du menu qui est sélectionné
     */
    private int selected;
    
	/** la map qui associe des lambda à l'indice du bouton
	 * 
	 */
	private HashMap<Integer,Consumer<Void>> actionMap;

 
    public MenuDevintControl(){
    	super();
    	selected=0;
    	menuItems = new HashMap<Integer,Button>();
    	menuTitle = new Label("jeu");
    	menuTitle.setPrefWidth(ConstantesDevint.MAX_SCREEN_WIDTH);
    	menuLayout = new GridPane();
    	menuLayout.setGridLinesVisible(true);
    	menuLayout.getStyleClass().add("grid-pane");
    	colIndex=0;
    	lineIndex=0;
    	BorderPane layout = new BorderPane();
    	layout.setTop(menuTitle);
    	layout.setCenter(menuLayout);
    	scene= new SceneDevint(layout,ConstantesDevint.MAX_SCREEN_WIDTH,ConstantesDevint.MAX_SCREEN_HEIGHT);
    	actionMap = new HashMap<Integer,Consumer<Void>>();
     }
    
    
    protected void setTitre(String t) {
    	menuTitle.setText(t);
    	menuTitle.setStyle("");
    }

	protected void mapTouchToActions() {
		scene.mapKeyPressedToConsumer(KeyCode.DOWN, (x) -> {down();});
		scene.mapKeyPressedToConsumer(KeyCode.UP, (x) -> {up();});
		scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> {action();});
	}
	
	/**
    * Permet d'ajouter un bouton
    * @param label Le texte sur le bouton
    * @param action L'action que l'on lie au bouton
    */
   public void addMenuItem(String label, Consumer<Void> action) {
	   //création du bouton
	   Button button = new Button(label.toUpperCase());
	   button.setPrefWidth(CstMenu.PREF_BUTTON_WIDTH);
	   button.setPrefHeight(CstMenu.PREF_BUTTON_HEIGHT);
	   unSelectButton(button);
	   menuLayout.add(button, colIndex, lineIndex);
	   // on lie le bouton et l'action clavier
	   actionMap.put(lineIndex, action);
	   // on lie aussi l'action au clic souris
	   button.setOnMouseClicked(new EventHandler<MouseEvent>() {
		   @Override
		   public void handle(MouseEvent event) {
			   action.accept(null);
		   }
	   });
	   menuItems.put(lineIndex, button);
	   lineIndex++;
   }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur bas
     */
    private void down() {
        selected = selected ==  (menuItems.size() - 1) ? 0
                : selected + 1;
        render();
    }

    /**
     * Permet de gerer l'action lorsque l'on appuie sur haut
     */
    private void up() {
        selected = selected == 0 ? (menuItems.size() - 1)
                : selected - 1;
        render();
     }
    

	/**
     * Permet de gerer le rendu du menu
     */
    void render() {
        for (int i = 0; i < menuItems.size(); i++) {
            if (i == selected ) {
                selectButton(menuItems.get(i));
            } else {
                unSelectButton(menuItems.get(i));
            }
        }
        scene.getSIVox().stop();
        scene.getSIVox().playText(menuItems.get(selected).getText(),SYNTHESE_MAXIMALE);
        scene.getSIVox().playText("CETTE OPTION EST "+menuItems.get(selected % menuItems.size()).getText(),
        		SYNTHESE_MINIMALE);
    }

    /**
     * Permet de gerer l'action lorsque l'on selectionne un element dans le menu
     * (touche enter)
     */
    private void action() {
		if (actionMap.containsKey(selected)) {
			actionMap.get(selected).accept(null);
		}
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
}
