package com.example.four_x_four_tic_tac_toe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.*;

import static com.example.four_x_four_tic_tac_toe.Movement.symulateMoves;


public class Controller {

    @FXML
    private Button Button_1, Button_2, Button_3, Button_4, Button_5, Button_6, Button_7, Button_8, Button_9, Button_10, Button_11, Button_12, Button_13, Button_14, Button_15, Button_16;
    @FXML
    private Button reset;
    @FXML
    private Button music;
    @FXML
    private Button startingPlayer;

    public static MediaPlayer mediaPlayer;
    public static Discography discography = new Discography(mediaPlayer);

    public static int[][] board = new int[4][4];

    public static char[][] boardPlayer = new char[4][4];

    public static Button[][] buttonsPlayer;

    public static boolean playerMove = true;
    public static boolean isGameStarted = false;

    public static final AI ai = new AI(board, playerMove);

    Movement movement = new Movement();


    @FXML
    private void initialize() {


        reset.setOnAction(param -> {
            discography.resetSound();
            reset();
        });


        startingPlayer.setOnAction(event -> {
            choosePlayer();
        });


        buttonsPlayer = new Button[][]{{Button_1, Button_2, Button_3, Button_4},
                {Button_5, Button_6, Button_7, Button_8},
                {Button_9, Button_10, Button_11, Button_12},
                {Button_13, Button_14, Button_15, Button_16}};

        for (int i = 0; i < buttonsPlayer.length; i++) {
            for (int j = 0; j < buttonsPlayer[i].length; j++) {
                Button button = buttonsPlayer[i][j];
                int x = i; // numer wiersza
                int y = j; // numer kolumny
                button.setOnAction(param -> {
                    if (playerMove) {
                        symulateMoves(button, x, y);
                        if (ai.isGameOver()) {
                            discography.loosingPlayer();

                            button.getStyleClass().remove("button-pressed");
                            button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");

                            FailureInfoPlayer();

                            reset();

                            playerMove = false;
                        }
                        printBoard();
                    }
                });
            }
        }


        music.setOnAction(e -> {
            discography.music(music);
        });
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println("|");
        }
        for (int col = 0; col < board[0].length; col++) {
            System.out.print("_______");
        }
        System.out.println();
    }

    public Alert FailureInfoPlayer() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER!");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("TRY NEXT TIME");
        alert.showAndWait();

        return alert;
    }


    public void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
        Button button = new Button();
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
        }

        System.out.println("RESET");
        System.out.print("____________________________\n");
    }


    public void choosePlayer() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Starting player");
        alert.setHeaderText("Choose starting player:");
        ButtonType playerButton = new ButtonType("Player");
        ButtonType computerButton = new ButtonType("Computer");
        alert.getButtonTypes().setAll(playerButton, computerButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == playerButton) {
                playerMove = true;
            } else if (result.get() == computerButton) {
                playerMove = false;
                movement.computerMove(board);
                printBoard();
                playerMove = true;
            }
            isGameStarted = true;
        }
    }


}