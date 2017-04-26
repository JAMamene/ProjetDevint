package dvt.polybeatmaker.controller;

import dvt.devint.SceneDevint;
import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.ConfigurationType;
import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import dvt.polybeatmaker.model.Sequence;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static dvt.devint.ConstantesDevint.SYNTHESE_MAXIMALE;

/**
 * Controller for the main screen.
 */
public class MainController extends ControleDevint {

    public static String DEFAULT_BORDER = "#FFFFFF;";

    private static String[] BORDER_COLOR = new String[]{"#FFFFFF;", "#e6e6ff;", "#4d004d;", "#ffe6ff;", "#4d3319;", "#f2e6d9;"};

    private int currentCSS = 0;
    private PolybeatModel model;
    private List<InstrumentController> childrenControllers;
    private ButtonMenu menu;

    @FXML private ProgressBar progressBar;
    @FXML private HBox mainBox;
    @FXML private Button load;
    @FXML private Button save;
    @FXML private Button quit;

    public void setPolybeatModel(PolybeatModel model) {
        this.model = model;
    }

    @Override
    protected void init() {
        try {
            childrenControllers = new ArrayList<>();
            for (Instrument instrument : Instrument.values()) {
                FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/instrument.fxml").toURI().toURL());
                mainBox.getChildren().add(loader.load());
                InstrumentController controller = loader.getController();
                childrenControllers.add(controller);
                controller.setInstrument(instrument);
                controller.setModel(model);
                model.getScheduler().setController(this);
                controller.init();
                menu = new ButtonMenu(Arrays.asList(quit, load, save), scene.getSIVox(), Arrays.asList(
                        (x) -> quit(), (x) -> load(), (x) -> save()), 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void reset() {}

    public void updateProgressBar(int duration) {
        progressBar.setProgress(0);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(progressBar.progressProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(new Duration(duration), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.F3, (x) -> swapBackgroundColor());
        scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> menu.moveSelection(-1));
        scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> menu.moveSelection(1));
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> menu.confirm());
    }

    private void swapBackgroundColor() {
        currentCSS = Math.floorMod(currentCSS + 1, 6);
        InstrumentController.setHighlightColor(BORDER_COLOR[currentCSS]);
        for (InstrumentController controller : childrenControllers) {
            controller.updateHighlight();
        }
    }

    @FXML
    private void quit() {
        Stage stage = (Stage) load.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void load() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/itemChooser.fxml").toURI().toURL());
            SceneDevint scene = new SceneDevint(loader.load());
            ItemChooserController controller = loader.getController();
            controller.setScene(scene);
            controller.init();
            controller.load(ConfigurationType.SEQUENCE, this::loadJSON, "Choisissez une séquence");
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadJSON(JSONObject json) {
        Sequence sequence = new Sequence(json);
        for (int i = 0; i < childrenControllers.size(); i++) {
            childrenControllers.get(i).loadActive(sequence.getActivate(i));
        }
    }

    @FXML
    private void save() {
        List<Instrument> currentInstruments = new ArrayList<>();
        List<Integer> currentActive = new ArrayList<>();
        for (InstrumentController controller : childrenControllers) {
            currentInstruments.add(controller.getInstrument());
            currentActive.add(controller.getActive());
        }
        Sequence sequence = new Sequence(currentInstruments, currentActive);
        try {
            FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/nameChooser.fxml").toURI().toURL());
            SceneDevint scene = new SceneDevint(loader.load());
            NameChooserController controller = loader.getController();
            controller.setScene(scene);
            controller.init();
            controller.load(sequence.toJSON(), ConfigurationType.SEQUENCE, "Entrez un nom pour la séquence actuelle");
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
