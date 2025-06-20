package dev.example.rps.game;

import dev.example.rps.domain.Move;
import dev.example.rps.domain.GameResult;

import static dev.example.rps.domain.Move.*;

public class RockPaperScissorsGame {
    private RockPaperScissorsGame() {

    }

    public static GameResult play(Move playerOneMove, Move playerTwoMove) {
        if (playerOneMove.equals(playerTwoMove)) {
            return GameResult.TIE;
        }

        switch (playerOneMove) {
            case ROCK -> {
                return Move.SCISSORS.equals(playerTwoMove) ? GameResult.PLAYER_ONE_WINS : GameResult.PLAYER_TWO_WINS;
            }
            case PAPER -> {
                return ROCK.equals(playerTwoMove) ? GameResult.PLAYER_ONE_WINS : GameResult.PLAYER_TWO_WINS;
            }
            case SCISSORS -> {
                return PAPER.equals(playerTwoMove) ?  GameResult.PLAYER_ONE_WINS : GameResult.PLAYER_TWO_WINS;
            }
            default -> throw new IllegalStateException("Unexpected move from Player One: " + playerOneMove);
        }
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
