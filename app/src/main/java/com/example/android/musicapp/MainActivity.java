package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // All songs right are reserved to Disney
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Selection> options = new ArrayList<Selection>();
        options.add(new Selection("Disney songs"));
        options.add(new Selection("Classics"));
        SelectionAdapter adapter = new SelectionAdapter(this, options);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // if (indexOf(Selection) == 0) {
                if (position == 0) {
                    Intent myIntent = new Intent(MainActivity.this, DisneyActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
                if (position == 1) {
                    Intent myIntent = new Intent(MainActivity.this, Classics.class);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });

    }
}
