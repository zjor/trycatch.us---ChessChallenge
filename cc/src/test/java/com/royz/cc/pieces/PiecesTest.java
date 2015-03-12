package com.royz.cc.pieces;

import org.junit.Before;
import org.junit.Test;

public class PiecesTest {

    private int[][] cells;

    @Before
    public void setUp() {
        cells = new int[9][9];
    }

    @Test
    public void shouldOccupyCenter() {
        King k = new King();
        k.occupyCells(cells, 2, 2);
        printCells(cells);
    }

    @Test
    public void shouldOccupyCorner() {
        King k = new King();
        k.occupyCells(cells, 1, 1);
        printCells(cells);
    }

    @Test
    public void shouldOccupyCornerAndLower() {
        King k = new King();
        k.occupyCells(cells, 3, 3);
        Piece.occupyLowerCells(cells, 3, 3);

        printCells(cells);
    }

    @Test
    public void rookShouldOccupyCenter() {
        Rook r = new Rook();
        r.occupyCells(cells, 4, 4);
        printCells(cells);
    }

    @Test
    public void shouldOccupyLower() {
        Piece.occupyLowerCells(cells, 3, 3);
        printCells(cells);
    }


    private void printCells(int[][] cells) {
        StringBuilder line = new StringBuilder();
        for (int i = 1; i < cells.length - 1; i++) {
            line.setLength(0);
            for (int j = 1; j < cells[i].length - 1; j++) {
                switch (cells[i][j]) {
                    case 0: line.append('.'); break;
                    case 1: line.append('o'); break;
                    case -1: line.append('x'); break;
                }
            }
            System.out.println(line);
        }
    }

}
