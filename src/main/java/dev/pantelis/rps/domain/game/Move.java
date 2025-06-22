package dev.pantelis.rps.domain.game;

import java.util.Random;

/**
 * Enum representing the possible moves in Rock-Paper-Scissors.
 * Provides a utility method to get a random move.
 */
public enum Move {
    ROCK,
    PAPER,
    SCISSORS;

    private static final Random RANDOM = new Random();
    private static final Move[] VALUES = Move.values();

    /**
     * Returns a random move from the available options (ROCK, PAPER, SCISSORS).
     *
     * @return A randomly selected Move.
     */
    public static Move getRandomMove() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }
}
