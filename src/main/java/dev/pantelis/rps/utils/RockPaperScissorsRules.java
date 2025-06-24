package dev.pantelis.rps.utils;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;

import static dev.pantelis.rps.domain.game.GameResult.*;
import static dev.pantelis.rps.domain.game.Move.*;

public class RockPaperScissorsRules {
    private RockPaperScissorsRules() {

    }

    public static GameResult determineResult(Move playerOneMove, Move playerTwoMove) {
        if (playerOneMove == null || playerTwoMove == null) {
            throw new IllegalArgumentException("Both player's moves must not be null");
        }

        if (playerOneMove.equals(playerTwoMove)) {
            return TIE;
        }

        return switch (playerOneMove) {
            case ROCK -> SCISSORS.equals(playerTwoMove) ? PLAYER_ONE_WINS : PLAYER_TWO_WINS;
            case PAPER -> ROCK.equals(playerTwoMove) ? PLAYER_ONE_WINS : PLAYER_TWO_WINS;
            case SCISSORS -> PAPER.equals(playerTwoMove) ? PLAYER_ONE_WINS : PLAYER_TWO_WINS;
        };
    }

    public static Move getWinningMove(Move opponentMove) {
        return switch (opponentMove) {
            case ROCK -> PAPER;
            case SCISSORS -> ROCK;
            case PAPER -> SCISSORS;
        };
    }

    public static Move getLosingMove(Move opponentMove) {
        return switch (opponentMove) {
            case ROCK -> SCISSORS;
            case SCISSORS -> PAPER;
            case PAPER -> ROCK;
        };
    }
}
