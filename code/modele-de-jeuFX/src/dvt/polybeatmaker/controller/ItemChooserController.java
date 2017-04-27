package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.ConfigurationType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class ItemChooserController extends ControleDevint {

    @FXML private Label mainLabel;
    @FXML private VBox itemBox;
    @FXML private Button quit;

    private int buttonIndex = 0;
    private String path;
    private Consumer<JSONObject> onSelect;
    private ButtonMenu menu;

    @Override
    protected void init() {
        mapTouchToActions();
    }

    @Override
    protected void reset() {}

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.UP, (x) -> menu.moveSelection(1));
        scene.mapKeyPressedToConsumer(KeyCode.DOWN, (x) -> menu.moveSelection(-1));
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> menu.confirm());
    }

    public void load(ConfigurationType type, Consumer<JSONObject> onSelect, String mainText) {
        this.onSelect = onSelect;
        this.path = "../ressources/recordings/" + type.getFolder();
        mainLabel.setText(mainText);
        File directory = new File(path);
        if (!directory.exists()) {
             if (!directory.mkdir()) {
                 mainLabel.setText("Erreur dossier sauvegarde");
             }
        }
        File[] folderContent = directory.listFiles();
        List<Button> buttons = new ArrayList<>();
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
        this.menu = new ButtonMenu(buttons, scene.getSIVox(), Collections.singletonList((x) -> select()), 0);
    }

    private String removeJSON(String original) {
        return original.substring(0, original.length() - 5);
    }

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

    private void select() {
        choose(menu.getCurrentSelection());
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

}
