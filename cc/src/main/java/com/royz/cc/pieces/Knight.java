package com.royz.cc.pieces;

public class Knight extends Piece {
    private static final int[][] offsets = new int[][]{
            {0, 0}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {2, 1},
            {-1, 2}, {-1, -2}, {2, -1}
    };

    @Override
    public int getWeight() {
        return WEIGHT_KNIGHT;
    }

    @Override
    public boolean occupyCells(int[][] cells, int row, int col) {
        boolean noThreat = true;
        for (int[] o: offsets) {
            int r = row + o[0];
            int c = col + o[1];
            if (r >= 0 && r < cells.length &&
                    c >= 0 && c < cells[0].length) {

                if (cells[r][c] == 1) {
                    noThreat = false;
                } else {
                    cells[r][c] = -1;
                }
            }
        }
        cells[row][col] = 1;
        return noThreat;
    }

    @Override
    public String toString() {
        return "N";
    }
}
