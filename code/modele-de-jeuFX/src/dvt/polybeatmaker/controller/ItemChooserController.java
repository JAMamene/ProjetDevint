package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.ConfigurationType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Controller for the window allowing the user to choose his recording in a list.
 * Once chosen, the element will be converted to JSON and applied to a specified Consumer.
 */
public class ItemChooserController extends ControleDevint {

    @FXML private Label mainLabel;
    @FXML private VBox itemBox;
    @FXML private Button quit;
    @FXML private ScrollPane scroll;

    private int buttonIndex = 0;
    private String path;
    private Consumer<JSONObject> onSelect;
    private ButtonMenu menu;
    private List<Button> buttons;

    /**
     * To avoid using a new model for the window, mapTouchToActions is called here.
     */
    @Override
    protected void init() {
        mapTouchToActions();
    }

    @Override
    protected void reset() {
    }

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.UP, (x) -> {
            menu.moveSelection(1);
            centerButton(menu.getCurrentSelection());
        });
        scene.mapKeyPressedToConsumer(KeyCode.DOWN, (x) -> {
            menu.moveSelection(-1);
            centerButton(menu.getCurrentSelection());
        });
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> menu.confirm());
    }

    public void centerButton(Button b) {
        if (b == quit) {
            return;
        }
        double h = scroll.getContent().getBoundsInLocal().getHeight();
        double y = (b.getBoundsInParent().getMaxY() + b.getBoundsInParent().getMinY()) / 2.0;
        double v = scroll.getViewportBounds().getHeight();
        scroll.setVvalue(scroll.getVmax() * ((y - 0.5 * v) / (h - v)));
    }

    /**
     * Loads the list of elements into the window. If the save path is not found, it will be created.
     *
     * @param type     - the type of the elements to initialize
     * @param onSelect - the Consumer of the JSONObject selected by the user
     */
    public void load(ConfigurationType type, Consumer<JSONObject> onSelect) {
        this.onSelect = onSelect;
        this.path = "../ressources/recordings/" + type.getFolder();
        mainLabel.setText(type.getChoiceText());
        File directory = new File(path);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                mainLabel.setText("Erreur dossier sauvegarde");
            }
        }
        File[] folderContent = directory.listFiles();
        buttons = new ArrayList<>();
        buttons.add(quit);
        if (folderContent != null) {
            for (File file : folderContent) {
                Button button = new Button(removeJSON(file.getName()));
                button.setPrefSize(1700, 100);
                button.setOnAction((x) -> choose(button));
                itemBox.getChildren().add(button);
                buttons.add(0, button);
            }
        }
        int start = buttons.size() == 1 ? 0 : buttons.size() - 1;
        this.menu = new ButtonMenu(buttons, scene.getSIVox(), Collections.singletonList((x) -> select()), start);
    }

    /**
     * Removes the .json extension from a String.
     *
     * @param original - a filename with the json extension
     * @return the same filename, without its extension
     */
    private String removeJSON(String original) {
        return original.substring(0, original.length() - 5);
    }

    /**
     * Loads the element associated to the button in a JSON, and applies the specified Consumer.
     *
     * @param current - the button chosen by the user
     */
    private void choose(Button current) {
        if (current == quit) {
            exit();
        }
        try {
            onSelect.accept(new JSONObject(new String(Files.readAllBytes(Paths.get(
                    path + current.getText() + ".json")))));
            exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects the current button.
     */
    private void select() {
        choose(menu.getCurrentSelection());
    }

    /**
     * Closes the window.
     */
    @FXML
    private void exit() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

}
