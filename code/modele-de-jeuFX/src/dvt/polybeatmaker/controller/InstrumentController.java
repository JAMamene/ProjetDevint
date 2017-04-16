package dvt.polybeatmaker.controller;

import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import dvt.polybeatmaker.model.Sound;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for one instrument.
 */
public class InstrumentController {

    @FXML private VBox box1;
    @FXML private VBox box2;
    @FXML private ImageView picture;

    private Instrument instrument;
    private List<ToggleButton> buttons;
    private PolybeatModel model;

    private Sound activated;
    private ToggleButton activatedButton;

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setModel(PolybeatModel model) {
        this.model = model;
    }

    public void init() throws MalformedURLException {
        buttons = new ArrayList<>();
        for (Node c : box1.getChildren()) {
            buttons.add((ToggleButton) c);
        }
        for (Node c : box2.getChildren()) {
            buttons.add((ToggleButton) c);
        }
        for (ToggleButton b : buttons) {
            b.setStyle(instrument.getStyle());
            b.setOnMouseClicked(event -> {
                if (!b.isSelected()) {
                    b.setStyle(instrument.getStyle());
                } else {
                    b.setStyle(instrument.getStyle() + "-fx-border-color: #ffffff;" +
                            "-fx-border-width: 10;");
                }

                if (activated != null) {
                    model.removeSound(activated);
                }
                if (activatedButton == b) {
                    model.removeSound(activated);
                    activatedButton = null;
                } else {
                    activated = instrument.getSound(Integer.parseInt(b.getId().substring(1)));
                    model.addSound(activated);
                    activatedButton = b;
                }
            });
        }
        picture.setImage(new Image(instrument.getPicURL()));
    }



}
