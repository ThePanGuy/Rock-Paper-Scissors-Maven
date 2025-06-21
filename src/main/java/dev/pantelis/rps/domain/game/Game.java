package dev.pantelis.rps.domain.game;

import dev.pantelis.rps.domain.player.Player;
import dev.pantelis.rps.game.RockPaperScissorsRules;

import java.util.logging.Logger;

public class Game {
    private static final Logger logger = Logger.getLogger(Game.class.getName());

    private final Player playerOne;
    private final Player playerTwo;
    private final int rounds;
    private GameResult result;

    public Game(Player playerOne, Player playerTwo, int rounds) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.rounds = rounds;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public GameResult getResult() {
        return result;
    }

    public void play() {
        int playerOneWins = 0;
        int playerTwoWins = 0;
        int ties = 0;

        logger.info("Starting game: " + playerOne.getName() + " vs " + playerTwo.getName() + " for " + rounds + " rounds.");
        logger.info(playerOne.getName() + " (Strategy: " + playerOne.getStrategy().getClass().getSimpleName() + ")");
        logger.info(playerTwo.getName() + " (Strategy: " + playerTwo.getStrategy().getClass().getSimpleName() + ")");
        logger.info("--------------------------------------------------");

        for (int round = 0; round < rounds; round++) {
            Move playerOneMove = playerOne.chooseMove();
            Move playerTwoMove = playerTwo.chooseMove();

            GameResult roundResult = RockPaperScissorsRules.determineResult(playerOneMove, playerTwoMove);

            playerOne.updateStrategyState(playerTwoMove);
            playerTwo.updateStrategyState(playerOneMove);

            switch (roundResult) {
                case PLAYER_ONE_WINS -> playerOneWins++;
                case PLAYER_TWO_WINS -> playerTwoWins++;
                case TIE -> ties++;
            }
        }

        logger.info("\nGame Results:");
        logger.info(playerOne.getName() + " wins " + playerOneWins + " of " + rounds + " games");
        logger.info(playerTwo.getName() + " wins " + playerTwoWins + " of " + rounds + " games");
        logger.info("Tie: " + ties + " of " + rounds + " games");

        if (playerOneWins > playerTwoWins) {
            result = GameResult.PLAYER_ONE_WINS;
        } else if (playerTwoWins > playerOneWins) {
            result = GameResult.PLAYER_TWO_WINS;
        } else {
            result = GameResult.TIE;
        }

        if (playerOneWins + playerTwoWins + ties != rounds) {
            logger.severe("Error: Total rounds count mismatch for game!");
        }
    }
}
