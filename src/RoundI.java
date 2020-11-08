import java.util.ArrayList;

public interface RoundI {
    void printDescription();
    int getNumberOfQuestions();
    ArrayList<Player> getPlayers();

    void askQuestion();
    String readAnswer();
    void giveCredits(String givenAnswer);
}
