package dvt.polybeatmaker.controller;

import javafx.scene.control.Button;
import t2s.SIVOXDevint;

import java.util.List;
import java.util.function.Consumer;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * General menu form for the game.
 * Given a list of buttons, this class will handle the movement and selection between them.
 * Each time the user goes over a button, its text content is read out loud with SIVOX.
 * When confirming a button, the supplied Void consumer of that button will be applied.
 * If the provided list of consumers contains only one consumer, the same will be executed
 * regardless of the confirmed button.
 * <p>
 * With SceneDevint, the keys used in the menu must be specified in mapTouchToAction():
 * ->  scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> buttonMenu.moveSelection(1));
 *
 * @author Guillaume André
 */
public class ButtonMenu {

    private static String CSS_SELECTED = "selectedbutton";
    private static String CSS_UNSELECTED = "unselectedbutton";

    private List<Button> items;
    private SIVOXDevint voice;
    private List<Consumer<Void>> onConfirm;
    private int current;

    /**
     * Creates a new menu from a list of buttons.
     * For the navigation to be functional, the keys must be associated with moveSelection().
     *
     * @param items     - the list of buttons of the menu
     * @param voice     - the SIVOX voice of the SceneDevint
     * @param onConfirm - the list of consumers associated with the buttons,
     *                  or a singleton list of a consumer used for all buttons
     * @param selected  - the position of the button selected by default when the menu starts
     */
    public ButtonMenu(List<Button> items, SIVOXDevint voice, List<Consumer<Void>> onConfirm, int selected) {
        this.items = items;
        this.voice = voice;
        this.onConfirm = onConfirm;
        current = selected;
        for (int i = 0; i < items.size(); i++) {
            changeStyle(items.get(i), (i == selected ? CSS_SELECTED : CSS_UNSELECTED));
        }
    }

    /**
     * Changes the style CSS class of a button.
     *
     * @param b     - the button to modify
     * @param style - the new CSS class of the button
     */
    private void changeStyle(Button b, String style) {
        b.getStyleClass().clear();
        b.getStyleClass().add(style);
    }

    /**
     * Selects a new button from the list from the specified offset.
     * For example, moveSelection(1) will select the next available button in the list.
     *
     * @param offset - the offset of the new button from the current button
     */
    public void moveSelection(int offset) {
        changeStyle(items.get(current), CSS_UNSELECTED);
        current = Math.floorMod(current + offset, items.size());
        changeStyle(items.get(current), CSS_SELECTED);
        playCurrent();
    }

    /**
     * Plays the current button text with SIVOX.
     */
    private void playCurrent() {
        voice.stop();
        voice.playText(items.get(current).getText(), SYNTHESE_MAXIMALE);
    }

    /**
     * Confirms the currently selected button and calls its associated Consumer.
     */
    public void confirm() {
        if (onConfirm.size() == 1) {
            onConfirm.get(0).accept(null);
        } else {
            onConfirm.get(current).accept(null);
        }
    }

    /**
     * Returns the button currently selected.
     *
     * @return the selected button
     */
    public Button getCurrentSelection() {
        return items.get(current);
    }

}
