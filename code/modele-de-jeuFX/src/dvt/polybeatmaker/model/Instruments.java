package dvt.polybeatmaker.model;

/**
 * Created byy Meriveri on 05/04/2017.
 */
public enum Instruments
{
    PIANO("piano"),
    GUITAR("guitar"),
    ELECTRICGUITAR("electricguitar"),
    CELLO("cello"),
    DRUMS("drums");

    private String name;

    Instruments (String name)
    {
        this.name = name;
    }
}
