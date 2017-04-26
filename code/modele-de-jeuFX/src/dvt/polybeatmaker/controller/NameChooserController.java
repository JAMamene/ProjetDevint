package dvt.polybeatmaker.controller;


import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.ConfigurationType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class NameChooserController extends ControleDevint {

    @FXML private TextField nameField;
    @FXML private Button save;
    @FXML private Button cancel;
    @FXML private Label mainLabel;

    private JSONObject onSave;
    private ConfigurationType type;
    private String description;
    private ButtonMenu menu;

    @Override
    protected void init() {
        menu = new ButtonMenu(Arrays.asList(cancel, save), scene.getSIVox(),
                Arrays.asList((x) -> exit(), (x) -> save()), 1);
    }

    @Override
    protected void reset() {}

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> menu.moveSelection(-1));
        scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> menu.moveSelection(1));
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> menu.confirm());
    }

    public void load(JSONObject onSave, ConfigurationType type, String description) {
        this.onSave = onSave;
        this.type = type;
        mainLabel.setText(description);
    }

    @FXML
    private void exit(){
        Stage stage =  (Stage) getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save() {
        if (nameField.getText().equals("")) {
            return;
        }
        for (char c : nameField.getText().toCharArray()) {
            if (!((c > 'a' && c < 'z') || (c > 'A' && c < 'Z') || c == '-')) {
                return;
            }
        }
        try {
            String path = "../ressources/recordings/" + type.getFolder();
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path + nameField.getText() + ".json")));
            bw.write(onSave.toString());
            bw.close();
            exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
