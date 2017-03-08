package dvt.exemple.quizz;

import dvt.jeu.simple.ModeleDevint;

/** jeu de quizz : version simple du jeu
* TODO : on pourrait mettre les questions et réponses dans un fichier dom
* */


public class QuizzModel extends ModeleDevint{
    private static final String[] QUESTIONS = {"Combien font 4 fois 4 ?", "Combien font 30 moins 5 ?", "Combien font 5 plus 5 ?" };
    private static final int[] ANSWERS = {16,25,10};
    private int random;
    
    // constructeur simple
    // on pourrait par exemple passer le chemin vers le fichier des questions
    public QuizzModel() {
    	super();
    	random = 0;
    }
    
    public String question() {
    	return QUESTIONS[random];
    }
    
    public void reset() {
		random = (int)(Math.random() * QUESTIONS.length);
    }

    public String reponse(int i) {
    	return String.valueOf(ANSWERS[i]);
    }
    
    public boolean valide(int i) {
    	return i==random;
    }
}
