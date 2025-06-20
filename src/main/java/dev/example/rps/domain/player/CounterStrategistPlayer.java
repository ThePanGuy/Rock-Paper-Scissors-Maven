package dev.example.rps.domain.player;

import dev.example.rps.domain.Move;
import dev.example.rps.game.RockPaperScissorsGame;

public class CounterStrategistPlayer extends Player {
    @Override
    public Move chooseMove() {
        if (opponentLastMove == null) {
            return Move.getRandomMove();
        }

        return RockPaperScissorsGame.getWinningMove(opponentLastMove);
    }
}
