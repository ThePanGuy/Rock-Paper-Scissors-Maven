package dev.pantelis.rps.domain.strategy;

import dev.pantelis.rps.domain.game.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveStrategyTest {
    @Test
    void testFixedMoveStrategy() {
        MoveStrategy strategy = new FixedMoveStrategy(Move.PAPER);
        assertEquals(Move.PAPER,
                strategy.chooseMove(),
                "FixedMoveStrategy initialized with PAPER should always choose PAPER");
    }

    @Test
    void testRandomMoveStrategy() {
        MoveStrategy strategy = new RandomMoveStrategy();
        Move chosenMove = strategy.chooseMove();
        assertNotNull(chosenMove, "RandomMoveStrategy's move should not be null");
        assertTrue(chosenMove.equals(Move.ROCK) || chosenMove.equals(Move.PAPER) || chosenMove.equals(Move.SCISSORS),
                "RandomMoveStrategy's move should be one of ROCK, PAPER, or SCISSORS");
    }

    @Test
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