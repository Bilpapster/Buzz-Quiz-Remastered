import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileManager files = new FileManager();
        files.getQuestionsFromFile();
        files.getHighscoresFromFile();
        Game ourGame = new Game();
        ourGame.initializeGamePlay();
        ourGame.play();
    }
}
