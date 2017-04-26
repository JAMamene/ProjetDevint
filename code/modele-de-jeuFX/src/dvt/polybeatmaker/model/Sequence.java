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

    public int getActivate(int id) {
        return activatedSounds.get(id);
    }

    public Instrument getInstrument(int id) {
        return instruments.get(id);
    }

    public Sequence(JSONObject json) {
        instruments = new ArrayList<>();
        activatedSounds = new ArrayList<>();
        JSONArray jsonInstrument = json.getJSONArray(INSTRUMENT_KEY);
        for (int i = 0; i < jsonInstrument.length(); i++) {
            instruments.add(Instrument.getInstrument(jsonInstrument.getString(i)));
        }
        JSONArray activatedArray = json.getJSONArray(ACTIVATED_KEY);
        for (int i = 0; i < activatedArray.length(); i++) {
            activatedSounds.add(activatedArray.getInt(i));
        }
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
