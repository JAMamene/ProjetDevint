package dvt.polybeatmaker.model;


import dvt.polybeatmaker.controller.MainController;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler extends Timer {

    private static final int LOOPTIME = 6;

    private MainController controller;

    private boolean started;

    private List<Sound> sounds;

    public Scheduler() {
        super();
        started = false;
        sounds = new ArrayList<>();
    }

    public void addToQueue(Sound s) {
        if (!sounds.contains(s)) {
            sounds.add(s);
        }
    }

    public void removeFromQueue(Sound s) {
        sounds.remove(s);
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void start() {
        started = true;
        schedule(
                new TimerTask() {
                    int i = 0;

                    @Override
                    public void run() {
                        double progress = i % (LOOPTIME) / (double) (LOOPTIME - 1);
                        if (i % LOOPTIME == 0) {
                            for (Sound s : sounds) {
                                s.play();
                            }
                            controller.updateProgressBar(progress);
                        } else {
                            controller.updateProgressBar(progress);
                        }
                        i++;
                    }
                }, 0, 1000);
    }

    public boolean isStarted() {
        return started;
    }

}
