package dev.example.rps.domain.player;

import dev.example.rps.domain.Move;

public class RandomPlayer extends Player {
    @Override
    public Move chooseMove() {
        return Move.getRandomMove();
    }
}
