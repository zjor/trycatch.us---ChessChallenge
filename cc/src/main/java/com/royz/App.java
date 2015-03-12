package com.royz;

import com.royz.cc.Solver;
import com.royz.cc.pieces.Bishop;
import com.royz.cc.pieces.King;
import com.royz.cc.pieces.Knight;
import com.royz.cc.pieces.Piece;
import com.royz.cc.pieces.Queen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private static void solveEightQueensProblem() {
        List<Piece> queens = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            queens.add(new Queen());
        }
        Solver solver = new Solver(queens, 8, 8);
        Solver.SilentSolutionCallbackImpl callback = new Solver.SilentSolutionCallbackImpl();
        solver.solve(callback);
        System.out.println("Configurations count: " + callback.getCount());
        System.out.println("Elapsed time: " + callback.getTime() + "ms");
    }

    private static void solveAssessmentProblem() {
        List<Piece> pieces = Arrays.asList(new King(), new King(), new Queen(), new Queen(), new Bishop(), new Bishop(), new Knight());
        Solver solver = new Solver(pieces, 7, 7);

        Solver.SilentSolutionCallbackImpl callback = new Solver.SilentSolutionCallbackImpl();
        solver.solve(callback);
        System.out.println("Configurations count: " + callback.getCount());
        System.out.println("Elapsed time: " + callback.getTime() + "ms");
    }



    /*
    Final answer is:
        Configurations count: 3063828
        Elapsed time: 16556ms
     */
    public static void main(String[] args) {
//        solveEightQueensProblem();
        solveAssessmentProblem();


    }
}
