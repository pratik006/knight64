package com.prapps.chess;

import java.util.List;

public abstract class Piece {
    protected Location loc;

    public Piece(Location loc) {
        this.loc = loc;
    }

    public abstract List<Location> moves();

    public void move(Location loc) {
        this.loc = loc;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public abstract Object clone();
}
