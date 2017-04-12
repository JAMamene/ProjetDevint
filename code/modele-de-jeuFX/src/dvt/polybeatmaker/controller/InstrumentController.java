package dvt.polybeatmaker.controller;

import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private List<Button> buttons;
    private PolybeatModel model;

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setModel(PolybeatModel model) {
        this.model = model;
    }

    public void init() throws MalformedURLException {
        buttons = new ArrayList<>();
        for (Node c : box1.getChildren()) {
            buttons.add((Button) c);
        }
        for (Node c : box2.getChildren()) {
            buttons.add((Button) c);
        }
        for (Button b : buttons) {
            b.setOnMouseClicked(event -> {
                model.addSound(instrument.getSound(Integer.parseInt(b.getId())));
            });
        }
        picture.setImage(new Image(instrument.getPicURL()));
    }



}
