package dev.pantelis.rps.game;

import dev.pantelis.rps.domain.game.GameResult;
import dev.pantelis.rps.domain.game.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsRulesTest {
    @Test
    @DisplayName("Move.getRandomMove should return a valid move")
    void testGetRandomMove() {
        Move randomMove = Move.getRandomMove();
        assertNotNull(randomMove, "Random move should not be null");
        assertTrue(randomMove == Move.ROCK || randomMove == Move.PAPER || randomMove == Move.SCISSORS,
                "Random move should be one of ROCK, PAPER, or SCISSORS");
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Tie - Rock vs Rock")
    void testRulesRockVsRockTie() {
        assertEquals(GameResult.TIE, RockPaperScissorsRules.determineResult(Move.ROCK, Move.ROCK));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Tie - Paper vs Paper")
    void testRulesPaperVsPaperTie() {
        assertEquals(GameResult.TIE, RockPaperScissorsRules.determineResult(Move.PAPER, Move.PAPER));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Tie - Scissors vs Scissors")
    void testRulesScissorsVsScissorsTie() {
        assertEquals(GameResult.TIE, RockPaperScissorsRules.determineResult(Move.SCISSORS, Move.SCISSORS));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Player One Wins - Paper vs Rock")
    void testRulesPlayer1WinsPaperVsRock() {
        assertEquals(GameResult.PLAYER_ONE_WINS, RockPaperScissorsRules.determineResult(Move.PAPER, Move.ROCK));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Player One Wins - Rock vs Scissors")
    void testRulesPlayer1WinsRockVsScissors() {
        assertEquals(GameResult.PLAYER_ONE_WINS, RockPaperScissorsRules.determineResult(Move.ROCK, Move.SCISSORS));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Player One Wins - Scissors vs Paper")
    void testRulesPlayer1WinsScissorsVsPaper() {
        assertEquals(GameResult.PLAYER_ONE_WINS, RockPaperScissorsRules.determineResult(Move.SCISSORS, Move.PAPER));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Player Two Wins - Rock vs Paper")
    void testRulesPlayer2WinsRockVsPaper() {
        assertEquals(GameResult.PLAYER_TWO_WINS, RockPaperScissorsRules.determineResult(Move.ROCK, Move.PAPER));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Player Two Wins - Paper vs Scissors")
    void testRulesPlayer2WinsPaperVsScissors() {
        assertEquals(GameResult.PLAYER_TWO_WINS, RockPaperScissorsRules.determineResult(Move.PAPER, Move.SCISSORS));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.determineResult: Player Two Wins - Scissors vs Rock")
    void testRulesPlayer2WinsScissorsVsRock() {
        assertEquals(GameResult.PLAYER_TWO_WINS, RockPaperScissorsRules.determineResult(Move.SCISSORS, Move.ROCK));
    }

    @Test
    @DisplayName("RockPaperScissorsGame.getWinningMove should return the correct winning move")
    void testRulesGetWinningMove() {
        assertEquals(Move.PAPER, RockPaperScissorsRules.getWinningMove(Move.ROCK), "Paper beats Rock");
        assertEquals(Move.SCISSORS, RockPaperScissorsRules.getWinningMove(Move.PAPER), "Scissors beats Paper");
        assertEquals(Move.ROCK, RockPaperScissorsRules.getWinningMove(Move.SCISSORS), "Rock beats Scissors");
    }

    @Test
    @DisplayName("RockPaperScissorsGame.getLosingMove should return the correct losing move")
    void testRulesGetLosingMove() {
        assertEquals(Move.SCISSORS, RockPaperScissorsRules.getLosingMove(Move.ROCK), "Rock beats Scissors");
        assertEquals(Move.ROCK, RockPaperScissorsRules.getLosingMove(Move.PAPER), "Paper beats Rock");
        assertEquals(Move.PAPER, RockPaperScissorsRules.getLosingMove(Move.SCISSORS), "Scissors beats Paper");
    }

}