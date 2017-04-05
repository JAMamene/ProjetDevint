package dvt.polybeatmaker.model;

import javafx.scene.control.Button;

import java.util.Timer;

/**
 * Single sound sample.
 */
public class Sound {
    Timer timer;
    public Sound()
    {
        timer = new Timer();
    }

    public boolean toBePlayed(Button b)
    {
        return true;
    }

}
