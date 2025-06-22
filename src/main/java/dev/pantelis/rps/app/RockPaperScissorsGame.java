package dev.pantelis.rps.app;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.player.Player;
import dev.pantelis.rps.domain.strategy.FixedMoveStrategy;
import dev.pantelis.rps.domain.strategy.RandomMoveStrategy;
import dev.pantelis.rps.game.RockPaperScissorsRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RockPaperScissorsGame {
    private static final Logger logger = LoggerFactory.getLogger(RockPaperScissorsGame.class);

    public static void main(String[] args) {
        Player playerOne = new Player("Paper Player", new FixedMoveStrategy(Move.PAPER));
        Player playerTwo = new Player("Random Player", new RandomMoveStrategy());

        //You can uncomment one of the following to switch strategies
//        Player playerOne = new Player("Copycat Player", new CopycatStrategy());
//        Player playerTwo = new Player("Counter Player", new CounterMoveStrategy());

        Player winner = play(playerOne, playerTwo, 100);

        logger.info("Overall Game Winner:");
        if (winner == null) {
            logger.info("The Game is a Tie!");
        } else {
            logger.info("{} Wins the Game!", winner.getName());
        }
    }

    public static Player play(Player playerOne, Player playerTwo, int rounds) {
        if (playerOne == null || playerTwo == null) {
            throw new IllegalArgumentException("Both players must not be null");
        }

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

        if (playerOneWins + playerTwoWins + ties != rounds) {
            logger.error("Error: Total rounds count mismatch for game!");
        }

        if (playerOneWins > playerTwoWins) {
            return playerOne;
        } else if (playerTwoWins > playerOneWins) {
            return playerTwo;
        } else {
            return null;
        }
    }
}