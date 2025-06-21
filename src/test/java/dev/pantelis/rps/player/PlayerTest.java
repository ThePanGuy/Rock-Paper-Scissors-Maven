package dev.pantelis.rps.player;

import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("FixedMoveStrategy should always choose PAPER")
    void testFixedMoveStrategy() {
        MoveStrategy strategy = new FixedMoveStrategy(Move.PAPER);
        assertEquals(Move.PAPER,
                strategy.chooseMove(),
                "FixedMoveStrategy initialized with PAPER should always choose PAPER");
    }

    @Test
    @DisplayName("RandomMoveStrategy should choose a valid random move")
    void testRandomMoveStrategy() {
        MoveStrategy strategy = new RandomMoveStrategy();
        Move chosenMove = strategy.chooseMove();
        assertNotNull(chosenMove, "RandomMoveStrategy's move should not be null");
        assertTrue(chosenMove.equals(Move.ROCK) || chosenMove.equals(Move.PAPER) || chosenMove.equals(Move.SCISSORS),
                "RandomMoveStrategy's move should be one of ROCK, PAPER, or SCISSORS");
    }

    @Test
    @DisplayName("CopycatStrategy should choose randomly on first round, then copy opponent's last move")
    void testCopycatStrategy() {
        CopycatStrategy copycat = new CopycatStrategy();

        Move firstMove = copycat.chooseMove();
        assertNotNull(firstMove, "First move should not be null");

        copycat.updateStrategyState(Move.ROCK);
        assertEquals(Move.ROCK, copycat.chooseMove(), "Copycat should choose ROCK after opponent played ROCK");

        copycat.updateStrategyState(Move.PAPER);
        assertEquals(Move.PAPER, copycat.chooseMove(), "Copycat should choose PAPER after opponent played PAPER");
    }

    @Test
    @DisplayName("CounterStrategy should choose randomly on first round, then beat opponent's last move")
    void testCounterStrategy() {
        CounterMoveStrategy counterStrategist = new CounterMoveStrategy();

        Move firstMove = counterStrategist.chooseMove();
        assertNotNull(firstMove, "First move should not be null");

        counterStrategist.updateStrategyState(Move.ROCK);
        assertEquals(Move.PAPER, counterStrategist.chooseMove(),
                "CounterStrategist should choose PAPER (beats ROCK)");

        counterStrategist.updateStrategyState(Move.PAPER);
        assertEquals(Move.SCISSORS,
                counterStrategist.chooseMove(),
                "CounterStrategist should choose SCISSORS (beats PAPER)");
    }
}
