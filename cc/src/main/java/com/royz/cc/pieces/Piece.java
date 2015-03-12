package com.royz.cc.pieces;

public abstract class Piece implements Comparable<Piece>{

    public static final int WEIGHT_KING = 1;
    public static final int WEIGHT_QUEEN = 2;
    public static final int WEIGHT_ROOK = 3;
    public static final int WEIGHT_BISHOP = 4;
    public static final int WEIGHT_KNIGHT = 5;

    /**
     * @return weight of the piece for sorting
     */
    public abstract int getWeight();

    /**
     * Sets false to the cell which this piece is threatening
     * @param cells
     * @param row
     * @param col
     * @return true if no existing piece on the board is threatened
     */
    public abstract boolean occupyCells(int[][] cells, int row, int col);

    /**
     * Cells with less row and (same row && less col) will be occupied
     * @param cells
     * @param row
     * @param col
     */
    public static void occupyLowerCells(int[][] cells, int row, int col) {
        for (int i = 1; i<= row; i++) {
            for (int j = 1; j< cells[i].length; j++) {
                if ((i < row || (i == row && j <= col)) && cells[i][j] == 0) {
                    cells[i][j] = -1;
                }
            }
        }
    }

    @Override
    public int compareTo(Piece o) {
        return getWeight() - o.getWeight();
    }

}
