import java.util.ArrayList;

public interface RoundI {
    String getName();
    int getNumberOfQuestions();
    ArrayList<PlayerTest> getPlayers();
    void askQuestion();
    String readAnswer();
    void giveCredits(String givenAnswer);
}
