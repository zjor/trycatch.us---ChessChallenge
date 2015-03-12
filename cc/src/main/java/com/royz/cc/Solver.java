package com.royz.cc;

import com.royz.cc.pieces.King;
import com.royz.cc.pieces.Piece;
import com.royz.cc.pieces.Rook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {

    private int rows, cols;
    private List<Pair<Piece, Position>> state;

    public Solver(List<Piece> pieces, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        state = init(pieces);
    }

    private List<Pair<Piece, Position>> init(List<Piece> pieces) {

        List<Pair<Piece, Position>> initialState = new ArrayList<>();

        for (Piece p : pieces) {
            initialState.add(new Pair<>(p, null));
        }

        Collections.sort(initialState, new Comparator<Pair<Piece, Position>>() {
            @Override
            public int compare(Pair<Piece, Position> o1, Pair<Piece, Position> o2) {
                return o1.getA().compareTo(o2.getA());
            }
        });

        return initialState;
    }

    private boolean evolve() {
        int[][] cells = new int[rows + 2][cols + 2];

        int frontierIndex = 0;
        while (frontierIndex < state.size() && state.get(frontierIndex).getB() != null) {
            Piece piece = state.get(frontierIndex).getA();
            Position pos = state.get(frontierIndex).getB();
            piece.occupyCells(cells, pos.getRow(), pos.getCol());
            frontierIndex++;
        }

        if (frontierIndex == state.size()) {
            return false;
        }

        Piece currentPiece = state.get(frontierIndex).getA();

        if (frontierIndex > 0 && currentPiece.getWeight() == state.get(frontierIndex - 1).getA().getWeight()) {
            Position pos = state.get(frontierIndex - 1).getB();
            Piece.occupyLowerCells(cells, pos.getRow(), pos.getCol());
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (cells[i][j] == 0 && allowed(cells, currentPiece, i, j)) {
                    state.get(frontierIndex).setB(new Position(i, j));
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Tries to apply a piece to the current configuration
     *
     * @param cells
     * @param piece
     * @param row
     * @param col
     * @return
     */
    private boolean allowed(int[][] cells, Piece piece, int row, int col) {
        int[][] playground = new int[rows + 2][cols + 2];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                playground[i][j] = cells[i][j];
            }
        }
        return piece.occupyCells(playground, row, col);
    }

    private boolean isCompleteState() {
        return state.get(state.size() - 1).getB() != null;
    }

    private boolean backtrack() {
        int[][] cells = new int[rows + 2][cols + 2];
        int lastPieceIndex = 0;
        while (lastPieceIndex < state.size() && state.get(lastPieceIndex).getB() != null) {
            lastPieceIndex++;
        }
        lastPieceIndex--;
        Piece lastPiece = state.get(lastPieceIndex).getA();
        Position lastPiecePosition = state.get(lastPieceIndex).getB();
        state.get(lastPieceIndex).setB(null);

        for (int i = 0; i < lastPieceIndex; i++) {
            Position p = state.get(i).getB();
            state.get(i).getA().occupyCells(cells, p.getRow(), p.getCol());
        }
        Piece.occupyLowerCells(cells, lastPiecePosition.getRow(), lastPiecePosition.getCol());

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (cells[i][j] == 0 && allowed(cells, lastPiece, i, j)) {
                    state.get(lastPieceIndex).setB(new Position(i, j));
                    return true;
                }
            }
        }

        return false;

    }

    private boolean isExhausted() {
        Position position = state.get(0).getB();
        return position != null && position.getRow() == rows && position.getCol() == cols;
    }

    public void solve(SolutionCallback callback) {
        while (!isExhausted()) {
            while (evolve()) ;
            if (isCompleteState()) {
                callback.onSolved(state);
            }

            while (!backtrack() && !isExhausted());
        }
        callback.onFinished();
    }


    public static void main(String[] args) {
        List<Piece> pieces = new LinkedList<>();
        pieces.add(new Rook());
        pieces.add(new King());
        pieces.add(new King());
        Solver solver = new Solver(pieces, 3, 3);
        solver.solve(new SolutionCallbackImpl(3, 3));
    }

    public static interface SolutionCallback {

        void onSolved(List<Pair<Piece, Position>> solution);

        void onFinished();
    }

    public static class SolutionCallbackImpl implements SolutionCallback {

        private int rows, cols;

        private long time;
        private long count;

        public SolutionCallbackImpl(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            time = System.currentTimeMillis();
        }

        @Override
        public void onSolved(List<Pair<Piece, Position>> solution) {
            count++;
            printSolution(solution);
        }

        @Override
        public void onFinished() {
            System.out.println("Number of configurations: " + count);
            System.out.println("Elapsed time: " + (System.currentTimeMillis() - time) + "ms");
        }

        private void printSolution(List<Pair<Piece, Position>> solution) {
            char[][] board = new char[rows][cols];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = '.';
                }
            }
            for (Pair<Piece, Position> p: solution) {
                board[p.getB().getRow() - 1][p.getB().getCol() - 1] = p.getA().toString().charAt(0);
            }

            StringBuilder line = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                line.setLength(0);
                for (int j = 0; j < board[i].length; j++) {
                    line.append(board[i][j]);
                }
                System.out.println(line);
            }
            System.out.println();

        }
    }

}
