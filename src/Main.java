import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        QuestionManager questionManager = new QuestionManager();

        questionManager.createQuestions();

        System.out.print("State your name player: ");
        Player player1 = new Player(input.nextLine());

        while(questionManager.listOfQuestions.size() > 0) {
            System.out.println();
            System.out.println();
            player1.printScore();
            questionManager.listOfQuestions.get(0).displayQuestion();
            String ans = input.nextLine();
            if (questionManager.listOfQuestions.get(0).isCorrectAnswer(ans)) {
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
