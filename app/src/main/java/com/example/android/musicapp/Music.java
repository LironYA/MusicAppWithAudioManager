package com.example.android.musicapp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Music {

    private String title;
    private String artist;
    private int song;

    public Music(String songArtist, String songTitle, int songMusic) {
        artist = songArtist;
        title = songTitle;
        song = songMusic;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getSong() {
        return song;
    }
}
