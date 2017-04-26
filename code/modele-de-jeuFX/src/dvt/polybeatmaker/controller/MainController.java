package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the main screen.
 */
public class MainController extends ControleDevint {

    public static String BLACK = "#000000;";
    public static String WHITE = "#ffffff;";
    public boolean isBlack = true;
    private PolybeatModel model;
    private List<InstrumentController> childrenControllers;
    private AnchorPane root;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private HBox mainBox;

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void reset() {

    }


    public void updateProgressBar(boolean reset, double progress) {
        if (reset) {
            progressBar.setProgress(0);
        }
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(progressBar.progressProperty(), progress);
        KeyFrame keyFrame = new KeyFrame(new Duration(1000), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void mapTouchToActions() {
        scene.mapKeyPressedToConsumer(KeyCode.F3, (x) -> swapBackgroundColor());
    }

    private void swapBackgroundColor() {
        getScene().getRoot().setStyle("-fx-background-color:" + (isBlack ? WHITE : BLACK));
        InstrumentController.setHighlightColor(isBlack ? BLACK : WHITE);
        for (InstrumentController controller : childrenControllers) {
            controller.updateHighlight();
        }
        isBlack = !isBlack;
    }


}
