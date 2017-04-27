package dvt.polybeatmaker.model;

import dvt.devint.ConstantesDevint;
import dvt.devint.SceneDevint;
import dvt.jeu.simple.ControleDevint;
import dvt.jeu.simple.JeuDevint;
import dvt.jeu.simple.ModeleDevint;
import dvt.polybeatmaker.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;

/**
 * Main devint game.
 */
public class MainGame extends JeuDevint {

    private PolybeatModel model;

    @Override
    public String titre() {
        return "PolybeatMaker";
    }

    @Override
    protected ModeleDevint initModel() {
        model = new PolybeatModel();
        return model;
    }

    /**
     * Creates the game Model and controller for the main window.
     *
     * @return the controlleer of the main window
     */
    @Override
    protected ControleDevint initControlAndScene() {
        ControleDevint controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(new File("../ressources/fxml/mainGame.fxml").toURI().toURL());
            Parent root = loader.load();
            controller = loader.getController();
            MainController main = (MainController) controller;
            SceneDevint sc = new SceneDevint(root, ConstantesDevint.MAX_SCREEN_WIDTH, ConstantesDevint.MAX_SCREEN_HEIGHT);
            main.setScene(sc);
            main.setPolybeatModel(model);
            this.setOnHidden(we -> model.getScheduler().cancel());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

}
