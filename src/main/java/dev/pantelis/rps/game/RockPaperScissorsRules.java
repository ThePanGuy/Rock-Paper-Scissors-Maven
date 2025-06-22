package dev.pantelis.rps.game;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;

import static dev.pantelis.rps.domain.game.GameResult.*;
import static dev.pantelis.rps.domain.game.Move.*;

/**
 * A utility class that encapsulates the core rules of Rock-Paper-Scissors.
 */
public class RockPaperScissorsRules {
    private RockPaperScissorsRules() {

    }

    /**
     * Determines the winner of a single round based on the moves chosen by Player One and Player Two.
     *
     * Rules:
     * - Scissors beats Paper
     * - Rock beats Scissors
     * - Paper beats Rock
     * - If both players choose the same, it's a tie.
     *
     * @param playerOneMove The move chosen by Player One.
     * @param playerTwoMove The move chosen by Player Two.
     * @return The result of the round (PLAYER_ONE_WINS, PLAYER_TWO_WINS, or TIE), relative to Player One.
     */
    public static GameResult determineResult(Move playerOneMove, Move playerTwoMove) {
        if (playerOneMove.equals(playerTwoMove)) {
            return TIE;
        }

        return switch (playerOneMove) {
            case ROCK -> SCISSORS.equals(playerTwoMove) ? PLAYER_ONE_WINS : PLAYER_TWO_WINS;
            case PAPER -> ROCK.equals(playerTwoMove) ? PLAYER_ONE_WINS : PLAYER_TWO_WINS;
            case SCISSORS -> PAPER.equals(playerTwoMove) ? PLAYER_ONE_WINS : PLAYER_TWO_WINS;
        };
    }

    /**
     * Returns the move that wins against the given opponent's move.
     *
     * @param opponentMove The move of the opponent.
     * @return The Move that beats the opponent's move.
     */
    public static Move getWinningMove(Move opponentMove) {
        return switch (opponentMove) {
            case ROCK -> PAPER;
            case SCISSORS -> ROCK;
            case PAPER -> SCISSORS;
        };
    }

    /**
     * Returns the move that loses against the given opponent's move.
     *
     * @param opponentMove The move of the opponent.
     * @return The Move that the opponent's move beats.
     */
    public static Move getLosingMove(Move opponentMove) {
        return switch (opponentMove) {
            case ROCK -> SCISSORS;
            case SCISSORS -> PAPER;
            case PAPER -> ROCK;
        };
    }
}
