package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.Instrument;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for one instrument.
 */
public class InstrumentController {

    @FXML private VBox box1;
    @FXML private VBox box2;
    @FXML private ImageView picture;

    private MainController parent;
    private Instrument instrument;
    private List<Button> buttons;

    @FXML
    public void initialize() {
        buttons = new ArrayList<>();
        for (Node c : box1.getChildren()) {
            buttons.add((Button) c);
        }
        for (Node c : box2.getChildren()) {
            buttons.add((Button) c);
        }
        for (Button b : buttons) {
            b.setOnMouseClicked(event -> {
                parent.addSound(instrument.getSound(Integer.parseInt(b.getId())));
            });
        }
    }



}
