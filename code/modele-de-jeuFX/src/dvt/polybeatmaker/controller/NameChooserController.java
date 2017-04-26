package dvt.polybeatmaker.controller;


import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.ConfigurationType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;

import static dvt.polybeatmaker.controller.MainController.CSS_SELECTED;
import static dvt.polybeatmaker.controller.MainController.CSS_UNSELECTED;

public class NameChooserController extends ControleDevint {

    @FXML private TextField nameField;
    @FXML private Button save;
    @FXML private Button cancel;
    @FXML private Label mainLabel;

    private JSONObject onSave;
    private ConfigurationType type;
    private String description;
    private boolean saveSelected = true;

    public void load(JSONObject onSave, ConfigurationType type, String description) {
        this.onSave = onSave;
        this.type = type;
        mainLabel.setText(description);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void reset() {

    }

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> swap());
        scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> swap());
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> confirm());
    }

    public void swap() {
        saveSelected = !saveSelected;
        save.getStyleClass().clear();
        cancel.getStyleClass().clear();
        if (saveSelected) {
            save.getStyleClass().add(CSS_SELECTED);
            cancel.getStyleClass().add(CSS_UNSELECTED);
        } else {
            save.getStyleClass().add(CSS_UNSELECTED);
            cancel.getStyleClass().add(CSS_SELECTED);
        }
    }

    public void confirm() {

    }
}
