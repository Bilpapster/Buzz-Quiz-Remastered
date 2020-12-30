package com;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final int numberOfRounds;
    private final int numberOfQuestionsPerRound;
    private final ArrayList<Player> players;
    private final ArrayList<RoundLogicI> rounds;
    private final QuestionManager questionManager;
    private final Parser parser;
    private final HighscoreManager highscoreManager;

    public Game() {
        numberOfRounds = 6;
        numberOfQuestionsPerRound = 5;
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        questionManager = new QuestionManager();
        questionManager.createQuestions();
        parser = new Parser();
        highscoreManager = new HighscoreManager();
        players.add(new Player());
        players.add(new Player());
    }

    public Game(int numberOfRounds, int numberOfQuestionsPerRound, ArrayList<Player> players) {
        this.numberOfRounds = numberOfRounds;
        this.numberOfQuestionsPerRound = numberOfQuestionsPerRound;
        this.players = players;
        rounds = new ArrayList<>();
        questionManager = new QuestionManager();
        questionManager.createQuestions();
        parser = new Parser();
        highscoreManager = new HighscoreManager();
    }

    public void initializeGamePlay() {

        Random randomNumbersGenerator = new Random(System.currentTimeMillis());
        Referee referee = new Referee(players);

        for (int round = 0; round < numberOfRounds; round++) {
            int randomNumber = (Math.abs(randomNumbersGenerator.nextInt()) % 5) + 1;
            switch (randomNumber) {
                case 2:
                    rounds.add(new HighStakesRoundLogic(numberOfQuestionsPerRound, referee));
                    break;
                case 3:
                    rounds.add(new StopTheClockRoundLogic(numberOfQuestionsPerRound,referee));
                    break;
                case 4:
                    rounds.add(new FastestFingerRoundLogic(numberOfQuestionsPerRound, referee));
                    break;
                case 5:
                    rounds.add(new BoilingPointRoundLogic(referee));
                    break;
                default:
                    rounds.add(new PointBuilderRoundLogic(numberOfQuestionsPerRound, referee));
            }
        }
    }

    public void play() {
        int roundsCounter = 1;
        for (RoundLogicI round : rounds) {
            System.out.println();
            System.out.println("**********" + " Round " + roundsCounter + " **********");
            System.out.print(round.getDescription());
            System.out.print("Press enter to start round ");
            parser.getEnter();
//            while (!round.isOver()) {
//                round.askQuestion();
//                round.readAnswers();
//                round.giveCredits();
//            }
            System.out.println();
            System.out.println("End of Round " + (roundsCounter++) + ".");
            for (Player player : players) {
                player.printScore();
            }
        }
        highscoreManager.updateHighscores(players);
    }
}
