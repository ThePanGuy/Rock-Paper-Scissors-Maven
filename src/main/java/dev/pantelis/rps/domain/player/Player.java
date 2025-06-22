package dev.pantelis.rps.domain.player;

import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.strategy.MoveStrategy;

/**
 * Represents a player in the Rock-Paper-Scissors game.
 * A player uses a MoveStrategy to decide his moves.
 */
public class Player {
    private String name;
    private MoveStrategy strategy;

    public Player(String name, MoveStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public MoveStrategy getStrategy() {
        return strategy;
    }

    public Move chooseMove() {
        return strategy.chooseMove();
    }

    /**
     * Informs the player's strategy about the opponent's move from the previous round.
     * This is crucial for stateful strategies to update their internal state.
     * @param opponentMove The opponent's move in the previous round.
     */
    public void updateStrategyState(Move opponentMove) {
        strategy.updateStrategyState(opponentMove);
    }
}
