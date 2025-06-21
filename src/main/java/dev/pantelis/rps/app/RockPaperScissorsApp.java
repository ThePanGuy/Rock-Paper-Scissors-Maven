package dev.pantelis.rps.app;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.player.*;
import dev.pantelis.rps.domain.strategy.CopycatStrategy;
import dev.pantelis.rps.domain.strategy.CounterMoveStrategy;
import dev.pantelis.rps.game.RockPaperScissorsRules;

public class RockPaperScissorsApp {
    public static void main(String[] args) {
        Player playerOne = new Player("Copycat Player", new CopycatStrategy());
        Player playerTwo = new Player("Counter Player", new CounterMoveStrategy());

        GameResult finalResult = RockPaperScissorsRules.play(playerOne, playerTwo);

        switch (finalResult) {
            case PLAYER_ONE_WINS ->  System.out.println("Player 1 Wins!");
            case PLAYER_TWO_WINS ->  System.out.println("Player 2 Wins!");
            case TIE ->   System.out.println("It's a tie!");
        }
    }
}