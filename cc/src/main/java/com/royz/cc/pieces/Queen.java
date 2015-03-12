package com.royz.cc.pieces;

public class Queen extends Piece {

    @Override
    public int getWeight() {
        return WEIGHT_QUEEN;
    }

    @Override
    public boolean occupyCells(int[][] cells, int row, int col) {
        boolean noThreat = true;

        /* iterate over columns */
        for (int i = 1; i < cells[row].length; i++) {
            if (cells[row][i] == 1) {
                noThreat = false;
            } else {
                cells[row][i] = -1;
            }
        }

        /* iterate over rows */
        for (int i = 1; i < cells.length; i++) {
            if (cells[i][col] == 1) {
                noThreat = false;
            } else {
                cells[i][col] = -1;
            }
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
