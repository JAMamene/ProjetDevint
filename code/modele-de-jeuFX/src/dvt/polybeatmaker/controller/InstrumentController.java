package dvt.polybeatmaker.controller;

import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.PolybeatModel;
import dvt.polybeatmaker.model.Sequence;
import dvt.polybeatmaker.model.Sound;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for a single instrument.
 */
public class InstrumentController {

    @FXML private VBox box1;
    @FXML private VBox box2;
    @FXML private ImageView picture;

    private Instrument instrument;
    private PolybeatModel model;

    private Sound activated;
    private ToggleButton activatedButton;
    private List<ToggleButton> buttons;
    private ToggleGroup group;

    public Instrument getInstrument() {
        return instrument;
    }

    /**
     * Binds the buttons to an instrument and model.
     *
     * @param instrument - the instrument of this controller
     * @param model      - the model of the game
     * @throws MalformedURLException - if failing to initialize the picture of the instrument
     */
    public void init(Instrument instrument, PolybeatModel model) throws MalformedURLException {
        this.instrument = instrument;
        this.model = model;
        this.group = new ToggleGroup();
        buttons = new ArrayList<>();
        for (Node c : box1.getChildren()) {
            initButton(c);
        }
        for (Node c : box2.getChildren()) {
            initButton(c);
        }
        picture.setImage(new Image(instrument.getPicURL()));
    }

    /**
     * Initializes one of the buttons.
     *
     * @param c - the Node corresponding to the button
     */
    public void initButton(Node c) {
        ToggleButton t = (ToggleButton) c;
        buttons.add(t);
        t.setStyle(instrument.getStyle());
        t.setOnMouseClicked(event -> toggleSound(t));
        t.setToggleGroup(group);
    }

    /**
     * Toggles the sound of a button ON and OFF and highlights the selected button.
     *
     * @param b - the toggled button
     */
    public void toggleSound(ToggleButton b) {
        b.setText(b.isSelected() ? "O" : "");
        try {
            if (activated != null) {
                model.removeSound(activated);
            }
            if (activatedButton == b) {
                activatedButton = null;
            } else {
                if (activatedButton != null) {
                    activatedButton.setText("");
                }
                activated = instrument.getSound(Integer.parseInt(b.getId().substring(1)));
                model.addSound(activated);
                activatedButton = b;
            }
        } catch (MediaException e) {
            //temporary since we don't have all the sounds yet
            System.out.println("Couldn't initialize sound");
        }
    }

    /**
     * Returns the id of the selected button, or Sequence.INACTIVE if none is active.
     *
     * @return the id of the selected button
     */
    public int getActive() {
        if (activatedButton == null) {
            return Sequence.INACTIVE;
        } else {
            return Integer.parseInt(activatedButton.getId().substring(1));
        }
    }

    /**
     * Unloads the current sound, and loads the sound of the button of the specified id.
     *
     * @param id - the id of the button tied to the new sound
     */
    public void loadActive(int id) {
        if (activatedButton != null) {
            activatedButton.setSelected(false);
            toggleSound(activatedButton);
        }
        if (id != Sequence.INACTIVE) {
            buttons.get(id - 1).setSelected(true);
            toggleSound(buttons.get(id - 1));
        }
    }

}
