package com.example.android.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MusicAdapter extends ArrayAdapter<Music> {
    private static final String LOG_TAG = MusicAdapter.class.getSimpleName();

    public MusicAdapter(@NonNull Context context, ArrayList<Music> musicVar) {
        super(context, 0, musicVar);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        // Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        Music currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView ArtistText = (TextView) listItemView.findViewById(R.id.artist_list);
        // Get the Artist name from the current Music object and set this text on the name TextView
        ArtistText.setText(currentWord.getArtist());
        TextView TitleText = (TextView) listItemView.findViewById(R.id.song_list);
        TitleText.setText(currentWord.getTitle());
        //Return the whole list item layout so that it can be shown in the ListView
        return listItemView;

    }
}
