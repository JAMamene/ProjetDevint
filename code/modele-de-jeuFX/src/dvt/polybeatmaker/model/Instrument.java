package dvt.polybeatmaker.model;

/**
 * Created by Meriveri on 05/04/2017.
 */
public enum Instrument
{
    PIANO("piano"),
    GUITAR("guitar"),
    ELECTRICGUITAR("electricguitar"),
    CELLO("cello"),
    DRUMS("drums");

    private String name;

    Instrument (String name)
    {
        this.name = name;
    }

}
