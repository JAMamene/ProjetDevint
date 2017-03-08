package dvt.jeu.simple;


import javafx.stage.Stage;

/**
 * classe à étendre pour créer un jeu Devint avec une IHM immobile qui se rafraîchit
 * selon les évênements utilisateur (par exemple, pour faire un quizz)
 *
 * @author helen
 */
public abstract class JeuDevint extends Stage {

    /**
     * le controle du jeu
     */
    protected ControleDevint control;

    /**
     * le modele du jeu
     */
    protected ModeleDevint model;

    /**
     * le titre du jeu
     *
     * @return le titre
     */
    public abstract String titre();

    /**
     * initialisation du modèle
     * méthode appelée dans le constructeur
     */
    protected abstract ModeleDevint initModel();

    /**
     * initialisation du contrôle devint et de la scene devint
     * méthode appelée dans le constructeur
     * la scene peut être construite à partir du FXML via un FXMLLoader()
     * <p>
     * on peut aussi tout créer à la main si on ne veut pas passer par FXML
     */
    protected abstract ControleDevint initControlAndScene();

    /**
     * constructeur du jeu
     */
    public JeuDevint() {
        this.model = initModel();
        this.control = initControlAndScene();
        this.control.setModel(model);
        this.control.mapTouchToActions();
        this.control.init();
        this.setScene(control.getScene());
        this.setTitle(titre());
        this.show();
    }

}
