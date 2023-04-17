package com.example.four_x_four_tic_tac_toe;

import javafx.scene.control.Button;

import static com.example.four_x_four_tic_tac_toe.Controller.*;

public class Movement {

    public static void symulateMoves(Button button,  int x, int y) {
        if(isGameStarted) {
            playerMove(button, x, y);
            computerMove(board);
        }
    }

    public static void playerMove(Button button, int x, int y) {
        if (!button.getStyleClass().contains("button-pressed")) { //sprawdzenie czy gra została już rozpoczęta(wybór pierwszego gracza) oraz czy dane pole na planszy nie zostało już zajęte
            board[x][y] = 1; // przypisanie znaku '1' do pola planszy
            boardPlayer[x][y] = '1';
            styleButtonAfterPlayerMove(button);
        }
    }

    public static int[][] computerMove(int[][] board) {
        int[] bestMove = ai.findBestMove(board);
        int row = bestMove[0];
        int col = bestMove[1];
        board[row][col] = 1;
        boardPlayer[row][col] = '1';

        Button button = buttonsPlayer[row][col];
        styleButtonAfterComputerMove(button);

        return board;
    }

    public static void styleButtonAfterPlayerMove(Button button) {
        button.getStyleClass().add("button-pressed");
        button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #37e8ff, #000000); -fx-font-size: 60px; -fx-text-fill: white;");
        button.setText("X");
    }

    public static void styleButtonAfterComputerMove(Button button) {
        button.setText("X");
        button.getStyleClass().add("button-pressed");
        button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ff1f1f, #000000); -fx-font-size: 60px; -fx-text-fill: white;");
    }


}
