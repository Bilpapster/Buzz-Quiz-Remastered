import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        QuestionManager questionManager = new QuestionManager();
        Parser parser = new Parser();

        questionManager.createQuestions();

        PlayerTest player1 = new PlayerTest(parser.askForName());

        while(questionManager.questionsRemaining() > 0) { // temporary way of knowing when the game will end, will be changed to rounds later on
            System.out.println();
            player1.printScore();
            questionManager.getNextQuestion().displayQuestion();
            String ans = parser.askForAnswer();
            if (questionManager.getNextQuestion().isCorrectAnswer(ans)) {
                System.out.println("Correct!");
                player1.updateScore(1000);
            }
            else
                System.out.println("Wrong...");
            questionManager.removeAnsweredQuestion();
        }
        player1.printScore();

    }

}
