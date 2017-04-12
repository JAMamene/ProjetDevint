package dvt.polybeatmaker.model;

import dvt.jeu.simple.ModeleDevint;

/**
 * @author Guillaume Andre
 */
public class PolybeatModel extends ModeleDevint {

    private Scheduler scheduler;

    public PolybeatModel() {
        scheduler = new Scheduler();
    }

    public void addSound(Sound sound) {
        scheduler.addToQueue(sound);
    }

    public void removeSound(Sound s) {
        scheduler.removeFromQueue(s);
    }

}
