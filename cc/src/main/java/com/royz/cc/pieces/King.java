package com.royz.cc.pieces;

public class King extends Piece {

    private static final int[][] offsets = new int[][]{
            {0, 0}, {0, 1}, {0, -1},
            {1, 0}, {1, 1}, {1, -1},
            {-1, 0}, {-1, 1}, {-1, -1}
    };

    @Override
    public int getWeight() {
        return WEIGHT_KING;
    }

    @Override
    public boolean occupyCells(int[][] cells, int row, int col) {
        boolean noThreat = true;
        for (int i = 0; i < offsets.length; i++) {
            int r = row + offsets[i][0];
            int c = col + offsets[i][1];
            if (cells[r][c] == 1) {
                noThreat = false;
            } else {
                cells[r][c] = -1;
            }
        }
        cells[row][col] = 1;
        return noThreat;
    }

    @Override
    public String toString() {
        return "K";
    }
}
