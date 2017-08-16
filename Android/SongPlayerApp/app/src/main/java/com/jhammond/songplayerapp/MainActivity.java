package com.jhammond.songplayerapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playSong1(View v) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.song1);
        if (!mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void playSong2(View v) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.song2);
        if (!mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

}
