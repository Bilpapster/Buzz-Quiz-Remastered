import java.util.ArrayList;

public interface RoundI {
    int getNumberOfQuestions();
    ArrayList<PlayerTest> getPlayers();
    void askQuestion();
    void collectAnswer();
    void giveCredits();
}
