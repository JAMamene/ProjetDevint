package dvt.exemple.quizz;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainQuizz extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new JeuQuizz();
	}
}
