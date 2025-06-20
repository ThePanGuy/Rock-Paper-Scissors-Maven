package dev.example.rps.domain.player;

import dev.example.rps.domain.Move;

public abstract class Player {
    protected Move opponentLastMove;

    public abstract Move chooseMove();

    public void setOpponentLastMove(Move opponentLastMove) {
        this.opponentLastMove = opponentLastMove;
    }
}
