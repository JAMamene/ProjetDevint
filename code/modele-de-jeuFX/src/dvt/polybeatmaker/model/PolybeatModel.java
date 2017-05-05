package dvt.polybeatmaker.model;

import dvt.jeu.simple.ModeleDevint;

/**
 * Main model of the game. Stores the scheduler used to play sounds.
 */
public class PolybeatModel extends ModeleDevint {

    private Scheduler scheduler;

    /**
     * Cretaes a new model and initializes its music scheduler.
     */
    public PolybeatModel() {
        scheduler = new Scheduler();
    }

    /**
     * Adds a new sound to the main loop.
     * Starts playback if it is not already started.
     *
     * @param sound - the sound to add
     */
    public void addSound(Sound sound) {
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        scheduler.addToQueue(sound);
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Removes a sound from a main loop.
     *
     * @param s - the sound to remove
     */
    public void removeSound(Sound s) {
        scheduler.removeFromQueue(s);
    }

    /**
     * Stop all sound from the loop.
     */
    public void removeAllSound() {
        scheduler.stopAllSound();
    }
}
