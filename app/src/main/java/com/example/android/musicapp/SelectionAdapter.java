package com.example.android.musicapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectionAdapter extends ArrayAdapter<Selection> {
    private static final String LOG_TAG = Selection.class.getSimpleName();
    private final ArrayList itemname;

    public SelectionAdapter(@NonNull Context context, ArrayList<Selection> itemname) {

        super(context, 0, itemname);
        this.itemname = itemname;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            Selection selected = getItem(position);
            TextView ArtistText = (TextView) listItemView.findViewById(R.id.artist_list);
            ArtistText.setText(selected.getTitle());
        }
        return listItemView;
    }
}