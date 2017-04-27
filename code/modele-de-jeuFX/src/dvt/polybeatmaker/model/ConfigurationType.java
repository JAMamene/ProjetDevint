package dvt.polybeatmaker.model;

/**
 * Enum used for the types of Objects that can be saved as JSON.
 * Stores save paths and interface Strings.
 */
public enum ConfigurationType {

    COMPOSITION("compositions/", "Choisissez une composition", "Entrez un nom pour la composition"),
    SEQUENCE("sequences/", "Choisissez une séquence", "Entrez un nom pour la séquence");

    private String folder;
    private String choiceText;
    private String saveText;

    /**
     * Creates a new type of item to save as json.
     *
     * @param folder     - the save path of the items
     * @param choiceText - the text to be displayed when picking one of the item
     * @param saveText   - the text to be displayed when setting an item name
     */
    ConfigurationType(String folder, String choiceText, String saveText) {
        this.folder = folder;
        this.choiceText = choiceText;
        this.saveText = saveText;
    }

    public String getFolder() {
        return folder;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public String getSaveText() {
        return saveText;
    }
}
