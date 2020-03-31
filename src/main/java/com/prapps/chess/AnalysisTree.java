package com.prapps.chess;

import java.util.LinkedList;
import java.util.concurrent.Callable;

public class AnalysisTree implements Callable<LinkedList<Location>> {
    private Board board;

    public AnalysisTree(Board board) {
        this.board = board;
    }

    @Override
    public LinkedList<Location> call() {
        return move();
    }

    public LinkedList<Location> move() {
        if (board.getMoveOrder().size() >= 64) {
            System.out.println(board.hashCode()+" - "+Thread.currentThread().getId()+" "+board.getMoveOrder().size() + " "+board.getMoveOrder());
            return board.getMoveOrder();
        }

        for(Location loc : board.moves()) {
            if (!board.getMoveOrder().contains(loc)) {
                board.move(loc);
                LinkedList<Location> moveOrder = move();
                if (moveOrder.size() >= 64) {
                    return moveOrder;
                }
                board.undoMove();
            }
        }

        return board.getMoveOrder();
    }
}
