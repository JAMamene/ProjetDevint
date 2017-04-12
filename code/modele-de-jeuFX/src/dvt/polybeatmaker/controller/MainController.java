package dvt.polybeatmaker.controller;

import dvt.jeu.simple.ControleDevint;
import dvt.polybeatmaker.model.Instrument;
import dvt.polybeatmaker.model.Scheduler;
import dvt.polybeatmaker.model.Sound;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

/**
 * Controller for the main screen.
 */
public class MainController extends ControleDevint {

    @FXML
    private HBox mainBox;

    private Scheduler scheduler;

    @Override
    protected void init() {
        scheduler = new Scheduler();
        try {
            for (Instrument instrument : Instrument.values()) {
                FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/instrument.fxml").toURI().toURL());
                mainBox.getChildren().add(loader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
