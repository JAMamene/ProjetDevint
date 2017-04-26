package dvt.polybeatmaker.model;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Meriveri on 05/04/2017.
 */
public enum Instrument {

    PIANO("piano", "#a48ef2"),
    GUITAR("guitar", "#8ee4f2"),
    ELECTRIC_GUITAR("electric_guitar", "#8ef2a1"),
    BASS("bass", "#f28e99"),
    DRUMS("drums", "#c33646");

    private String name;
    private String color;

    Instrument(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public static Instrument getInstrument(String name) {
        for (Instrument instrument : Instrument.values()) {
            if (name.equals(instrument.getName())) {
                return instrument;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPicURL() throws MalformedURLException {
        return new File("../ressources/images/" + name + ".png").toURI().toURL().toString();
    }

    public Sound getSound(int id) {
        return new Sound(name + "/" + name + id + ".mp3");
    }

    public String getStyle() {
        return "-fx-background-color:" + color + ";";
    }

}
