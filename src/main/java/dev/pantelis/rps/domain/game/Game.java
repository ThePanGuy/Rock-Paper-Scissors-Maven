package dev.pantelis.rps.domain.game;

import dev.pantelis.rps.domain.player.Player;
import dev.pantelis.rps.game.RockPaperScissorsRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

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

        logger.info("Starting game: {} vs {} for {} rounds.", playerOne.getName(), playerTwo.getName(), rounds);
        logger.info("{} (Strategy: {})", playerOne.getName(), playerOne.getStrategy().getClass().getSimpleName());
        logger.info("{} (Strategy: {})", playerTwo.getName(), playerTwo.getStrategy().getClass().getSimpleName());
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

        logger.info("Game Results:");
        logger.info("{} wins {} of {} games", playerOne.getName(), playerOneWins, rounds);
        logger.info("{} wins {} of {} games", playerTwo.getName(), playerTwoWins, rounds);
        logger.info("Tie: {} of {} games", ties, rounds);

        if (playerOneWins > playerTwoWins) {
            result = GameResult.PLAYER_ONE_WINS;
        } else if (playerTwoWins > playerOneWins) {
            result = GameResult.PLAYER_TWO_WINS;
        } else {
            result = GameResult.TIE;
        }

        if (playerOneWins + playerTwoWins + ties != rounds) {
            logger.error("Error: Total rounds count mismatch for game!");
        }
    }
}
