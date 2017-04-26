package dvt.polybeatmaker.model;

public enum ConfigurationType {

    COMPOSITION ("compositions/"),
    SEQUENCE ("sequences/");

    private String folder;

    ConfigurationType(String folder) {
        this.folder = folder;
    }

    public String getFolder(){
        return folder;
    }

}
