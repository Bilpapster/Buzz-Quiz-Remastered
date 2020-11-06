import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        QuestionManager questionManager = new QuestionManager();

        questionManager.createQuestions();

        System.out.print("State your name player: ");
        Player player1 = new Player(input.nextLine());

        while(questionManager.questionsRemaining() > 0) { // temporary way of knowing when the game will end, will be changed to rounds later on
            System.out.println();
            System.out.println();
            player1.printScore();
            questionManager.getNextQuestion().displayQuestion();
            String ans = input.nextLine();
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
