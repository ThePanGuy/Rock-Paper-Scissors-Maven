package dev.pantelis.rps.app;

import dev.pantelis.rps.domain.game.Game;
import dev.pantelis.rps.domain.player.Player;
import dev.pantelis.rps.domain.strategy.CopycatStrategy;
import dev.pantelis.rps.domain.strategy.CounterMoveStrategy;

import java.util.logging.Logger;

public class RockPaperScissorsGame {
    private static final Logger logger = Logger.getLogger(RockPaperScissorsGame.class.getName());


    public static void main(String[] args) {
        Player playerOne = new Player("Copycat Player", new CopycatStrategy());
        Player playerTwo = new Player("Counter Player", new CounterMoveStrategy());

        //You can uncomment one of the following to switch strategies
//        Player playerOne = new Player("Rock Player", new FixedMoveStrategy(Move.ROCK));
//        Player playerTwo = new Player("Random Player", new RandomMoveStrategy());

        Game game = new Game(playerOne, playerTwo, 100);
        game.play();

        logger.info("\nOverall Game Winner:");
        switch (game.getResult()) {
            case PLAYER_ONE_WINS -> logger.info(game.getPlayerOne().getName() + " Wins the Game!");
            case PLAYER_TWO_WINS -> logger.info(game.getPlayerTwo().getName() + " Wins the Game!");
            case TIE -> logger.info("The Game is a Tie!");
        }
    }
}