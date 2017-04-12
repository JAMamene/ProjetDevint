package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

/**
 * Controller for the main screen.
 */
public class MainController extends ControleDevint {

    private PolybeatModel model;

    @FXML
    private HBox mainBox;

    public void setPolybeatModel(PolybeatModel model) {
        this.model = model;
    }

    public void initializeStuff() {
        try {
            for (Instrument instrument : Instrument.values()) {
                FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/instrument.fxml").toURI().toURL());
                mainBox.getChildren().add(loader.load());
                InstrumentController controller =  loader.getController();
                controller.setInstrument(instrument);
                controller.setModel(model);
                controller.init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void init() {

    }

    @Override
    protected void reset() {

    }

    @Override
    public void mapTouchToActions() {

    }



}
