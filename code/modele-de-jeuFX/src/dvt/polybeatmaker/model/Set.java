package dvt.polybeatmaker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dvt.polybeatmaker.model.Instrument.*;

public enum Set {
    ROCK(0, Arrays.asList(PIANO, GUITAR, ELECTRIC_GUITAR, BASS)),
    CHILL(1, Arrays.asList(PIANOG, MISC, PIANOD));

    private final static int NUMBER_MAX_OF_SET = 2;
    private static int CPT = 0;
    private int id;
    private List<Instrument> instruments;

    Set(int id, List<Instrument> instruments){
        this.instruments = new ArrayList<>();
        for(Instrument instru : instruments){
            this.instruments.add(instru);
        }
        this.id = id;
    }

    public static Set moveToSet(int call){
        CPT = Math.floorMod(CPT + call, NUMBER_MAX_OF_SET);
        return getSet(CPT);
    }

    public static Set getSet(int id) {
        for (Set set : Set.values()) {
            if (id == (set.id)) {
                return set ;
            }
        }
        return null;
    }

    public int getId(){ return id;}

    public List<Instrument> getInstruments() {
        return instruments;
    }


}
