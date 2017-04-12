package dvt.polybeatmaker.model;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Meriveri on 05/04/2017.
 */
public enum Instrument {

    PIANO("piano"),
    GUITAR("guitar"),
    ELECTRICGUITAR("electricguitar"),
    CELLO("cello"),
    BASS("bass"),
    DRUMS("drums");

    private String name;

    Instrument(String name) {
        this.name = name;
    }

    public String getPicURL() throws MalformedURLException {
        return new File("../ressources/images/" + name + ".svg").toURI().toURL().toString();
    }

    public Sound getSound(int id) {
        return new Sound(name + "/" + name + id + ".mp3");
    }

}
