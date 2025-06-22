package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;

/**
 * Strategy: Always chooses a fixed move provided during construction.
 */
public class FixedMoveStrategy implements MoveStrategy {
    private final Move move;

    public FixedMoveStrategy(Move move) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null");
        }

        this.move = move;
    }

    @Override
    public Move chooseMove() {
        return move;
    }
}
