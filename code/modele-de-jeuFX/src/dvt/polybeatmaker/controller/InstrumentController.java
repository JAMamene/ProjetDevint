package dvt.polybeatmaker.controller;

import dvt.polybeatmaker.model.Instrument;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;

/**
 * Controller for one instrument.
 */
public class InstrumentController {

    @FXML
    private List<Button> buttons;

    private MainController parent;
    private Instrument instrument;

    @FXML
    public void initialize() {
        for (Button b : buttons) {
            b.setOnMouseClicked(event -> {
                parent.addSound(instrument.getSound(Integer.parseInt(b.getId())));
            });
        }
    }



}
