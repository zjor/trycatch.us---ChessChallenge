package com.royz.cc.pieces;

public class Bishop extends Piece {

    @Override
    public int getWeight() {
        return WEIGHT_BISHOP;
    }

    @Override
    public boolean occupyCells(int[][] cells, int row, int col) {
        boolean noThreat = true;

        /* iterate over rows */
        for (int i = 1; i < cells.length; i++) {
            int d = row - i;
            int colLo = col - d;
            int colHi = col + d;
            if (colLo >= 0 && colLo < cells[i].length) {
                if (cells[i][colLo] == 1) {
                    noThreat = false;
                } else {
                    cells[i][colLo] = -1;
                }
            }

            if (colHi >= 0 && colHi < cells[i].length) {
                if (cells[i][colHi] == 1) {
                    noThreat = false;
                } else {
                    cells[i][colHi] = -1;
                }
            }
        }
        cells[row][col] = 1;
        return noThreat;
    }
}
