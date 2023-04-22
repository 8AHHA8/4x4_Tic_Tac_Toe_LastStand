package com.example.four_x_four_tic_tac_toe;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Discography {

    public MediaPlayer mediaPlayer;

    Media musicTheme;


    public Discography(MediaPlayer mediaPlayer1){
        this.mediaPlayer = mediaPlayer1;
    }

    public MediaPlayer soundPlayer1(){
        String path = getClass().getResource("plum.mp3").toString();
        Media play = new Media(path);
        mediaPlayer = new MediaPlayer(play);
        mediaPlayer.play();

        return mediaPlayer;
    }

    public MediaPlayer soundPlayer2(){
        String path = getClass().getResource("beep.mp3").toString();
        Media play = new Media(path);
        mediaPlayer = new MediaPlayer(play);
        mediaPlayer.play();

        return mediaPlayer;
    }

    public MediaPlayer loosingPlayer(){
        String pathLosing1 = getClass().getResource("GameOver.mp3").toString();
        Media playLosing1 = new Media(pathLosing1);
        mediaPlayer = new MediaPlayer(playLosing1);
        mediaPlayer.play();

        return mediaPlayer;
    }

    public MediaPlayer music(Button button) {
        String path = getClass().getResource("ambient.mp3").toString();
        musicTheme = new Media(path);

        if (mediaPlayer == null || !mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            button.getStyleClass().add("button-pressed");
            button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #0018ff, #000000);");

            mediaPlayer = new MediaPlayer((musicTheme));
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            System.out.println("MUSIC START");
            System.out.print("____________________________\n");

        } else {
            button.getStyleClass().remove("button-pressed");
            button.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 95%, #ca01e6, #000000);");

            mediaPlayer.stop();

            System.out.println("MUSIC STOP");
            System.out.print("____________________________\n");
        }
        return mediaPlayer;
    }

    public MediaPlayer resetSound(){
        Button button = new Button();
        button.getStyleClass().add("button-pressed");
        String path = getClass().getResource("reset.mp3").toString();
        Media play = new Media(path);
        mediaPlayer = new MediaPlayer(play);
        mediaPlayer.play();

        return mediaPlayer;
    }


}
