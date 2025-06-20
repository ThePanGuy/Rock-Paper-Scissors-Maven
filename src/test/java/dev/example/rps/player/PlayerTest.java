package dev.example.rps.player;

import dev.example.rps.domain.Move;
import dev.example.rps.domain.player.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("RockPlayer should always choose ROCK")
    void testRockPlayerChoice() {
        Player rockPlayer = new RockPlayer();
        for (int i = 0; i < 10; i++) {
            assertEquals(Move.ROCK, rockPlayer.chooseMove(), "Player A should always choose PAPER");
        }
    }

    @Test
    @DisplayName("PlayerB should choose a random move")
    void testRandomPlayerChoice() {
        Player randomPlayer = new RandomPlayer();
        Move chosenMove = randomPlayer.chooseMove();
        assertNotNull(chosenMove, "Random player's move should not be null");
        assertTrue(chosenMove == Move.ROCK || chosenMove == Move.PAPER || chosenMove == Move.SCISSORS,
                "Random player's move should be one of ROCK, PAPER, or SCISSORS");

        // A very basic check for "randomness": try a few times and see if not all are the same
        boolean sawDifferentMoves = false;
        Move firstMove = randomPlayer.chooseMove();
        for (int i = 0; i < 20; i++) {
            if (randomPlayer.chooseMove() != firstMove) {
                sawDifferentMoves = true;
                break;
            }
        }
        assertTrue(sawDifferentMoves, "Random player should be able to choose different moves over multiple attempts");
    }


    @Test
    @DisplayName("CopycatPlayer should choose randomly on first round, then copy opponent's last move")
    void testCopycatPlayerStrategy() {
        CopycatPlayer copycat = new CopycatPlayer();

        Move firstMove = copycat.chooseMove();
        assertNotNull(firstMove, "First move should not be null");
        assertTrue(firstMove == Move.ROCK || firstMove == Move.PAPER || firstMove == Move.SCISSORS,
                "First move should be one of ROCK, PAPER, or SCISSORS");

        copycat.setOpponentLastMove(Move.ROCK);
        assertEquals(Move.ROCK, copycat.chooseMove(), "Copycat should choose ROCK after opponent played ROCK");

        copycat.setOpponentLastMove(Move.PAPER);
        assertEquals(Move.PAPER, copycat.chooseMove(), "Copycat should choose PAPER after opponent played PAPER");

        copycat.setOpponentLastMove(Move.SCISSORS);
        assertEquals(Move.SCISSORS,
                copycat.chooseMove(),
                "Copycat should choose SCISSORS after opponent played SCISSORS");
    }

    @Test
    @DisplayName("CounterStrategistPlayer should choose randomly on first round, then beat opponent's last move")
    void testCounterStrategistPlayerStrategy() {
        CounterStrategistPlayer counterStrategist = new CounterStrategistPlayer();

        Move firstMove = counterStrategist.chooseMove();
        assertNotNull(firstMove, "First move should not be null");
        assertTrue(firstMove == Move.ROCK || firstMove == Move.PAPER || firstMove == Move.SCISSORS,
                "First move should be one of ROCK, PAPER, or SCISSORS");

        counterStrategist.setOpponentLastMove(Move.ROCK);
        assertEquals(Move.PAPER, counterStrategist.chooseMove(), "CounterStrategist should choose PAPER (beats ROCK)");

        counterStrategist.setOpponentLastMove(Move.PAPER);
        assertEquals(Move.SCISSORS,
                counterStrategist.chooseMove(),
                "CounterStrategist should choose SCISSORS (beats PAPER)");

        counterStrategist.setOpponentLastMove(Move.SCISSORS);
        assertEquals(Move.ROCK,
                counterStrategist.chooseMove(),
                "CounterStrategist should choose ROCK (beats SCISSORS)");
    }
}
