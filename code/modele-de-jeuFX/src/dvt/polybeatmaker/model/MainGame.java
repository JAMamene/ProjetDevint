package dvt.polybeatmaker.model;

import dvt.jeu.simple.ControleDevint;
import dvt.jeu.simple.JeuDevint;
import dvt.jeu.simple.ModeleDevint;

/**
 * Jeu devint principal.
 */
public class MainGame extends JeuDevint {

    @Override
    public String titre() {
        return "PolybeatMaker";
    }

    @Override
    protected ModeleDevint initModel() {
//        SceneDevint sc = new SceneDevint()
        return null;
    }

    @Override
    protected ControleDevint initControlAndScene() {
        return null;
    }

}
