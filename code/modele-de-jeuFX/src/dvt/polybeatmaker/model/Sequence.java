package dvt.polybeatmaker.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

    public static int INACTIVE = -1;

    private static String INSTRUMENT_KEY = "instruments";
    private static String ACTIVATED_KEY = "active";

    private List<Instrument> instruments;
    private List<Integer> activatedSounds;

    public Sequence(List<Instrument> instruments, List<Integer> activatedSounds) {
        this.instruments = instruments;
        this.activatedSounds = activatedSounds;
    }

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

    public int getActivate(int id) {
        return activatedSounds.get(id);
    }

    public Instrument getInstrument(int id) {
        return instruments.get(id);
    }

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
