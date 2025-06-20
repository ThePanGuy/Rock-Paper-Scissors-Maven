package dev.example.rps.domain.player;

import dev.example.rps.domain.Move;

public class RockPlayer extends Player {
    @Override
    public Move chooseMove() {
        return Move.ROCK;
    }
}
