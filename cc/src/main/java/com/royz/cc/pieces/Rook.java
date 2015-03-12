package com.royz.cc.pieces;

public class Rook extends Piece {

    @Override
    public int getWeight() {
        return WEIGHT_ROOK;
    }

    @Override
    public boolean occupyCells(int[][] cells, int row, int col) {
        boolean noThreat = true;

        for (int i = 1; i < cells[row].length; i++) {
            if (cells[row][i] == 1) {
                noThreat = false;
            } else {
                cells[row][i] = -1;
            }
        }

        for (int i = 1; i < cells.length; i++) {
            if (cells[i][col] == 1) {
                noThreat = false;
            } else {
                cells[i][col] = -1;
            }
        }
        cells[row][col] = 1;
        return noThreat;
    }

    @Override
    public String toString() {
        return "R";
    }
}
