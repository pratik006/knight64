package com.prapps.chess.knight;

import com.prapps.chess.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Knight64 {
    static int maxThreads = 4;
    static Integer activeThreads = 0;

    public static void main(String[] args) {
        Piece piece = new Knight(new Location(0,0));
        Board board = new Board(Arrays.asList(piece));
        board.move(new Location(0,0), new Location(2,1));

        ExecutorService service = Executors.newFixedThreadPool(maxThreads);
        List<Future> resp = new ArrayList<>();
        board.moves().stream().filter(m -> !board.getMoveOrder().contains(m)).forEach(m -> {
            Board newboard = (Board) board.clone();
            newboard.move(m);
            System.out.println(newboard.hashCode()+" --- "+newboard.getMoveOrder());
            resp.add(service.submit(new AnalysisTree(newboard)));
        });
        resp.forEach(r -> {
            try {
                System.out.println("Ans: "+r.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
