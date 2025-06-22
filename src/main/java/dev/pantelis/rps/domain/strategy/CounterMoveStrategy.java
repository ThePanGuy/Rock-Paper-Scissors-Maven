package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;

import static dev.pantelis.rps.game.RockPaperScissorsRules.getWinningMove;

/**
 * Strategy: Chooses the move that beats the opponent's last move.
 * For the first round (when no last opponent move is available), it chooses randomly.
 */
public class CounterMoveStrategy implements MoveStrategy {
    private Move opponentLastMove;

    @Override
    public Move chooseMove() {
        if (opponentLastMove == null) {
            return Move.getRandomMove();
        }

        return getWinningMove(opponentLastMove);
    }

    @Override
    public void updateStrategyState(Move opponentMove) {
        this.opponentLastMove = opponentMove;
    }
}
