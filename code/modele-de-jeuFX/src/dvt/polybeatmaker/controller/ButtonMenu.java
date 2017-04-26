package dvt.polybeatmaker.controller;

import javafx.scene.control.Button;
import t2s.SIVOXDevint;

import java.util.List;
import java.util.function.Consumer;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * @author Guillaume André
 */
public class ButtonMenu {

    private static String CSS_SELECTED = "selectedbutton";
    private static String CSS_UNSELECTED = "unselectedbutton";

    private List<Button> items;
    private SIVOXDevint voice;
    private List<Consumer<Void>> onConfirm;
    private int current;

    public ButtonMenu(List<Button> items, SIVOXDevint voice, List<Consumer<Void>> onConfirm, int selected) {
        this.items = items;
        this.voice = voice;
        this.onConfirm = onConfirm;
        current = selected;
        for (int i = 0; i < items.size(); i++) {
            changeStyle(items.get(i), (i == selected ? CSS_SELECTED : CSS_UNSELECTED));
        }
    }

    public void moveSelection(int offset) {
        changeStyle(items.get(current), CSS_UNSELECTED);
        current = Math.floorMod(current + offset, items.size());
        changeStyle(items.get(current), CSS_SELECTED);
        playCurrent();
    }

    private void playCurrent() {
        voice.stop();
        voice.playText(items.get(current).getText(), SYNTHESE_MAXIMALE);
    }

    private void changeStyle(Button b, String style) {
        b.getStyleClass().clear();
        b.getStyleClass().add(style);
    }

    public void confirm() {
        if (onConfirm.size() == 1) {
            onConfirm.get(0).accept(null);
        } else {
            onConfirm.get(current).accept(null);
        }
    }

    public Button getCurrentSelection() {
        return items.get(current);
    }

}
