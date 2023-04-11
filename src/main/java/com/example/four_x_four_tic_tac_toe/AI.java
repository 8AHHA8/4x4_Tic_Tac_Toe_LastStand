package com.example.four_x_four_tic_tac_toe;

import javafx.scene.Node;
import javafx.scene.input.Clipboard;

import java.math.BigInteger;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AI {


    int[][] board;

    public AI(int[][] board) {
        this.board = board;
    }

    public int CheckHorizontals() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    counter++;
                }
            }
            if (counter == 4) {
                break;
            } else {
                counter = 0;
            }
        }
        return counter;
    }

    public int CheckVerticals() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] == 1) {
                    counter++;
                }
            }
            if (counter == 4) {
                break;
            } else {
                counter = 0;
            }
        }
        return counter;
    }

    public int CheckDiagonalsLeft() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 1) {
                counter++;
            }
        }
        return counter;
    }

    public int CheckDiagonalsRight() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - i - 1] == 1) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isGameOver() {
        boolean checker;
        if (CheckHorizontals() == 4 || CheckVerticals() == 4 || CheckDiagonalsLeft() == 4 || CheckDiagonalsRight() == 4) {
            checker = true;
        } else {
            checker = false;
        }
        return checker;
    }

    public int MinMax(int[][] board, int depth, int alpha, int beta, boolean MaximizingPlayer) {

        if (isGameOver() || depth == 0) {
            return evaluateBoard(board);//zwracam najlepszy możliwy wynik
        }

        if (MaximizingPlayer) {
            int maxInteger = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int playerOneMove = MinMax(board, depth - 1, alpha, beta, false);
                    maxInteger = max(maxInteger, playerOneMove);
                    alpha = max(alpha, playerOneMove);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return maxInteger;
        } else {
            int minInteger = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int playerTwoMove = MinMax(board, depth - 1, alpha, beta, true);
                    minInteger = min(minInteger, playerTwoMove);
                    beta = min(beta, playerTwoMove);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return minInteger;
        }
    }

    public int evaluateBoard(int[][] board) {
        int score = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) { //sprawdzam czy pole jest wolne
                    int localScore = 0;
                    boolean lineFound = false;

                        if (board[i][j] == 1) { //sprawdzam czy w wierszu już jest znak, czyli czy warto ten wiersz rozważać
                            lineFound = true;
                            break;
                        }

                    if (!lineFound) { //bazując na wyniku if'a przyznaję wartość
                        localScore++;
                    }

                    lineFound = false;


                        if (board[j][i] == 1) { //sprawdzam czy w kolumnie już jest znak, czyli czy warto tę kolumnę rozważać
                            lineFound = true;
                            break;
                        }


                    if (!lineFound) {//bazując na wyniku if'a przyznaję wartość
                        localScore++;
                    }

                    lineFound = false;

                        if (board[i][i] == 1) { //sprawdzam czy na lewym skosie już jest znak, czyli czy warto ten skos rozważać
                            lineFound = true;
                            break;
                        }


                    if (!lineFound) {//bazując na wyniku if'a przyznaję wartość
                        localScore++;
                    }

                    lineFound = false;


                        if (board[i][board.length - i - 1] == 1) { //sprawdzam czy na prawym skosie już jest znak, czyli czy warto ten skos rozważać
                            lineFound = true;
                            break;
                        }

                    if (!lineFound) {//bazując na wyniku if'a przyznaję wartość
                        localScore++;
                    }

                    score += localScore;//przypisuję wartość zliczonych punktów
                }
            }
        }
        return score;
    }

    public int[] findBestMove(int[][] board) {
        int[] bestMove = new int[2];
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;//symuluję ruch gracza
                    int meantimeVariable = MinMax(board, 6, Integer.MIN_VALUE, Integer.MAX_VALUE, false);//przypisuję maksymalną wartość
                    board[i][j] = 0;//cofam symulację ruchu
                    if (meantimeVariable > bestScore) {
                        bestScore = meantimeVariable;
                        bestMove[0] = i;//przypisuję współrzędne najlepszego ruchu
                        bestMove[1] = j;//przypisuję współrzędne najlepszego ruchu
                    }
                } else {
                    System.out.println("Field [" + i + "," + j + "] is taken.");
                }
            }
        }
        System.out.println();
        return bestMove;
    }
}

