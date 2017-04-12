package dvt.polybeatmaker.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler extends Timer {

    private static final int LOOPTIME = 6000;

    private List<Sound> sounds;

    public Scheduler() {
        super();
        sounds = new ArrayList<>();
        schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        for (Sound s : sounds) {
                            s.play();
                        }
                    }
                }, 0, LOOPTIME);
    }

    public void addToQueue(Sound s) {
        sounds.add(s);
    }

    public void removeFromQueue(Sound s) {
        sounds.remove(s);
    }

}
