package dvt.polybeatmaker.model;

import dvt.jeu.simple.ModeleDevint;

public class PolybeatModel extends ModeleDevint {

    private Scheduler scheduler;

    public PolybeatModel() {
        scheduler = new Scheduler();
    }

    public void addSound(Sound sound) {
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        scheduler.addToQueue(sound);
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void removeSound(Sound s) {
        scheduler.removeFromQueue(s);
    }

}
