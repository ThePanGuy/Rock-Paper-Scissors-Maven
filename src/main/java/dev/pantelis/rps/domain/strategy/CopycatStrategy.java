package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;

public class CopycatStrategy implements MoveStrategy {
    private Move opponentLastMove;

    @Override
    public Move chooseMove() {
        if (opponentLastMove == null) {
            return Move.getRandomMove();
        }

        return opponentLastMove;
    }

    @Override
    public void updateStrategyState(Move opponentMove) {
        this.opponentLastMove = opponentMove;
    }
}
