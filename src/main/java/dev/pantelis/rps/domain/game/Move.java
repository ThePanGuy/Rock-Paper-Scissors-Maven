package dev.pantelis.rps.domain.game;

import java.util.Random;

public enum Move {
    ROCK,
    PAPER,
    SCISSORS;

    private static final Random RANDOM = new Random();
    private static final Move[] VALUES = Move.values();

    public static Move getRandomMove() {
        return VALUES[RANDOM.nextInt(VALUES.length)];
    }
}
