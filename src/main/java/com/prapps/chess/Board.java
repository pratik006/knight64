package com.prapps.chess;

import java.util.*;
import java.util.stream.Collectors;

public final class Board implements Cloneable {
    public static final int RANKS = 8;
    public static final int FILES = 8;

    short[] squares = new short[64];
    HashMap<Location, Piece> piecesMap = new HashMap(32);
    private LinkedList<Location> moveOrder = new LinkedList<>();

    public Board(HashMap<Location, Piece> piecesMap, LinkedList<Location> moves) {
        this.piecesMap = piecesMap;
        this.moveOrder = moves;
    }

    public Board(List<Piece> pieces) {
        pieces.forEach(p -> piecesMap.put(p.getLoc(), p));
        moveOrder.add(new Location(0,0));
    }

    public void move(Location target) {
        Piece piece = piecesMap.remove(moveOrder.peekFirst());
        piece.setLoc(target);
        piecesMap.put(target, piece);
        moveOrder.push(target);
    }

    public void move(Location src, Location target) {
        Piece piece = piecesMap.remove(src);
        piece.setLoc(target);
        piecesMap.put(target, piece);
        moveOrder.push(target);
    }

    public void undoMove() {
        Piece piece = piecesMap.remove(moveOrder.peekFirst());
        moveOrder.pop();
        piecesMap.put(moveOrder.peekFirst(), piece);
    }

    public List<Location> moves() {
        return piecesMap.values().stream().flatMap(p -> p.moves().stream()).collect(Collectors.toList());
    }

    public Object clone() {
        return new Board((HashMap<Location, Piece>) piecesMap.clone(), (LinkedList<Location>) moveOrder.clone());
    }

    public LinkedList<Location> getMoveOrder() {
        return moveOrder;
    }
}
