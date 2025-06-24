package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;

public interface MoveStrategy {

    Move chooseMove();

    default void updateStrategyState(Move opponentMove) {
    }
}
