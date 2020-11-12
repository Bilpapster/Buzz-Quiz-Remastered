import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game ourGame = new Game();
        ourGame.initializeGamePlay();
        ourGame.play();
    }
}
