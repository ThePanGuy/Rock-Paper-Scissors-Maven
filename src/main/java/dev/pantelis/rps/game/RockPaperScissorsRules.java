package dev.pantelis.rps.game;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.player.Player;

import static dev.pantelis.rps.domain.game.Move.*;

public class RockPaperScissorsRules {
    private RockPaperScissorsRules() {

    }

    public static Player determineRoundWinner(Player playerOne, Player playerTwo) {
        Move playerOneMove = playerOne.chooseMove();
        Move playerTwoMove = playerTwo.chooseMove();

        GameResult result = determineResult(playerOneMove, playerTwoMove);

        playerOne.updateStrategyState(playerTwoMove);
        playerTwo.updateStrategyState(playerOneMove);

        return switch (result) {
            case PLAYER_ONE_WINS -> playerOne;
            case PLAYER_TWO_WINS -> playerTwo;
            case TIE -> null;
        };
    }


    public static GameResult determineResult(Move playerOneMove, Move playerTwoMove) {
        if (playerOneMove.equals(playerTwoMove)) {
            return GameResult.TIE;
        }

        switch (playerOneMove) {
            case ROCK -> {
                return SCISSORS.equals(playerTwoMove) ? GameResult.PLAYER_ONE_WINS : GameResult.PLAYER_TWO_WINS;
            }
            case PAPER -> {
                return ROCK.equals(playerTwoMove) ? GameResult.PLAYER_ONE_WINS : GameResult.PLAYER_TWO_WINS;
            }
            case SCISSORS -> {
                return PAPER.equals(playerTwoMove) ? GameResult.PLAYER_ONE_WINS : GameResult.PLAYER_TWO_WINS;
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
