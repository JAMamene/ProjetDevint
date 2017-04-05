package dvt.polybeatmaker.controller;

import dvt.devint.menu.MenuDevint;
import dvt.polybeatmaker.model.MainGame;

/**
 * Main menu controller.
 */
public class MenuController extends MenuDevint {

    @Override
    public String titre() {
        return "PolybeatMaker";
    }

    @Override
    public void initMenu() {
        control.addMenuItem("Démarrer PolybeatMaker", (x) -> new MainGame());
        control.addMenuItem("Choisir instruments", (x) -> new MainGame());
        control.addMenuItem("Aide", (x) -> new MainGame());
    }
}
