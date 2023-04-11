package com.example.four_x_four_tic_tac_toe;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Discography {

    public MediaPlayer mediaPlayer1;


    public Discography(MediaPlayer mediaPlayer1){
        this.mediaPlayer1 = mediaPlayer1;
    }



    public MediaPlayer SoundPlayer1(){
        String path = getClass().getResource("plum.mp3").toString();
        Media play = new Media(path);
        mediaPlayer1 = new MediaPlayer(play);
        mediaPlayer1.play();

        return mediaPlayer1;
    }

    public MediaPlayer SoundPlayer2(){
        String path = getClass().getResource("beep.mp3").toString();
        Media play = new Media(path);
        mediaPlayer1 = new MediaPlayer(play);
        mediaPlayer1.play();

        return mediaPlayer1;
    }

    public MediaPlayer LoosingPlayer1(){
        String pathLosing1 = getClass().getResource("GameOver.mp3").toString();
        Media playLosing1 = new Media(pathLosing1);
        mediaPlayer1 = new MediaPlayer(playLosing1);
        mediaPlayer1.play();

        return mediaPlayer1;
    }

    public MediaPlayer LoosingPlayer2(){
        String pathLosing2 = getClass().getResource("GameOver.mp3").toString();
        Media playLosing2 = new Media(pathLosing2);
        mediaPlayer1 = new MediaPlayer(playLosing2);
        mediaPlayer1.play();

        return mediaPlayer1;
    }


}
