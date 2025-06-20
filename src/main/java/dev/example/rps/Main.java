package dev.example.rps;

import dev.example.rps.domain.GameResult;
import dev.example.rps.domain.Move;
import dev.example.rps.domain.players.CopycatPlayer;
import dev.example.rps.domain.players.CounterStrategistPlayer;
import dev.example.rps.domain.players.Player;
import dev.example.rps.game.RockPaperScissorsGame;

public class Main {
    public static void main(String[] args) {
        Player playerOne = new CopycatPlayer();
        Player playerTwo = new CounterStrategistPlayer();

        int numberOfRounds = 100;

        int playerOneWins = 0;
        int playerTwoWins = 0;
        int ties = 0;

        System.out.println("Starting Rock-Paper-Scissors game simulation for " + numberOfRounds + " rounds...");

        for (int i = 0; i < numberOfRounds; i++) {
            Move playerOneMove = playerOne.chooseMove();
            Move playerTwoMove = playerTwo.chooseMove();

            GameResult gameResult = RockPaperScissorsGame.play(playerOneMove, playerTwoMove);

            playerOne.setOpponentLastMove(playerTwoMove);
            playerTwo.setOpponentLastMove(playerOneMove);

            switch (gameResult) {
                case PLAYER_ONE_WINS -> playerOneWins++;
                case PLAYER_TWO_WINS -> playerTwoWins++;
                case TIE -> ties++;
            }
        }

        // Print the final results
        System.out.println("\nGame Results:");
        System.out.println("Player One wins " + playerOneWins + " of " + numberOfRounds + " games");
        System.out.println("Player Two wins " + playerTwoWins + " of " + numberOfRounds + " games");
        System.out.println("Tie: " + ties + " of " + numberOfRounds + " games");

        // Simple validation that total rounds match
        if (playerOneWins + playerTwoWins + ties != numberOfRounds) {
            System.err.println("Error: Total rounds count mismatch!");
        }
    }
}