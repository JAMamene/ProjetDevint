package dvt.exemple.multijoueur;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainMulti extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new JeuMulti();
	}
}
