package dvt.polybeatmaker.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Sequence {

    public static int INACTIVE = -1;

    private List<Instrument> instruments;
    private List<Integer> activatedSounds;

    public Sequence(List<Instrument> instruments, List<Integer> activatedSounds) {
        this.instruments = instruments;
        this.activatedSounds = activatedSounds;
    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        JSONArray arrayInstruments = new JSONArray();
        for (Instrument instrument : instruments) {
            arrayInstruments.put(instrument.getName());
        }
        result.put("instruments", arrayInstruments);
        JSONArray activated = new JSONArray();
        for (int active : activatedSounds) {
            activated.put(active);
        }
        result.put("active", activated);
        return result;
    }

}
