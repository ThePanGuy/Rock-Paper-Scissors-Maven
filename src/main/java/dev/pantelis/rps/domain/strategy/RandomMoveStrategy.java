package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;

public class RandomMoveStrategy implements MoveStrategy {
    @Override
    public Move chooseMove() {
        return Move.getRandomMove();
    }
}
