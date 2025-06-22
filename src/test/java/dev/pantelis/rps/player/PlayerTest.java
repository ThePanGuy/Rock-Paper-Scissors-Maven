package dev.pantelis.rps.player;

import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.player.Player;
import dev.pantelis.rps.domain.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("Player should use his strategy via chooseMove")
    void testPlayerChooseMoveStrategy() {
        MoveStrategy mockStrategy = new MoveStrategy() {
            private int callCount = 0;

            @Override
            public Move chooseMove() {
                callCount++;
                return (callCount % 2 == 0) ? Move.ROCK : Move.PAPER;
            }
        };

        Player player = new Player("Test Player", mockStrategy);

        assertEquals(Move.PAPER, player.chooseMove(), "Player should use his strategy to choose a move");
        assertEquals(Move.ROCK, player.chooseMove(), "Player should use his strategy to choose a move");
    }

    @Test
    @DisplayName("Player should update strategy state correctly via updateStrategyState")
    void testPlayerUpdatesStrategyState() {
        class MockStrategy implements MoveStrategy {
            public Move mockMove;

            @Override
            public Move chooseMove() {
                return Move.ROCK;
            }

            @Override
            public void updateStrategyState(Move mockMove) {
                this.mockMove = mockMove;
            }
        }

        MockStrategy mockStrategy = new MockStrategy();
        Player player = new Player("Copy Player", mockStrategy);

        player.updateStrategyState(Move.SCISSORS);
        assertEquals(Move.SCISSORS, mockStrategy.mockMove);

        player.updateStrategyState(Move.PAPER);
        assertEquals(Move.PAPER, mockStrategy.mockMove);
    }
}
