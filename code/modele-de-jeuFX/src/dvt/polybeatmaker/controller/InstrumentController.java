package dvt.polybeatmaker.controller;

import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import dvt.polybeatmaker.model.Sequence;
import dvt.polybeatmaker.model.Sound;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for one instrument.
 */
public class InstrumentController {

    private static String BORDER_PROPERTY = "-fx-border-width: 10;" +
            "-fx-border-color: ";
    private static String HIGHLIGHT_COLOR = MainController.DEFAULT_BORDER;

    @FXML private VBox box1;
    @FXML private VBox box2;
    @FXML private ImageView picture;

    private Instrument instrument;
    private PolybeatModel model;

    private Sound activated;
    private ToggleButton activatedButton;
    private List<ToggleButton> buttons;

    public static void setHighlightColor(String color) {
        HIGHLIGHT_COLOR = color;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setModel(PolybeatModel model) {
        this.model = model;
    }

    public void init() throws MalformedURLException {
        buttons = new ArrayList<>();
        for (Node c : box1.getChildren()) {
            initButton(c);
        }
        for (Node c : box2.getChildren()) {
            initButton(c);
        }
        picture.setImage(new Image(instrument.getPicURL()));
    }

    public void initButton(Node c) {
        ToggleButton t = (ToggleButton) c;
        buttons.add(t);
        t.setStyle(instrument.getStyle());
        t.setOnMouseClicked(event -> toggleSound(t));
    }

    public void toggleSound(ToggleButton b) {
        swapBorder(b);
        try {
            if (activated != null) {
                model.removeSound(activated);
            }
            if (activatedButton == b) {
                activatedButton = null;
            } else {
                if (activatedButton != null) {
                    activatedButton.setStyle(instrument.getStyle());
                    activatedButton.setText("");
                    activatedButton.setSelected(false);
                }
                activated = instrument.getSound(Integer.parseInt(b.getId().substring(1)));
                model.addSound(activated);
                activatedButton = b;
            }
        } catch (MediaException e) {
            //temporary since we don't have all the sounds yet
            System.out.println("Couldn't load sound");
        }
    }

    private void swapBorder(ToggleButton b) {
        if (!b.isSelected()) {
            b.setStyle(instrument.getStyle());
            b.setText("");
        } else {
            b.setStyle(instrument.getStyle() + BORDER_PROPERTY + HIGHLIGHT_COLOR);
            b.setText("O");
        }
    }

    public void updateHighlight() {
        if (activatedButton != null) {
            activatedButton.setStyle(instrument.getStyle() + BORDER_PROPERTY + HIGHLIGHT_COLOR);
        }
    }

    public int getActive() {
        if (activatedButton == null) {
            return Sequence.INACTIVE;
        } else {
            return Integer.parseInt(activatedButton.getId().substring(1));
        }
    }

    public void loadActive(int id) {
        if (activatedButton != null) {
            activatedButton.setSelected(false);
            toggleSound(activatedButton);
        }
        if (id != Sequence.INACTIVE) {
            buttons.get(id-1).setSelected(true);
            toggleSound(buttons.get(id -1));
        }
    }

}
