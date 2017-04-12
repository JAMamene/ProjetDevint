package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.Scheduler;
import dvt.polybeatmaker.model.Sound;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.util.List;

/**
 * Controller for the main screen.
 */
public class MainController extends ControleDevint {

    @FXML
    private HBox mainBox;

    private Scheduler scheduler;

    private List<InstrumentController> instruments;

    @Override
    protected void init() {
        scheduler = new Scheduler();
//        Sound phil = new Sound("phillippe.mp3");
//        Sound tg = new Sound("tg.mp3");
//        scheduler.addToQueue(phil);
//        scheduler.addToQueue(tg);
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        scheduler.removeFromQueue(tg);
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        scheduler.cancel();
    }

    @Override
    protected void reset() {

    }

    @Override
    public void mapTouchToActions() {

    }

    public void addSound(Sound sound) {
        scheduler.addToQueue(sound);
    }

}
