package dvt.polybeatmaker.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A configuration of activated sounds, saved from the main game window.
 */
public class Sequence {

    public static int INACTIVE = -1;

    private static String INSTRUMENT_KEY = "instruments";
    private static String ACTIVATED_KEY = "active";

    private List<Instrument> instruments;
    private List<Integer> activatedSounds;

    /**
     * Creates a new sequence corresponding to the specified instruments and active sounds.
     *
     * @param instruments     - the list of instruments of the sequence
     * @param activatedSounds - the list of active sounds corresponding to the instruments
     */
    public Sequence(List<Instrument> instruments, List<Integer> activatedSounds) {
        this.instruments = instruments;
        this.activatedSounds = activatedSounds;
    }

    /**
     * Loads a new Sequence from its JSON form.
     *
     * @param json - the json of the sequence
     */
    public Sequence(JSONObject json) {
        instruments = new ArrayList<>();
        activatedSounds = new ArrayList<>();
        for (Object instrument : json.getJSONArray(INSTRUMENT_KEY)) {
            instruments.add(Instrument.getInstrument(instrument.toString()));
        }
        for (Object active : json.getJSONArray(ACTIVATED_KEY)) {
            activatedSounds.add((Integer) active);
        }
    }

    /**
     * Returns the id of the active sound corresponding to the id of the specified instrument.
     *
     * @param id - the id of the instrument
     * @return the id of the active sound for that instrument
     */
    public int getActivate(int id) {
        return activatedSounds.get(id);
    }

    /**
     * Returns the instrument at the specified position in the sequance.
     *
     * @param id - the position of the instrument
     * @return the instrument at thta position
     */
    public Instrument getInstrument(int id) {
        return instruments.get(id);
    }

    /**
     * Converts a sequence to JSON.
     *
     * @return a JSON corresponding to this sequence
     */
    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONArray arrayInstruments = new JSONArray();
        for (Instrument instrument : instruments) {
            arrayInstruments.put(instrument.getName());
        }
        result.put(INSTRUMENT_KEY, arrayInstruments);
        JSONArray activated = new JSONArray();
        for (int active : activatedSounds) {
            activated.put(active);
        }
        result.put(ACTIVATED_KEY, activated);
        return result;
    }

}
