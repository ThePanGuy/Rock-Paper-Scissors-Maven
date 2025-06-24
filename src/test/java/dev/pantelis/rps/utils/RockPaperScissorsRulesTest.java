package dev.pantelis.rps.utils;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsRulesTest {
    @Test
    void testGetRandomMove() {
        Move randomMove = Move.getRandomMove();
        assertNotNull(randomMove, "Random move should not be null");
        assertTrue(randomMove == Move.ROCK || randomMove == Move.PAPER || randomMove == Move.SCISSORS,
                "Random move should be one of ROCK, PAPER, or SCISSORS");
    }

    @Test
    void testRulesRockVsRockTie() {
        assertEquals(GameResult.TIE, RockPaperScissorsRules.determineResult(Move.ROCK, Move.ROCK));
    }

    @Test
    void testRulesPaperVsPaperTie() {
        assertEquals(GameResult.TIE, RockPaperScissorsRules.determineResult(Move.PAPER, Move.PAPER));
    }

    @Test
    void testRulesScissorsVsScissorsTie() {
        assertEquals(GameResult.TIE, RockPaperScissorsRules.determineResult(Move.SCISSORS, Move.SCISSORS));
    }

    @Test
    void testRulesPlayer1WinsPaperVsRock() {
        assertEquals(GameResult.PLAYER_ONE_WINS, RockPaperScissorsRules.determineResult(Move.PAPER, Move.ROCK));
    }

    @Test
    void testRulesPlayer1WinsRockVsScissors() {
        assertEquals(GameResult.PLAYER_ONE_WINS, RockPaperScissorsRules.determineResult(Move.ROCK, Move.SCISSORS));
    }

    @Test
    void testRulesPlayer1WinsScissorsVsPaper() {
        assertEquals(GameResult.PLAYER_ONE_WINS, RockPaperScissorsRules.determineResult(Move.SCISSORS, Move.PAPER));
    }

    @Test
    void testRulesPlayer2WinsRockVsPaper() {
        assertEquals(GameResult.PLAYER_TWO_WINS, RockPaperScissorsRules.determineResult(Move.ROCK, Move.PAPER));
    }

    @Test
    void testRulesPlayer2WinsPaperVsScissors() {
        assertEquals(GameResult.PLAYER_TWO_WINS, RockPaperScissorsRules.determineResult(Move.PAPER, Move.SCISSORS));
    }

    @Test
    void testRulesPlayer2WinsScissorsVsRock() {
        assertEquals(GameResult.PLAYER_TWO_WINS, RockPaperScissorsRules.determineResult(Move.SCISSORS, Move.ROCK));
    }

    @Test
    void testRulesNullMoves() {
        assertThrows(IllegalArgumentException.class, () -> RockPaperScissorsRules.determineResult(null, null));
        assertThrows(IllegalArgumentException.class, () -> RockPaperScissorsRules.determineResult(Move.SCISSORS, null));
        assertThrows(IllegalArgumentException.class, () -> RockPaperScissorsRules.determineResult(null, Move.ROCK));
    }

    @Test
    void testRulesGetWinningMove() {
        assertEquals(Move.PAPER, RockPaperScissorsRules.getWinningMove(Move.ROCK), "Paper beats Rock");
        assertEquals(Move.SCISSORS, RockPaperScissorsRules.getWinningMove(Move.PAPER), "Scissors beats Paper");
        assertEquals(Move.ROCK, RockPaperScissorsRules.getWinningMove(Move.SCISSORS), "Rock beats Scissors");
    }

    @Test
    void testRulesGetLosingMove() {
        assertEquals(Move.SCISSORS, RockPaperScissorsRules.getLosingMove(Move.ROCK), "Rock beats Scissors");
        assertEquals(Move.ROCK, RockPaperScissorsRules.getLosingMove(Move.PAPER), "Paper beats Rock");
        assertEquals(Move.PAPER, RockPaperScissorsRules.getLosingMove(Move.SCISSORS), "Scissors beats Paper");
    }

}