package com.example.four_x_four_tic_tac_toe;

import javafx.scene.Node;
import javafx.scene.input.Clipboard;

import java.math.BigInteger;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AI {


    int[][] board;
    boolean playerMove;

    public AI(int[][] board, boolean playerMove) {
        this.board = board;
        this.playerMove = playerMove;
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

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
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
        // printBoard();
        boolean checker;
        if (CheckHorizontals() == 4 || CheckVerticals() == 4 || CheckDiagonalsLeft() == 4 || CheckDiagonalsRight() == 4) {
            checker = true;
        } else {
            checker = false;
        }
        return checker;
    }

    public int MinMax(int[][] board, int depth, int alpha, int beta, boolean MaximizingPlayer) {

        if (isGameOver()) {
            if (!playerMove) {
                return -100 + (16 - depth);
            } else {
                return 100 - (16 - depth);
            }
        }

        if (MaximizingPlayer) {
            int maxInteger = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        playerMove = !playerMove;
                        int playerOneMove = MinMax(board, depth - 1, alpha, beta, false);
                        board[i][j] = 0;
                        playerMove = !playerMove;
                        maxInteger = max(maxInteger, playerOneMove);
                        alpha = max(alpha, maxInteger);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return maxInteger;
        } else {
            int minInteger = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        playerMove = !playerMove;
                        int playerTwoMove = MinMax(board, depth - 1, alpha, beta, true);
                        board[i][j] = 0;
                        playerMove = !playerMove;
                        minInteger = min(minInteger, playerTwoMove);
                        beta = min(beta, minInteger);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return minInteger;
        }
    }

    public int[] findBestMove(int[][] board) {
        int[] bestMove = new int[2];
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;//symuluję ruch gracza
                    playerMove = !playerMove;
                    int meantimeVariable = MinMax(board, 16, Integer.MIN_VALUE, Integer.MAX_VALUE, false);//przypisuję maksymalną wartość
                    board[i][j] = 0;//cofam symulację ruchu
                    playerMove = !playerMove;
                    System.out.println(bestScore);
                    if (meantimeVariable > bestScore) {
                        bestScore = meantimeVariable;
                        bestMove[0] = i;//przypisuję współrzędne najlepszego ruchu
                        bestMove[1] = j;//przypisuję współrzędne najlepszego ruchu
                    }
                }
            }
        }
        System.out.println();
        return bestMove;
    }
}


//TODO: POPOPRAWIAĆ WARTOŚCI PLAYERMOVE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
