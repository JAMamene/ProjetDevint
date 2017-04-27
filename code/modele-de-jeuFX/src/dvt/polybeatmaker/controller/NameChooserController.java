package dvt.polybeatmaker.controller;


import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.ConfigurationType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameChooserController extends ControleDevint {

    @FXML private TextField nameField;
    @FXML private Button save;
    @FXML private Button cancel;
    @FXML private Label mainLabel;
    @FXML private VBox box;

    private JSONObject onSave;
    private ConfigurationType type;
    private String description;
    private ButtonMenu menu;
    private boolean errorDisplayed;

    @Override
    public void init() {
        mapTouchToActions();
        menu = new ButtonMenu(Arrays.asList(cancel, save), scene.getSIVox(),
                Arrays.asList((x) -> exit(), (x) -> save()), 1);
    }

    @Override
    protected void reset() {
    }

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> menu.moveSelection(-1));
        scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> menu.moveSelection(1));
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> menu.confirm());
        scene.mapKeyPressedToConsumer(KeyCode.BACK_SPACE, (x) -> backspace());
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save() {
        checkName();
        if (errorDisplayed) {
            return;
        }
        try {
            String path = "../ressources/recordings/" + type.getFolder();
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + nameField.getText().trim() + ".json")));
            bw.write(onSave.toString());
            bw.close();
            exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void backspace() {
        if (!nameField.getText().equals("")) {
            nameField.setText(nameField.getText().substring(0, nameField.getText().length() - 1));
            nameField.positionCaret(nameField.getLength());
        }
    }

    private void checkName() {
        Pattern valid = Pattern.compile("[a-zA-Z \\-_0-9]+");
        Matcher match = valid.matcher(nameField.getText());
        if (!match.find() || !match.group().equals(nameField.getText()) || nameField.getText().trim().equals("")) {
            displayError();
        } else {
            removeError();
        }
    }

    private void displayError() {
        if (!errorDisplayed) {
            Label error = new Label("Nom invalide");
            error.setPrefSize(1700, 130);
            box.getChildren().add(1, error);
            errorDisplayed = true;
        }
    }

    private void removeError() {
        if (errorDisplayed) {
            box.getChildren().remove(1);
            errorDisplayed = false;
        }
    }

    public void load(JSONObject onSave, ConfigurationType type, String description) {
        this.onSave = onSave;
        this.type = type;
        mainLabel.setText(description);
    }

}
