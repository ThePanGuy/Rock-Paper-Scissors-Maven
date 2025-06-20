package dev.example.rps.domain.players;

import dev.example.rps.domain.Move;

public class CopycatPlayer extends Player {
    @Override
    public Move chooseMove() {
        if (opponentLastMove == null) {
            return Move.getRandomMove();
        }

        return opponentLastMove;
    }
}
