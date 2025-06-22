package dev.pantelis.rps.app;

import dev.pantelis.rps.domain.game.Game;
import dev.pantelis.rps.domain.player.Player;
import dev.pantelis.rps.domain.strategy.CopycatStrategy;
import dev.pantelis.rps.domain.strategy.CounterMoveStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RockPaperScissorsGame {
    private static final Logger logger = LoggerFactory.getLogger(RockPaperScissorsGame.class);


    public static void main(String[] args) {
        Player playerOne = new Player("Copycat Player", new CopycatStrategy());
        Player playerTwo = new Player("Counter Player", new CounterMoveStrategy());

        //You can uncomment one of the following to switch strategies
//        Player playerOne = new Player("Rock Player", new FixedMoveStrategy(Move.ROCK));
//        Player playerTwo = new Player("Random Player", new RandomMoveStrategy());

        Game game = new Game(playerOne, playerTwo, 100);
        game.play();

        logger.info("Overall Game Winner:");
        if (game.getWinner() == null) {
            logger.info("The Game is a Tie!");
        } else {
            logger.info("{} Wins the Game!", game.getWinner().getName());
        }
    }
}