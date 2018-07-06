package com.example.android.musicapp;

import android.content.Context;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
//All song and pictures rights are reserved to Disney
public class DisneyActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    public AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mediaPlayer.pause();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                        mediaPlayer.pause();
                    }
                }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_activity);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final Button playButton = (Button) findViewById(R.id.play);
        final Button pauseButton = (Button) findViewById(R.id.pause);
        final Button stopButton = (Button) findViewById(R.id.stop);
        final TextView song_text = findViewById(R.id.song_name);
        final ImageView picture = (ImageView) findViewById(R.id.picture);
        // Array list
        final ArrayList<Music> musicLibrary = new ArrayList<Music>();
        musicLibrary.add(new Music("Disney - Moana", "How far I'll go", R.raw.moana));
        musicLibrary.add(new Music("Disney - The little mermaid", "Part of your world", R.raw.mermaid));
        musicLibrary.add(new Music("Disney - Lion King", "Circle Of Life", R.raw.lionking));

        MusicAdapter adapter = new MusicAdapter(this, musicLibrary);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                releaseMediaPlayer();
                Music music = musicLibrary.get(position);
                // Request audio focus so in order to play the audio file. The app needs to play a
                // long audio file, so we will request audio focus with a long amount of time
                // with AUDIOFOCUS_GAIN.
                // https://developer.android.com/guide/topics/media-apps/audio-focus
                int result = mAudioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(DisneyActivity.this, music.getSong());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                    Log.e("media", "result" + result);
                }

                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.start();
                    }
                });
                pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.pause();
                    }
                });
                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        releaseMediaPlayer();
                    }
                });

            }
        });
    }
    @Override
    protected void onStop () {
        super.onStop();
        releaseMediaPlayer();
    }
        private void releaseMediaPlayer() {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
                mAudioManager.abandonAudioFocus(afChangeListener);
            }
}}
