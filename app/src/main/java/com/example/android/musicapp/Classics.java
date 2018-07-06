package com.example.android.musicapp;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Classics extends AppCompatActivity {
    int position_player = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_activity);
        final Button playButton = (Button) findViewById(R.id.play);
        final Button pauseButton = (Button) findViewById(R.id.pause);
        final Button stopButton = (Button) findViewById(R.id.stop);
        final TextView song_text = findViewById(R.id.song_name);

        final ArrayList<Music> musicLibrary = new ArrayList<Music>();
        musicLibrary.add(new Music("Emma Shapplin", "Carmine Meo", R.raw.emmashapplin));
        musicLibrary.add(new Music("Queen", "Bohemian Rhapsody", R.raw.queen));
        musicLibrary.add(new Music("The Verve", "Bitter Sweet Symphony", R.raw.verve));

        MusicAdapter adapter = new MusicAdapter(this, musicLibrary);
        final MediaPlayer mediaPlayer1 = MediaPlayer.create(this, R.raw.emmashapplin);
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.queen);
        final MediaPlayer mediaPlayer3 = MediaPlayer.create(this, R.raw.verve);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position == 0) {
                    mediaPlayer1.start();
                    song_text.setText(getResources().getString(R.string.emma));
                    Toast.makeText(Classics.this, (getResources().getString(R.string.emma_playing)), Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    mediaPlayer2.start();
                    song_text.setText(getResources().getString(R.string.queen));
                    Toast.makeText(Classics.this, (getResources().getString(R.string.queen_playing)), Toast.LENGTH_SHORT).show();
                }
                if (mediaPlayer3 != null && position == 2) {
                    mediaPlayer3.start();
                    song_text.setText(getResources().getString(R.string.verve));
                    Toast.makeText(Classics.this, (getResources().getString(R.string.verve)), Toast.LENGTH_SHORT).show();
                }
                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == 0) {
                            mediaPlayer1.start();
                        } else if (position == 1) {
                            mediaPlayer2.start();
                        } else if (position == 2) {
                            mediaPlayer3.start();
                        }
                    }
                });
                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position == 0) {
                            mediaPlayer1.pause();
                        } else if (position == 1) {
                            mediaPlayer2.pause();
                        } else if (position == 2) {
                            mediaPlayer3.pause();
                        }
                    }
                });
                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        assert mediaPlayer1 != null;
                        assert mediaPlayer2 != null;
                        assert mediaPlayer3 != null;
                        if (mediaPlayer1.isPlaying() || mediaPlayer2.isPlaying() || mediaPlayer3.isPlaying()) {
                            mediaPlayer1.stop();
                            mediaPlayer2.stop();
                            mediaPlayer3.stop();
                            Classics.this.finish();
                            Intent myIntent = new Intent(Classics.this, Classics.class);
                            Classics.this.startActivity(myIntent);
                        } else {
                            Classics.this.finish();
                        }
                    }
                });
            }
        });
    }
}

