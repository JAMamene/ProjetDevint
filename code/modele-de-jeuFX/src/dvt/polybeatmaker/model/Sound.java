package dvt.polybeatmaker.model;

import javafx.scene.media.AudioClip;

import java.io.File;

/**
 * Single sound sample.
 */
public class Sound {

    private AudioClip ac;

    public Sound(String file) {
        ac = new AudioClip(new File("../ressources/sons/" + file).toURI().toString());
    }

    public void play() {
        ac.play();
    }

}
