package dev.pantelis.rps.game;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.player.Player;

import static dev.pantelis.rps.domain.game.Move.*;

public class RockPaperScissorsRules {
    private RockPaperScissorsRules() {

    }

    public static GameResult play(Player playerOne, Player playerTwo) {
        int numberOfRounds = 100;

        int playerOneWins = 0;
        int playerTwoWins = 0;
        int ties = 0;

        System.out.println("Starting Rock-Paper-Scissors game simulation for " + numberOfRounds + " rounds...");

        for (int i = 0; i < numberOfRounds; i++) {
            Move playerOneMove = playerOne.chooseMove();
            Move playerTwoMove = playerTwo.chooseMove();

            GameResult roundResult = determineRoundResult(playerOneMove, playerTwoMove);

            playerOne.updateStrategyState(playerTwoMove);
            playerTwo.updateStrategyState(playerOneMove);

            switch (roundResult) {
                case PLAYER_ONE_WINS -> playerOneWins++;
                case PLAYER_TWO_WINS -> playerTwoWins++;
                case TIE -> ties++;
            }
        }

        System.out.println("\nGame Results:");
        System.out.println(playerOne.getName() + " wins " + playerOneWins + " of " + numberOfRounds + " games");
        System.out.println(playerTwo.getName() + " wins " + playerTwoWins + " of " + numberOfRounds + " games");
        System.out.println("Tie: " + ties + " of " + numberOfRounds + " games");

        if (playerOneWins + playerTwoWins + ties != numberOfRounds) {
            System.err.println("Error: Total rounds count mismatch!");
        }

        if (playerOneWins > playerTwoWins) {
            return GameResult.PLAYER_ONE_WINS;
        } else if (playerTwoWins > playerOneWins) {
            return GameResult.PLAYER_TWO_WINS;
        } else {
            return GameResult.TIE;
        }
    }

    public static GameResult determineRoundResult(Move playerOneMove, Move playerTwoMove) {
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
