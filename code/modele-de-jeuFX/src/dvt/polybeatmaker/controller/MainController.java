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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for the main screen.
 */
public class MainController extends ControleDevint {

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

    /**
     * Initializes the instruments controls and the menu.
     */
    @Override
    protected void init() {
        try {
            childrenControllers = new ArrayList<>();
            model.getScheduler().setController(this);
            for (Instrument instrument : Instrument.values()) {
                FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/instrument.fxml").toURI().toURL());
                mainBox.getChildren().add(loader.load());
                InstrumentController controller = loader.getController();
                childrenControllers.add(controller);
                controller.init(instrument, model);
                menu = new ButtonMenu(Arrays.asList(quit, load, save), scene.getSIVox(), Arrays.asList(
                        (x) -> quit(), (x) -> load(), (x) -> save()), 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void reset() {}

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.LEFT, (x) -> menu.moveSelection(-1));
        scene.mapKeyPressedToConsumer(KeyCode.RIGHT, (x) -> menu.moveSelection(1));
        scene.mapKeyPressedToConsumer(KeyCode.ENTER, (x) -> menu.confirm());
    }

    /**
     * Closes the window.
     */
    @FXML
    private void quit() {
        Stage stage = (Stage) load.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens up a new window to select a sequence to initialize.
     */
    @FXML
    private void load() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/itemChooser.fxml").toURI().toURL());
            SceneDevint scene = new SceneDevint(loader.load());
            ItemChooserController controller = loader.getController();
            controller.setScene(scene);
            controller.init();
            controller.load(ConfigurationType.SEQUENCE, this::loadJSON);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens up a new window to save the current sequence.
     */
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
            controller.initialize(sequence.toJSON(), ConfigurationType.SEQUENCE);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the progress bar animation for a cycle.
     *
     * @param duration - the duration of the animation
     */
    public void updateProgressBar(int duration) {
        progressBar.setProgress(0);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(progressBar.progressProperty(), 1);
        KeyFrame keyFrame = new KeyFrame(new Duration(duration), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     * Loads the JSON associated to a sequence into the window.
     * @param json - the JSOn of the sequence
     */
    public void loadJSON(JSONObject json) {
        Sequence sequence = new Sequence(json);
        for (int i = 0; i < childrenControllers.size(); i++) {
            childrenControllers.get(i).loadActive(sequence.getActivate(i));
        }
    }


}
