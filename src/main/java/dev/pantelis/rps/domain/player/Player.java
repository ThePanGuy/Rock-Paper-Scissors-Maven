package dev.pantelis.rps.domain.player;

import dev.pantelis.rps.domain.game.Move;
import dev.pantelis.rps.domain.strategy.MoveStrategy;

public class Player {
    private String name;
    private MoveStrategy strategy;

    public Player(String name, MoveStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public MoveStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public Move chooseMove() {
        return strategy.chooseMove();
    }

    public void updateStrategyState(Move opponentMove) {
        strategy.updateStrategyState(opponentMove);
    }
}
