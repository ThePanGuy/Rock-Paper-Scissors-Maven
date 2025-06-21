package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;

/**
 * Interface defining how a player chooses their move.
 * Strategies can be stateless or stateful, requiring updates.
 */
public interface MoveStrategy {

    /**
     * Determines the move for the current round based on the strategy's logic.
     *
     * @return The chosen Move.
     */
    Move chooseMove();

    /**
     * Updates the strategy with the opponent's move from the previous round.
     * This method is essential for stateful strategies (e.g., Copycat, CounterStrategist).
     * Stateless strategies can provide an empty implementation.
     *
     * @param opponentMove The move the opponent made in the previous round.
     */
    default void updateStrategyState(Move opponentMove) {
        // Default empty implementation for stateless strategies.
        // Stateful strategies will override this.
    }
}
