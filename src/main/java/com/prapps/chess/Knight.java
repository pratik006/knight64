package com.prapps.chess;

import static com.prapps.chess.Board.FILES;
import static com.prapps.chess.Board.RANKS;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece implements Cloneable {
    public Knight(Location loc) {
        super(loc);
    }

    @Override
    public List<Location> moves() {
        List<Location> moves = new ArrayList<>(8);
        if (loc.getX()+2 < RANKS && loc.getY()+1 < FILES) {
            moves.add(new Location(loc.getX()+2, loc.getY()+1));
        }
        if (loc.getX()+2 < RANKS && loc.getY()-1 >= 0) {
            moves.add(new Location(loc.getX()+2, loc.getY()-1));
        }
        if (loc.getX()-2 >= 0 && loc.getY()+1 < FILES) {
            moves.add(new Location(loc.getX()-2, loc.getY()+1));
        }
        if (loc.getX()-2 >= 0 && loc.getY()-1 >= 0) {
            moves.add(new Location(loc.getX()-2, loc.getY()-1));
        }

        if (loc.getX()+1 < RANKS && loc.getY()+2 < FILES) {
            moves.add(new Location(loc.getX()+1, loc.getY()+2));
        }
        if (loc.getX()-1 >= 0 && loc.getY()+2 < FILES) {
            moves.add(new Location(loc.getX()-1, loc.getY()+2));
        }
        if (loc.getX()+1 < RANKS && loc.getY()-2 >= 0) {
            moves.add(new Location(loc.getX()+1, loc.getY()-2));
        }
        if (loc.getX()-1 >= 0 && loc.getY()-2 >= 0) {
            moves.add(new Location(loc.getX()-1, loc.getY()-2));
        }

        return moves;
    }

    @Override
    public Object clone() {
        return new Knight(this.loc);
    }
}
