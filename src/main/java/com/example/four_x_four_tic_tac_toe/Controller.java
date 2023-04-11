package com.example.four_x_four_tic_tac_toe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;

import java.io.IOException;
import java.security.PublicKey;
import java.util.*;


public class Controller {

    @FXML
    private Button Button_1, Button_2, Button_3, Button_4, Button_5, Button_6, Button_7, Button_8, Button_9, Button_10, Button_11, Button_12, Button_13, Button_14, Button_15, Button_16;
    @FXML
    private Button reset;
    @FXML
    private Button Music;

    public static MediaPlayer mediaPlayer1;

    public static Discography discography = new Discography(mediaPlayer1);

    Media musicTheme;

    int clickCounter1 = 0;

    public int[][] board = new int[4][4];

    public char[][] board_player = new char[4][4];

    Button[] buttons_player;

    AI ai;

    @FXML
    private void initialize() {


        reset.setOnAction(param -> {
            Button button = new Button();
            button.getStyleClass().add("button-pressed");
            String path = getClass().getResource("reset.mp3").toString();
            Media play = new Media(path);
            mediaPlayer1 = new MediaPlayer(play);
            mediaPlayer1.play();

            Pane pane = (Pane) reset.getParent();
            pane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Board.fxml"));
            try {
                pane.getChildren().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!button.getStyleClass().contains("button-pressed")) {
                button.getStyleClass().remove("button-pressed");
                button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");
                mediaPlayer1.stop();
            }

            System.out.println("RESET");
            System.out.print("____________________________\n");
        });


        buttons_player = new Button[]{Button_1, Button_2, Button_3, Button_4, Button_5, Button_6, Button_7, Button_8, Button_9, Button_10, Button_11, Button_12, Button_13, Button_14, Button_15, Button_16};
        for (int i = 0; i < buttons_player.length; i++) {
            Button button = buttons_player[i];
            int x = i / 4; // numer wiersza
            int y = i % 4; // numer kolumny
            button.setOnAction(param -> {
                if (!button.getStyleClass().contains("button-pressed")) {
                    clickCounter1++;
                    if (clickCounter1 % 2 == 1) {
                        button.getStyleClass().add("button-pressed");
                        button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #37e8ff, #000000); -fx-font-size: 60px; -fx-text-fill: white;");
                        button.setText("X");
                        board[x][y] = 1; // przypisanie wartości pola planszy
                        board_player[x][y] = '1';

                        discography.SoundPlayer1();

                        ai = new AI(board);

                        if (ai.isGameOver() && clickCounter1 % 2 == 1) {
                            discography.LoosingPlayer2();

                            button.getStyleClass().remove("button-pressed");
                            button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");

                            FailureInfoPlayer1();

                            if (mediaPlayer1 == null || mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                                System.out.print("____________________________\n");
                                System.out.println("MUSIC IS CURRENTLY NOT PLAYING");
                                System.out.print("____________________________\n");
                            } else {
                                mediaPlayer1.stop();
                                System.out.println("MUSIC STOP");
                                System.out.print("____________________________\n");
                            }

                            RESET();
                        }
                        printBoard();
                        System.out.println(board[x][y] + " " + x + " " + y);





                        performComputerMove(board);
                        clickCounter1++;


                        ai = new AI(board);

                        if (ai.isGameOver() && clickCounter1 % 2 == 0) {
                            discography.LoosingPlayer2();

                            button.getStyleClass().remove("button-pressed");
                            button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");

                            FailureInfoPlayer2();

                            if (mediaPlayer1 == null || mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                                System.out.print("____________________________\n");
                                System.out.println("MUSIC IS CURRENTLY NOT PLAYING");
                                System.out.print("____________________________\n");
                            } else {
                                mediaPlayer1.stop();
                                System.out.println("MUSIC STOP");
                                System.out.print("____________________________\n");
                            }

                            RESET();
                        }
                        printBoard();
                        System.out.println(board[x][y] + " " + x + " " + y);
                    }
                }
            });
        }


        String path = getClass().getResource("ambient.mp3").toString();
        musicTheme = new Media(path);

        Music.setOnAction(event -> {
            Button button = (Button) event.getSource();
            if (mediaPlayer1 == null || !mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                button.getStyleClass().add("button-pressed");
                button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #0018ff, #000000);");

                mediaPlayer1 = new MediaPlayer((musicTheme));
                mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer1.play();

                System.out.println("MUSIC START");
                System.out.print("____________________________\n");

            } else {
                button.getStyleClass().remove("button-pressed");
                button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");

                mediaPlayer1.stop();

                System.out.println("MUSIC STOP");
                System.out.print("____________________________\n");
            }
        });
    }

    public int[][] performComputerMove(int[][] board) {
        int[] bestMove = ai.findBestMove(board);
        int row = bestMove[0];
        int col = bestMove[1];
        board[row][col] = 1;
        board_player[row][col] = '1';

        // Zaznacz pole planszy, na którym wykonano ruch drugiego gracza
        Button button = buttons_player[row * 4 + col];
        button.setText("X");
        button.getStyleClass().add("button-pressed");
        button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ff1f1f, #000000); -fx-font-size: 60px; -fx-text-fill: white;");

        return board;
    }

    private void printBoard() {
        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println("|");
        }
        for (int col = 0; col < board[0].length; col++) {
            System.out.print("_______");
        }
        System.out.println();
    }

    private Alert FailureInfoPlayer1() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PLAYER 1 HAS LOST!");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("TRY NEXT TIME");
        alert.showAndWait();

        return alert;
    }

    private Alert FailureInfoPlayer2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("PLAYER 2 HAS LOST!");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("TRY NEXT TIME");
        alert.showAndWait();

        return alert;
    }

    private void RESET() {
        Pane pane = (Pane) reset.getParent();
        pane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Board.fxml"));
        try {
            pane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    Button[] buttons_player = {Button_1, Button_2, Button_3, Button_4, Button_5, Button_6, Button_7, Button_8, Button_9, Button_10, Button_11, Button_12, Button_13, Button_14, Button_15, Button_16};
//        for (int i = 0; i < buttons_player.length; i++) {
//        Button button = buttons_player[i];
//        int x = i / 4; // numer wiersza
//        int y = i % 4; // numer kolumny
//        button.setOnAction(param -> {
//            if (!button.getStyleClass().contains("button-pressed")) {
//                clickCounter1++;
//                if (clickCounter1 % 2 == 1) {
//
//                    button.getStyleClass().add("button-pressed");
//                    button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #37e8ff, #000000); -fx-font-size: 60px; -fx-text-fill: white;");
//
//                    button.setText("X");
//                    board[x][y] = 1; // przypisanie wartości pola planszy
//                    board_player[x][y] = '1';
//
//                    discography.SoundPlayer1();
//
//
//                    ai = new AI(board);
//
//                    if (ai.isGameOver()) {
//                        discography.LoosingPlayer1();
//
//                        RESET();
//
//                        button.getStyleClass().remove("button-pressed");
//                        button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");
//
//                        if (mediaPlayer1 == null || mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
//                            System.out.print("____________________________\n");
//                            System.out.println("MUSIC IS CURRENTLY NOT PLAYING");
//                            System.out.print("____________________________\n");
//                        } else {
//                            mediaPlayer1.stop();
//                            System.out.println("MUSIC STOP");
//                            System.out.print("____________________________\n");
//                        }
//
//                        FailureInfoPlayer1();
//                    }
//                    printBoard();
//                    System.out.println(board[x][y] + " " + x + " " + y + " " + Arrays.deepToString(board));
//                } else {
//
//                    int[] bestMove = ai.findBestMove(board);
//
//                    System.out.println(Arrays.toString(bestMove));
//
//
//                    int row = bestMove[0];
//                    int col = bestMove[1];
//
//
//                    board[row][col] = 1; // przypisanie wartości pola planszy
//                    board_player[row][col] = '1';
//
////                        board[x][y] = 1; // przypisanie wartości pola planszy
////                        board_player[x][y] = '1';
//
//                    button.setText("X");
//                    button.getStyleClass().add("button-pressed");
//                    button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ff1f1f, #000000); -fx-font-size: 60px; -fx-text-fill: white;");
//
//
//                    discography.SoundPlayer2();
//
//                    ai = new AI(board);
//
//                    if (ai.isGameOver()) {
//                        discography.LoosingPlayer2();
//
//                        button.getStyleClass().remove("button-pressed");
//                        button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");
//
//                        FailureInfoPlayer2();
//
//                        if (mediaPlayer1 == null || mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
//                            System.out.print("____________________________\n");
//                            System.out.println("MUSIC IS CURRENTLY NOT PLAYING");
//                            System.out.print("____________________________\n");
//                        } else {
//                            mediaPlayer1.stop();
//                            System.out.println("MUSIC STOP");
//                            System.out.print("____________________________\n");
//                        }
//
//                        RESET();
//                    }
//                    printBoard();
//                    System.out.println(board[row][col] + " " + row + " " + col + " " + Arrays.toString(bestMove));
//
////                        System.out.println(board[x][y] + " " + x + " " + y + " " + Arrays.deepToString(board));
//                }
//            }
//        });
//    }


}